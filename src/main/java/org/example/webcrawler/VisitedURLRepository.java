package org.example.webcrawler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author amanjain
 **/
public class VisitedURLRepository {
    private final Set<String> visitedUrls = ConcurrentHashMap.newKeySet();

    public boolean add(String url){
        return visitedUrls.add(url);
    }

    public boolean contains(String url){
        return visitedUrls.contains(url);
    }

    public int size(){
        return visitedUrls.size();
    }
}
