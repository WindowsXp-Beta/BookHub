package com.windowsxp.bookstore.configuration;

import com.windowsxp.bookstore.utils.LogUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class LuceneConfig {

    public static final String BOOK_INFO_PATH = "src/main/resources/Lucene/dataDir/bookInfo.txt";
    private static final String LUCENE_INDEX_PATH ="src/main/resources/Lucene/indexDir/";

    @Bean
    public Analyzer analyzer() {
        return new StandardAnalyzer();
    }

    @Bean
    public Directory directory() throws IOException {
        Path path = Paths.get(LUCENE_INDEX_PATH);
        File file = path.toFile();
        if(!file.exists()) {
            // If the folder does not exist, create
            boolean result = file.mkdirs();
            if(!result){
                LogUtil.error("create lucene index directory failed");
            }
        }
        return FSDirectory.open(path);
    }

    @Bean
    public IndexWriter indexWriter(Directory directory, Analyzer analyzer) throws IOException {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // Clear index
        indexWriter.deleteAll();
        indexWriter.commit();
        return indexWriter;
    }
}