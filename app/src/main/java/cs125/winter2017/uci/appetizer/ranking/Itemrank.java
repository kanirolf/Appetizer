package cs125.winter2017.uci.appetizer.ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cs125.winter2017.uci.appetizer.Search.MenuItem;

/**
 * Created by Andrew on 3/19/2017.
 */

public class Itemrank {

    private String Query;
    private double T_calories;
    private double T_fat;
    private double T_protein;
    private double T_cholesterol;
    private double T_sugar;
    private double T_carbs;
    private double T_sodium;
    private double T_fiber;

    private double query_points = 300;
    private double calorie_points = 80;
    private double fat_points = 50;
    private double protein_points = 40;
    private double cholesterol_points = 50;
    private double sugar_points = 40;
    private double sodium_points = 40;
    private double carbs_points = 30;
    private double fiber_points = 30;
    private double total_points;

    private ArrayList<MenuItem> Items;

    public Itemrank(String query, double cal,double fat,double protein, double chol, double sugar, double carbs, double sodium, double fiber,ArrayList<MenuItem> items){
        Query = query.toLowerCase();
        T_calories = cal;
        T_fat = fat;
        T_protein = protein;
        T_carbs = carbs;
        T_cholesterol = chol;
        T_sugar = sugar;
        T_sodium = sodium;
        T_fiber = fiber;
        Items = items;
        total_points = query_points + calorie_points + fat_points + protein_points + carbs_points + cholesterol_points + sugar_points + fiber_points + sodium_points;
    }

    public double comp_query(String name){
        if (name.toLowerCase().contains(Query)){
            return query_points;
        }
        else{
            return 0;
        }
    }

    public double comp_calories(double calories){
        double diff = Math.abs(T_calories - calories);
        double ratio = diff/T_calories;
        if (ratio < .1){
            return calorie_points;
        }
        else if (ratio < .2){
            return (calorie_points*3/4);
        }
        else if (ratio < .3){
            return (calorie_points/2);
        }
        else if (ratio < .4){
            return (calorie_points/4);
        }
        else{
            return 0;
        }
    }

    public double comp_fat(double fat){
        double diff = T_fat - fat;
        double ratio = diff/T_fat;
        if (ratio > .3){
            return fat_points;
        }
        else if (ratio > .2){
            return (fat_points*3/4);
        }
        else if (ratio > .1){
            return (fat_points/2);
        }
        else if (ratio > 0){
            return (fat_points/4);
        }
        else{
            return 0;
        }
    }

    public double comp_protein(double protein){
        double diff = Math.abs(T_protein - protein);
        double ratio = diff/T_protein;
        if (ratio < .1){
            return protein_points;
        }
        else if (ratio < .2){
            return (protein_points*3/4);
        }
        else if (ratio < .3){
            return (protein_points/2);
        }
        else if (ratio < .4){
            return (protein_points/4);
        }
        else{
            return 0;
        }
    }

    public double comp_cholesterol(double cholesterol){
        double diff = T_cholesterol - cholesterol;
        double ratio = diff/T_cholesterol;
        if (ratio > .3){
            return cholesterol_points;
        }
        else if (ratio > .2){
            return (cholesterol_points*3/4);
        }
        else if (ratio > .1){
            return (cholesterol_points/2);
        }
        else if (ratio > 0){
            return (cholesterol_points/4);
        }
        else{
            return 0;
        }
    }

    public double comp_sugar(double sugar){
        double diff = T_sugar - sugar;
        double ratio = diff/T_sugar;
        if (ratio > .3){
            return sugar_points;
        }
        else if (ratio > .2){
            return (sugar_points*3/4);
        }
        else if (ratio > .1){
            return (sugar_points/2);
        }
        else if (ratio > 0){
            return (sugar_points/4);
        }
        else{
            return 0;
        }
    }

    public double comp_sodium(double sodium){
        double diff = T_sodium - sodium;
        double ratio = diff/T_sodium;
        if (ratio > .3){
            return sodium_points;
        }
        else if (ratio > .2){
            return (sodium_points*3/4);
        }
        else if (ratio > .1){
            return (sodium_points/2);
        }
        else if (ratio > 0){
            return (sodium_points/4);
        }
        else{
            return 0;
        }
    }

    public double comp_carbs(double carbs){
        double diff = Math.abs(T_carbs - carbs);
        double ratio = diff/T_carbs;
        if (ratio < .1){
            return carbs_points;
        }
        else if (ratio < .2){
            return (carbs_points*3/4);
        }
        else if (ratio < .3){
            return (carbs_points/2);
        }
        else if (ratio < .4){
            return (carbs_points/4);
        }
        else{
            return 0;
        }
    }

    public double comp_fiber(double fiber){
        double diff = Math.abs(T_fiber - fiber);
        double ratio = diff/T_fiber;
        if (ratio < .1){
            return fiber_points;
        }
        else if (ratio < .2){
            return (fiber_points*3/4);
        }
        else if (ratio < .3){
            return (fiber_points/2);
        }
        else if (ratio < .4){
            return (fiber_points/4);
        }
        else{
            return 0;
        }
    }

    public ArrayList<MenuItem> sort() {
        for (int i = 0; i < Items.size(); i++) {
            double item_total_points = total_points;
            double cur_points = 0;
            MenuItem item = Items.get(i);

            if (!item.getName().equals("")) {
                cur_points = cur_points + comp_query(item.getName());
            } else {
                item_total_points = item_total_points - query_points;
            }

            if (item.getCalorie() != -1) {
                cur_points = cur_points + comp_calories(item.getCalorie());
            } else {
                item_total_points = item_total_points - calorie_points;
            }

            if (item.getFat() != -1) {
                cur_points = cur_points + comp_fat(item.getFat());
            } else {
                item_total_points = item_total_points - fat_points;
            }

            if (item.getProtein() != -1) {
                cur_points = cur_points + comp_protein(item.getProtein());
            } else {
                item_total_points = item_total_points - protein_points;
            }

            if (item.getCholesterol() != -1) {
                cur_points = cur_points + comp_cholesterol(item.getCholesterol());
            } else {
                item_total_points = item_total_points - cholesterol_points;
            }

            if (item.getCarbs() != -1) {
                cur_points = cur_points + comp_carbs(item.getCarbs());
            } else {
                item_total_points = item_total_points - carbs_points;
            }

            if (item.getSodium() != -1) {
                cur_points = cur_points + comp_sodium(item.getSodium());
            } else {
                item_total_points = item_total_points - sodium_points;
            }

            if (item.getSugar() != -1) {
                cur_points = cur_points + comp_sugar(item.getSugar());
            } else {
                item_total_points = item_total_points - sugar_points;
            }

            if (item.getFibers() != -1) {
                cur_points = cur_points + comp_fiber(item.getFibers());
            } else {
                item_total_points = item_total_points - fiber_points;
            }


            item.setRank(cur_points/item_total_points);
        }


        Collections.sort(Items, new itemrankComparator());

        return Items;

    }
}
