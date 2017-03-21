package cs125.winter2017.uci.appetizer.Search;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class SearchPlacesRequestParser {
    private SearchPlacesRequestParser(){}

    public static List<Restaurant> parseJSON(JSONObject jsonObject) throws JSONException{
        final List<Restaurant> results = new ArrayList<>();

        JSONArray resultsArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < resultsArray.length(); i++)
            results.add(parseResult(resultsArray.getJSONObject(i)));

        return results;
    }

    public static Restaurant parseResult(JSONObject result) throws JSONException{
        Log.i("SEARCH_PLACES_R_PARSER", result.toString());
        String ID = result.getString("id");

        String name = result.getString("name");

        String address = "";
        if (result.has("vicinity"))
            address = result.getString("vicinity");

        int priceLevel = -1;
        if (result.has("price_level"))
            priceLevel = result.getInt("price_level");

        JSONObject location = result.getJSONObject("geometry").getJSONObject("location");
        double lat = location.getDouble("lat");
        double lng = location.getDouble("lng");


        double rating = 0;
        if (result.has("rating"))
            result.getDouble("rating");

        return new Restaurant(name, ID, address, lat, lng, priceLevel, rating);
    }
}
