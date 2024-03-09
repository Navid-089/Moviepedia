package com.example.java_project;

import java.util.ArrayList;
import java.util.List;

public class SearchByProductionCompany {

   List <Movie> prodMovieList= new ArrayList<>();

    public SearchByProductionCompany(List<Movie> prodMovieList) {

        this.prodMovieList.addAll(prodMovieList);
    }

    public List<Movie> mostRecentMovies()
    {
        List<Movie> movieList=new ArrayList<>();
        int year=-1111;

        for(Movie m: prodMovieList)
        {
            if(m.getYear() > year)
                year=m.getYear();
        }

        for(Movie m: prodMovieList)
        {
            if(m.getYear() == year )
                movieList.add(m);
        }

        return movieList;
    }

    public List<Movie> maxRevenueMovies()
    {
        List<Movie> movieList=new ArrayList<>();
        long rev=0;

        for(Movie m: prodMovieList)
        {
            if(m.getRevenue() > rev)
                rev=m.getRevenue();
        }

        for(Movie m: prodMovieList)
        {
            if(m.getRevenue() == rev )
                movieList.add(m);
        }

        return movieList;
    }

    public long totalProfit()
    {
        long sum=0;

        for(Movie m: prodMovieList)
            sum += m.getProfit();

        return sum;
    }
 }
