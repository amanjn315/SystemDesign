package org.example.bookmyshow;

import java.time.Duration;

/**
 * @author amanjain
 **/
public class Movie {
    String name;
    String desc;
    Duration duration;

    public Movie(String name, String desc, Duration duration) {
        this.name = name;
        this.desc = desc;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Duration getDuration() {
        return duration;
    }
}
