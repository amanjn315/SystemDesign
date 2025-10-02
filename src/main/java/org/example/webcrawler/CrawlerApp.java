package org.example.webcrawler;

import java.util.List;

/**
 * @author amanjain
 **/
public class CrawlerApp {
    public static void main(String[] args){
        CrawlerConfiguration config = new CrawlerConfiguration(List.of("https://jsoup.org/"))
                .setMaxPagesToCrawl(100)
                .setNumberOfThreads(4)
                .setMaxDepth(3);

        Crawler crawler = new Crawler(config);

        System.out.println("Starting crawler...");
        long startTime = System.currentTimeMillis();
        crawler.start();
        long endTime = System.currentTimeMillis();
        System.out.println("Crawler finished in " + (endTime - startTime) + "ms.");
    }
}
