/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Noah McElroy
 */
public class DVD {
    private String title;
    private int month;
    private int day;
    private int year;
    private String MPAARating;
    private String directorName;
    private String Studio;
    private String userRating;

    public DVD(String title, int month, int day, int year, String MPAARating, String directorName, String Studio, String userRating) {
        this.title = title;
        this.month = month;
        this.day = day;
        this.year = year;
        this.MPAARating = MPAARating;
        this.directorName = directorName;
        this.Studio = Studio;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMPAARating() {
        return MPAARating;
    }

    public void setMPAARating(String MPAARating) {
        this.MPAARating = MPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String Studio) {
        this.Studio = Studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public DVD(String title) {
        this.title = title;
    }
}
