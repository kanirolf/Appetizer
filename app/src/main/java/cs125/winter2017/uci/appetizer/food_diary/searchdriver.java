package cs125.winter2017.uci.appetizer.food_diary;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Andrew on 3/18/2017.
 */

public class searchdriver {
    //Fix the amount of nutrionix searches to like 1-2
    public static void main(String[] args){
        Search_Places temp = new Search_Places("burgers", 33.6825773, -117.8140463,5000,-1,"restaurant","snack",false);
        String jsonresult = temp.search();
        JsonElement parser = new JsonParser().parse(jsonresult);
        JsonObject jsobject = parser.getAsJsonObject();
        JsonArray jsarray = jsobject.getAsJsonArray("results");
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        ArrayList<String> IDS = new ArrayList<String>();

        for(int i = 0; i < jsarray.size();i++) {
            jsobject = jsarray.get(i).getAsJsonObject();
            String name = jsobject.get("name").getAsString();
            String id = jsobject.get("id").getAsString();
            String address = jsobject.get("formatted_address").getAsString();
            int price = 1;

            if(jsobject.get("price_level") != null){
                price = jsobject.get("price_level").getAsInt();
            }
            double rating = Double.parseDouble(jsobject.get("rating").getAsString());
            jsobject = jsobject.getAsJsonObject("geometry");
            jsobject = jsobject.getAsJsonObject("location");
            double lat = Double.parseDouble(jsobject.get("lat").getAsString());
            double lng = Double.parseDouble(jsobject.get("lng").getAsString());
            restaurants.add(new Restaurant(name,id,address,lat,lng,price,rating));
            SearchMenu sm = new SearchMenu(name);
            String result = sm.search();
            JsonElement parser2 = new JsonParser().parse(result);
            JsonObject jsobject2 = parser2.getAsJsonObject();
            System.out.println(sm.getUrl());
            //System.out.println(jsobject2);
            if(jsobject2.get("total") != null && jsobject2.get("total").getAsInt() > 0){
                JsonArray jsonarray = jsobject2.getAsJsonArray("hits");
                jsobject2 = jsonarray.get(0).getAsJsonObject();
                if(jsobject2.get("type").getAsInt() == 1){
                    IDS.add(jsobject2.get("_id").getAsString());
                }
            }
        }
        System.out.println(IDS.get(0));

    }
}
