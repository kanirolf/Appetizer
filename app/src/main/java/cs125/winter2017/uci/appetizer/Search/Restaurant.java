package cs125.winter2017.uci.appetizer.Search;

import java.util.ArrayList;

/**
 * Created by Andrew on 3/18/2017.
 */

public class Restaurant {

    private String Name;
    private String RestID;
    private String Address;
    private double Lat;
    private double Lon;
    private int Price;
    private double Rating;
    private double Rank;
    private ArrayList<MenuItem> Menu;

    public Restaurant(String name, String restID, String address, double lat, double lon, int price, double rating){
        Name = name;
        RestID = restID;
        Address = address;
        Lat = lat;
        Lon = lon;
        Price = price;
        Rating = rating;
        Rank = 0;
        Menu = new ArrayList<MenuItem>();

    }

    public String toString(){
        return "RESTAURANT: " + Name + "     RANK: " + Rank + " \n";
    }

    public void setRank(double rank){ Rank = rank;}

    public void setMenu(ArrayList<MenuItem> menu){
        Menu = menu;
    }

    public String getName(){return Name;}

    public String getAddress(){return Address;}

    public int getPrice(){return Price;}

    public double getLat() {return Lat;}

    public double getLon() {return Lon;}

    public double getRating() {return Rating;}

    public String getRestID() {return RestID;}

    public double getRank(){return Rank;}

    public ArrayList<MenuItem> getMenu() {return Menu;}
}
