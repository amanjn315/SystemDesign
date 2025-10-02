package org.example.webcrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amanjain
 **/
public class PageParser {
    /**
     * Parses HTML content and extracts all absolute URLs.
     *
     * @param htmlContent The HTML content of the page.
     * @param baseUrl The base URL of the page, used to resolve relative links.
     * @return A list of absolute URLs found on the page.
     */
    public List<String> extractLinks(String htmlContent, String baseUrl){
        Document doc = Jsoup.parse(htmlContent, baseUrl);
        Elements links = doc.select("a[href]");

        final String cleanBaseUrl = baseUrl.split("#")[0];

        return links.stream()
                .map(link -> link.attr("abs:href"))
                .map(url -> url.split("#")[0])
                .filter(url -> !url.isEmpty())
                .filter(url -> !url.equals(cleanBaseUrl))
                .filter(url -> url.startsWith("http"))
                .distinct()
                .collect(Collectors.toList());
    }
}
