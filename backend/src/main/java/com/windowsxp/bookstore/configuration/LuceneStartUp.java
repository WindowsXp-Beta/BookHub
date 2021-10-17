package com.windowsxp.bookstore.configuration;

import com.windowsxp.bookstore.serviceimpl.SearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.IndexWriter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@Component
@Order(value = 1)
public class LuceneStartUp implements ApplicationRunner {

    private final SearchService searchService;
    private final IndexWriter indexWriter;

    @Override
    public void run(ApplicationArguments args){
        log.info("begin building Lucene index");
        Date start = new Date();
        try {
            searchService.indexDocs(indexWriter, LuceneConfig.BOOK_INFO_PATH);
            Date end = new Date();
            log.info("finish building Lucene index in " + (end.getTime() - start.getTime()) + " total milliseconds");
        } catch (IOException e) {
            log.error(" caught a " + e.getClass() +
                    "\n with message: " + e.getMessage());
        }
    }
}
