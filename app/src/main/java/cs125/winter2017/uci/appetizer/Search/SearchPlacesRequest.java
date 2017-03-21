package cs125.winter2017.uci.appetizer.Search;

import android.location.Location;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;


public class SearchPlacesRequest extends JsonObjectRequest {

    private static final String API_KEY = "AIzaSyARCNUYBmwBkXLBCWkmddG2Kr-Bcb0xe-Y";
    private static final String HOSTNAME = "maps.googleapis.com";
    private static final String PATH = "/maps/api/place/nearbysearch/json";

    private SearchPlacesRequest(Builder builder){
        super(Method.GET, getURL(builder), null, newResponseListener(builder.listener),
                builder.errorListener);
    }

    public static class Builder {

        private String query;
        private String mealType;
        private Location location;

        private int radius;
        private int price;

        private ResponseListener listener;
        private Response.ErrorListener errorListener;

        public Builder(){
            query = "";
            location = null;

            radius = 3000;
            price = -1;

            listener = null;
            errorListener = null;
        }

        public Builder setQuery(String query){
            this.query = StringEscapeUtils.escapeHtml4(query);
            return this;
        }

        public Builder setMealType(String mealType){
            this.mealType = mealType;
            return this;
        }

        public Builder setLocation(Location location){
            this.location = location;
            return this;
        }

        public Builder setRadius(int radius){
            this.radius = radius;
            return this;
        }

        public Builder setPrice(int price){
            this.price = price;
            return this;
        }

        public Builder setListener(ResponseListener listener){
            this.listener = listener;
            return this;
        }

        public Builder setErrorListener(Response.ErrorListener errorListener){
            this.errorListener = errorListener;
            return this;
        }

        public SearchPlacesRequest build(){
            return new SearchPlacesRequest(this);
        }
    }

    private static Response.Listener<JSONObject> newResponseListener(final ResponseListener listener){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Restaurant> results = Collections.emptyList();
                try {
                    results = SearchPlacesRequestParser.parseJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onResponse(results);
            }
        };
    }


    private static String getURL(Builder builder){
        String queryString = "keyword=" + builder.query;
        queryString +=  "&type=" + builder.mealType;

        Location location = builder.location;
        double lat = 0;
        double lng = 0;
        if (location != null){
            lat = location.getLatitude();
            lng = location.getLongitude();
        }

        queryString += "&location=" + Double.toString(lat) + "," + Double.toString(lng);
        queryString += "&radius=" + builder.radius;
        if (builder.price > -1)
            queryString = queryString + "&maxprice=" + builder.price;

        queryString = queryString + "&key=" + API_KEY;

        try {
            return new URI("https", HOSTNAME, PATH, queryString, null).toASCIIString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

    public interface ResponseListener {
        void onResponse(List<Restaurant> results);
    }
}
