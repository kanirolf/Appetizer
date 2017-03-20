package cs125.winter2017.uci.appetizer.Search;

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

    public Restaurant(String name, String restID, String address, double lat, double lon, int price, double rating){
        Name = name;
        RestID = restID;
        Address = address;
        Lat = lat;
        Lon = lon;
        Price = price;
        Rating = rating;
    }

    public String getName(){return Name;}

    public String getAddress(){return Address;}

    public int getPrice(){return Price;}

    public double getLat() {return Lat;}

    public double getLon() {return Lon;}

    public double getRating() {return Rating;}

    public String getRestID() {return RestID;}

}
