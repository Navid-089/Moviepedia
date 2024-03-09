package com.example.java_project;

import java.io.Serializable;

public class Movie implements Serializable {
    public String title;
    public int year;
    public String genre1, genre2, genre3;
    public int time;
    public String productionCompany;
    public long revenue;
    public long budget;

    public String genre;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public void setGenre3(String genre3) {
        this.genre3 = genre3;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getYear() {
        return year;
    }

    public int getTime() {
        return time;
    }

    public String getGenre1() {
        return genre1;
    }

    public String getGenre2() {
            return genre2;
    }

    public String getGenre3() {
            return genre3;
    }
    public String getProductionCompany() {
        return productionCompany;
    }
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        if(!genre2.equals(""))
        {
            if(!genre3.equals(""))
                return genre1 + "," + genre2 + "," + genre3;
            else
                return genre1 + "," + genre2;
        }
        else
            return genre1;
    }

    public long getProfit() { return revenue - budget; }


}

