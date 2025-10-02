package org.example.webcrawler;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author amanjain
 **/
public class URLFrontier {
    private final Queue<String> urlQueue = new ConcurrentLinkedQueue<>();

    public void addUrl(String url){
        if(url != null && !url.isEmpty()){
            urlQueue.add(url);
        }
    }

    public String getNextUrl(){
        return urlQueue.poll();
    }

    public boolean isEmpty(){
        return urlQueue.isEmpty();
    }

    public int size(){
        return urlQueue.size();
    }
}
