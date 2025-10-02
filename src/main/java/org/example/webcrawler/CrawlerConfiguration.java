package org.example.webcrawler;

import java.util.List;

/**
 * @author amanjain
 **/
public class CrawlerConfiguration {
    private List<String> seedUrls;
    private int maxDepth = 5;
    private int maxPagesToCrawl = 1000;
    private int numberOfThreads = 4;
    private long politenessDelayMillis = 200;
    private String userAgent = "MySimpleJavaCrawler/1.0";

    public CrawlerConfiguration(List<String> seedUrls){
        this.seedUrls = seedUrls;
    }

    public CrawlerConfiguration setMaxDepth(int maxDepth){
        this.maxDepth = maxDepth;
        return this;
    }

    public CrawlerConfiguration setMaxPagesToCrawl(int maxPagesToCrawl) {
        this.maxPagesToCrawl = maxPagesToCrawl;
        return this;
    }

    public CrawlerConfiguration setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        return this;
    }

    public List<String> getSeedUrls() {
        return seedUrls;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getMaxPagesToCrawl() {
        return maxPagesToCrawl;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public long getPolitenessDelayMillis() {
        return politenessDelayMillis;
    }

    public String getUserAgent() {
        return userAgent;
    }


}
