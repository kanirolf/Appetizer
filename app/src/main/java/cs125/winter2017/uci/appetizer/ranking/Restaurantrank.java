package cs125.winter2017.uci.appetizer.ranking;

import java.util.ArrayList;
import java.util.Collections;

import cs125.winter2017.uci.appetizer.Search.MenuItem;
import cs125.winter2017.uci.appetizer.Search.Restaurant;

/**
 * Created by Andrew on 3/19/2017.
 */

public class Restaurantrank {

    private String Query;
    private double T_calories;
    private double T_fat;
    private double T_protein;
    private double T_cholesterol;
    private double T_sugar;
    private double T_carbs;
    private double T_sodium;
    private double T_fiber;
    private ArrayList<Restaurant> Restaurants;

    public Restaurantrank(String query, double cal,double fat,double protein, double chol, double sugar, double carbs, double sodium, double fiber,ArrayList<Restaurant> restaurants){
        Query = query.toLowerCase();
        T_calories = cal;
        T_fat = fat;
        T_protein = protein;
        T_carbs = carbs;
        T_cholesterol = chol;
        T_sugar = sugar;
        T_sodium = sodium;
        T_fiber = fiber;
        Restaurants = restaurants;
    }

    private double getrank(ArrayList<MenuItem> items){
        int size = items.size();
        if (size > 10){
            size = 10;
        }
        double ranksum = 0;
        for (int i = 0; i < size; i++){
            ranksum += items.get(i).getRank();
        }
        return ranksum/size;
    }

    public ArrayList<Restaurant> sort(){
        for (int i = 0; i < Restaurants.size(); i++){
            Restaurant cur = Restaurants.get(i);
            Itemrank i_rank = new Itemrank(Query,T_calories,T_fat,T_protein,T_cholesterol, T_sugar, T_carbs, T_sodium, T_fiber, cur.getMenu());
            cur.setMenu(i_rank.sort());
            if (cur.getMenu().size() >0)
                cur.setRank(getrank(cur.getMenu()));

        }
        Collections.sort(Restaurants,new RestaurantComparator());
        return Restaurants;
    }



}
