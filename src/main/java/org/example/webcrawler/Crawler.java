package org.example.webcrawler;

import java.util.concurrent.*;

/**
 * @author amanjain
 **/
public class Crawler {
    private final CrawlerConfiguration config;
    private final URLFrontier frontier;
    private final VisitedURLRepository visitedRepository;
    private final PageFetcher fetcher;
    private final PageParser parser;

    public Crawler(CrawlerConfiguration config) {
        this.config = config;
        this.frontier = new URLFrontier();
        this.visitedRepository = new VisitedURLRepository();
        this.fetcher = new PageFetcher(config.getUserAgent());
        this.parser = new PageParser();
    }

    public void start(){
        // 1. Add seed URLs to frontier and visited repository
        for (String seedUrl : config.getSeedUrls()){
            if(visitedRepository.add(seedUrl)){
                frontier.addUrl(seedUrl);
            }
        }

        // 2. Create a fixed-size thread pool
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(config.getNumberOfThreads());

        // 3. Submit a fixed number of persistent worker tasks
        for (int i = 0; i < config.getNumberOfThreads(); i++) {
            // NOTE: We no longer pass a specific URL to the worker
            executor.submit(new WorkerTask(frontier, visitedRepository, fetcher, parser, config));
        }

        // 4. Shutdown the executor
        monitorAndShutdown(executor);
        System.out.println("Crawling finished. Total pages crawled: " + visitedRepository.size());
    }

    private void monitorAndShutdown(ThreadPoolExecutor executor) {
        try {
            while (true) {
                Thread.sleep(5000); // Check every 5 seconds

                // CONDITION 1: Check if the page limit has been reached.
                if (visitedRepository.size() >= config.getMaxPagesToCrawl()) {
                    System.out.println("Max pages to crawl limit (" + config.getMaxPagesToCrawl() + ") reached. Shutting down.");
                    shutdownExecutor(executor);
                    break; // Exit the monitoring loop
                }

                // CONDITION 2: Check if the crawl has finished naturally.
                // This is important for crawls that finish before hitting the max page limit.
                if (frontier.isEmpty() && executor.getActiveCount() == 0) {
                    System.out.println("Frontier is empty and all threads are idle. Shutting down.");
                    shutdownExecutor(executor);
                    break; // Exit the monitoring loop
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            shutdownExecutor(executor);
        }
    }

    private void shutdownExecutor(ExecutorService executor) {
        try {
            System.out.println("Attempting to shutdown executor...");
            executor.shutdown();
            if(!executor.awaitTermination(60, TimeUnit.SECONDS)){
                System.err.println("Executor did not terminate in 60 seconds.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
