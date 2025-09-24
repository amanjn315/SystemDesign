package org.example.lru;

/**
 * @author amanjain
 **/
public class LruCacheDemo {
    public static void main(String[] args){
        Cache cache = new Cache(3);
        ReverseIndexService service = new ReverseIndexService();
        QueryApi api = new QueryApi(cache, service);

        System.out.println("Processing queries...\n");

        api.processQuery("What is caching?");
        api.processQuery("How does LRU work?");
        api.processQuery("Show me Java code");

        System.out.println("--- Cache is now full ---\n");

        api.processQuery("What is caching?");
        System.out.println();

        api.processQuery("Why use a HashMap?");
        System.out.println();

        api.processQuery("How does LRU work?");
    }
}
