package com.moviebooking.moviesearch.entity;

import java.sql.Date;
import java.util.List;

public class Movie {
    private Integer id;
    private String title;

    private Date releasedate;

    private String genre;

    private List<String> languages;
}
