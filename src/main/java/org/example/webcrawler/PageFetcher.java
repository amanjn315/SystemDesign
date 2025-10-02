package org.example.webcrawler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * @author amanjain
 **/
public class PageFetcher {
    private final HttpClient httpClient;
    private final String userAgent;

    public PageFetcher(String userAgent){
        this.userAgent = userAgent;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public String fetch(String url) throws IOException, InterruptedException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(20))
                    .header("User-Agent", userAgent)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() >= 200 && response.statusCode() < 300){
                return response.body();
            } else {
                System.err.println("Failed to fetch " + url + ". Status: " + response.statusCode());
                return null;
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid URL format: " + url);
            return null;
        }
    }
}
