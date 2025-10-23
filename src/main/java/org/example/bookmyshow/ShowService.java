package org.example.bookmyshow;

import java.util.Map;

/**
 * @author amanjain
 **/
public class ShowService {
    private Map<String, Show> showDatabase;

    public Show getShow(String showId){
        return showDatabase.get(showId);
    }
}
