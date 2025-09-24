package org.example.lru;

/**
 * @author amanjain
 **/
public class QueryApi {
    private final Cache memoryCache;
    private final ReverseIndexService reverseIndexService;

    public QueryApi(Cache memoryCache, ReverseIndexService reverseIndexService) {
        this.memoryCache = memoryCache;
        this.reverseIndexService = reverseIndexService;
    }

    public String processQuery(String query){
        String parsedQuery = parseQuery(query);
        String results = memoryCache.get(parsedQuery);

        if(results == null){
            System.out.println("Cache Miss! Fetch from service for: " + parsedQuery);
            results = reverseIndexService.processSearch(parsedQuery);
            memoryCache.set(parsedQuery, results);
        } else {
            System.out.println("Cache Hit! Found in cache: " + parsedQuery);
        }
        return results;
    }

    private String parseQuery(String query) {
        return query.toLowerCase().trim();
    }
}
