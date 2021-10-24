package com.windowsxp.bookhub.backend.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.windowsxp.bookhub.backend.dao.BookDao;
import com.windowsxp.bookhub.backend.utils.LogUtil;
import com.windowsxp.bookhub.book.entity.Book;
import lombok.AllArgsConstructor;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchService {

    private final BookDao bookDao;
    private final Directory directory;
    private final Analyzer analyzer;

    public void indexDocs(final IndexWriter writer, String rawPath) throws IOException {
        Path path = Paths.get(rawPath);
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        indexDoc(writer, file);
                    } catch (IOException ignore) {
                        // don't index files that can't be read.
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            indexDoc(writer, path);
        }
    }

    private void indexDoc(IndexWriter writer, Path file) throws IOException {
        try (InputStream stream = Files.newInputStream(file)) {
            InputStreamReader read = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(read);
            String rawBookItem = bufferedReader.readLine();
            while(rawBookItem != null) {
                JSONObject bookItem = JSONObject.parseObject(rawBookItem);
                Document doc = new Document();

                doc.add(new StringField("bookId", bookItem.get("bookId").toString(), Field.Store.YES));

                doc.add(new TextField("description", bookItem.get("description").toString(), Field.Store.NO));

                if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
                    LogUtil.debug("adding " + file);
                    writer.addDocument(doc);
                } else {
                    LogUtil.debug("updating " + file);
                    writer.updateDocument(new Term("path", file.toString()), doc);
                }
                rawBookItem = bufferedReader.readLine();
            }
            writer.commit();
        }
    }

    public Page<Book> searchBooks(String rawQuery, Integer page, Integer pageSize) throws Exception {
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        //refactor: make the field a variable
        String field = "description";
        QueryParser parser = new QueryParser(field, analyzer);
        Query query = parser.parse(rawQuery);
        LogUtil.debug("Searching for: " + query.toString(field));

        List<Book> bookList = new ArrayList<>();
        TopDocs results = indexSearcher.search(query, pageSize * (page + 1));
        ScoreDoc[] hits = results.scoreDocs;
        int numTotalHits = Math.toIntExact(results.totalHits.value);
        int start = page * pageSize;
        int end = Math.min(numTotalHits, pageSize * (page + 1));
        for(int i = start; i < end; i++){
            Document doc = indexSearcher.doc(hits[i].doc);
            Optional<Book> optionalBook = bookDao.findBookById(Integer.valueOf(doc.get("bookId")));
            optionalBook.ifPresent(bookList::add);
        }
        return new PageImpl<>(bookList, PageRequest.of(page, pageSize), numTotalHits);
    }

}