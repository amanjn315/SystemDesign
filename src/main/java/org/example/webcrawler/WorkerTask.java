package org.example.webcrawler;

import java.io.IOException;
import java.util.List;

/**
 * @author amanjain
 **/
public class WorkerTask implements Runnable {
    private final URLFrontier frontier;
    private final VisitedURLRepository visitedRepository;
    private final PageFetcher fetcher;
    private final PageParser parser;
    private final CrawlerConfiguration config;

    public WorkerTask(URLFrontier frontier, VisitedURLRepository visitedRepository, PageFetcher fetcher, PageParser parser, CrawlerConfiguration config) {
        this.frontier = frontier;
        this.visitedRepository = visitedRepository;
        this.fetcher = fetcher;
        this.parser = parser;
        this.config = config;
    }

    @Override
    public void run(){
        // This worker will run until there are no more URLs to process
        while (!Thread.currentThread().isInterrupted()) {
            String urlToCrawl = frontier.getNextUrl();
            if(urlToCrawl != null){
                try {
                    // 1. Fetch the page content
                    String htmlContent = fetcher.fetch(urlToCrawl);

                    if(htmlContent != null){
                        // 2. Process the content (e.g. save to disk, index etc.)
                        System.out.println("Crawled: " + urlToCrawl + " by " + Thread.currentThread().getName());
                        // For this example, we just print it. In a real app, you'd have a PageProcessor component

                        // 3. Parse and extract links
                        List<String> newLinks = parser.extractLinks(htmlContent, urlToCrawl);

                        // 4. Add new, unvisited links to the frontier
                        for(String link : newLinks){
                            if(visitedRepository.add(link)){
                                frontier.addUrl(link);
                            }
                        }

                        // 5. Apply politeness delay
                        Thread.sleep(config.getPolitenessDelayMillis());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restores the interrupted status
                    System.err.println("Worker task for " + urlToCrawl + " was interrupted.");
                } catch (Exception e){
                    System.out.println("Error processing " + urlToCrawl + ": " + e.getMessage());
                }
            } else {
                // Frontier is empty, this thread can pause and check again later
                try {
                    Thread.sleep(1000); // Wait for more URLs to appear
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
