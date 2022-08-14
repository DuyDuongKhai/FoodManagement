/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class Food implements Comparable<Food> {

    private String id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private String date;

    public Food(String id, String name, double weight, String type, String place, String date) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.date = date;
    }

    public Food() {
        id = "";
        name = "";
        weight = 0;
        type = "";
        place = "";
        date = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        
        return String.format("%-15s%c%-15s%c%-10.2f%c%-15s%c%-24s%c%-15s", id,'|', name,'|', weight,'|', type,'|', place,'|', date,'|' );
    }

    @Override
    public int compareTo(Food o) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date day = df.parse(date);
            Date oDay = df.parse(o.getDate());
            if (day.compareTo(oDay) > 0) {
                return -1;
            } else if (day.compareTo(oDay) < 0) {
                return 1;
            } else {
                if (id.compareTo(o.getId()) > 0) {
                    return -1;
                } else if (id.compareTo(o.getId()) < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return 0;
    }

}
