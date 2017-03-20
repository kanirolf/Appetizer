package cs125.winter2017.uci.appetizer.Search;



import java.util.ArrayList;

public class searchdriver {
    //Fix the amount of nutrionix searches to like 1-2
    public static void main(String[] args){
        SearchMenu sm = new SearchMenu("Jack in the box");
        ArrayList<MenuItem> items = sm.search();


//        Search_Places temp = new Search_Places("burgers", 33.6825773, -117.8140463,5000,-1,"restaurant","snack",false);
//        String jsonresult = temp.search();
//        JsonElement parser = new JsonParser().parse(jsonresult);
//        JsonObject jsobject = parser.getAsJsonObject();
//        JsonArray jsarray = jsobject.getAsJsonArray("results");
//        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
//        ArrayList<String> IDS = new ArrayList<String>();
//
//        for(int i = 0; i < 2;i++) {
//            jsobject = jsarray.get(i).getAsJsonObject();
//            String name = jsobject.get("name").getAsString();
//            String id = jsobject.get("id").getAsString();
//            String address = jsobject.get("formatted_address").getAsString();
//            int price = 1;
//
//            if (jsobject.get("price_level") != null) {
//                price = jsobject.get("price_level").getAsInt();
//            }
//            double rating = Double.parseDouble(jsobject.get("rating").getAsString());
//            jsobject = jsobject.getAsJsonObject("geometry");
//            jsobject = jsobject.getAsJsonObject("location");
//            double lat = Double.parseDouble(jsobject.get("lat").getAsString());
//            double lng = Double.parseDouble(jsobject.get("lng").getAsString());
//            restaurants.add(new Restaurant(name, id, address, lat, lng, price, rating));
//            SearchMenu sm = new SearchMenu(name);
//        }
//
//
    }
}
