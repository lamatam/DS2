/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.ds_project;

/**
 *
 * @author loolo
 */
public class DateTime implements IDateTime {
    int day;
    int month;
    int year;
    int hour;
    int minute;
    public DateTime (String dt)
    {
    date = date.trim();
        int firstSlash = date.indexOf('/');
        int secondSlash  = date.lastIndexOf('/');
        int space = date.lastIndexOf(' ');
        int first_dots = date.indexOf(':');
        
        this.month = Integer.parseInt(date.substring(0, firstSlash ));
        this.day = Integer.parseInt(date.substring( firstSlash +1, secondSlash));
        this.year = Integer.parseInt(date.substring(secondSlash+1, space));
        this.hour = Integer.parseInt(date.substring( space+1 , first_dots));
        this.minute = Integer.parseInt(date.substring( first_dots+1));
        
    }
    public DateTime ( int year, int month, int day , int hour, int minute)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
        public DateTime ( IDateTime dt)
    {
        this.year = dt.getYear();
        this.month = dt.getMonth();
        this.day = dt.getDay();
        this.hour = dt.getHour();
        this.minute = dt.getMinute();
    }

    @Override
    public int getYear() {
    return year;    }

    @Override
    public int getMonth() {
    return month;
    }

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public int getHour() {
        return hour;
    }

    @Override
    public int getMinute() {
    return minute;
    }

    @Override
    public String format(){
            String str = "";
        str += String.format("%02d" , month) + "/";
        str += String.format("%02d" , day) + "/";
        str += String.format("%04d" , year) + " ";
        str += String.format("%02d" , hour) + ":";
        str += String.format("%02d" , minute) ;
        return str;
    }

    @Override
    public int compareTo(IDateTime other)
    {
        if (this.year != other.getYear())
            return (this.year - other.getYear());
        else if (this.month != other.getMonth())
            return (this.month - other.getMonth());
        else if (this.day != other.getDay())
            return (this.day - other.getDay());
        else if (this.hour != other.getHour())
            return (this.hour - other.getHour());
        return (this.minute - other.getMinute());
    }
    
}
