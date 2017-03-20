package cs125.winter2017.uci.appetizer.ranking;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import cs125.winter2017.uci.appetizer.Search.MenuItem;
import cs125.winter2017.uci.appetizer.Search.Restaurant;
import cs125.winter2017.uci.appetizer.Search.SearchMenu;
import cs125.winter2017.uci.appetizer.Search.Search_Places;

/**
 * Created by Andrew on 3/19/2017.
 */

public class RankingDriver {
    public static void main(String[] args){
//        SearchMenu sm = new SearchMenu("Red Robin");
//        ArrayList<MenuItem> items = sm.search("521b95434a56d006cae29678");
//        Itemrank itemrank = new Itemrank("burger", 700, 19, 18, 0, 4, 28, 430, 1, items);
//        System.out.println(itemrank.sort());

        Search_Places temp = new Search_Places("fries", 33.6825773, -117.8140463,5000,-1,"snack");
        String jsonresult = temp.search();
        JsonElement parser = new JsonParser().parse(jsonresult);
        JsonObject jsobject = parser.getAsJsonObject();
        JsonArray jsarray = jsobject.getAsJsonArray("results");
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        ArrayList<String> IDS = new ArrayList<String>();
//        int size = 5;
//        if(jsarray.size() < 5)
//            size = jsarray.size();
        for(int i = 0; i < jsarray.size() && restaurants.size() < 5;i++) {
            jsobject = jsarray.get(i).getAsJsonObject();
            String name = jsobject.get("name").getAsString();
            System.out.println(name);
            String id = jsobject.get("id").getAsString();
            String address = jsobject.get("formatted_address").getAsString();
            int price = 1;

            if (jsobject.get("price_level") != null) {
                price = jsobject.get("price_level").getAsInt();
            }
            double rating = Double.parseDouble(jsobject.get("rating").getAsString());
            jsobject = jsobject.getAsJsonObject("geometry");
            jsobject = jsobject.getAsJsonObject("location");
            double lat = Double.parseDouble(jsobject.get("lat").getAsString());
            double lng = Double.parseDouble(jsobject.get("lng").getAsString());
            Restaurant curRest = new Restaurant(name, id, address, lat, lng, price, rating);
            SearchMenu sm = new SearchMenu(name);
            ArrayList<MenuItem> menu = sm.search();
            curRest.setMenu(menu);
            if (menu.size() > 0)
                restaurants.add(curRest);

        }

        Restaurantrank ranker = new Restaurantrank("fries", 700, 19, 18, 0, 4, 28, 430, 1, restaurants);
        restaurants = ranker.sort();
        for(int i = 0; i < restaurants.size();i++)
            System.out.println(restaurants.get(i));

    }


}
