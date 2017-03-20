package cs125.winter2017.uci.appetizer.Search;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;




public class SearchMenu {

    private String restaurantName;
    private String ID = "";
    private ArrayList<MenuItem> Menuitems = new ArrayList<>();
    private String AppID = "24e35392";
    private String AppKey = "b31b775b1586d5cef18054eadd23b48e";
    private String AppID2 = "463febfe";
    private String AppKey2 = "a60ae69ae343086a86778e86f3fb9851";

    public SearchMenu(String name){
        restaurantName = name;
    }

    private String getUrl(){
        String url = "https://api.nutritionix.com/v1_1/brand/search?query=";
        url = url + restaurantName.replaceAll(" ","+");
        url = url + "&min_score=1&appId=";
        url = url + AppID2;
        url = url + "&appKey=";
        url = url + AppKey2;
        return url;
    }

    private String getUrlitems(){
        ID = "521b95434a56d006cae29678";
        String url = "https://api.nutritionix.com/v1_1/search/?brand_id=";
        url = url + ID;
        url = url + "&results=0%3A50&cal_min=100&cal_max=1000&fields=item_name%2Cbrand_name" +
                "%2Citem_id%2Cbrand_id%2Cnf_calories%2Cnf_total_fat%2Cnf_cholesterol%2Cnf_sodium" +
                "%2Cnf_total_carbohydrate%2Cnf_sugars%2Cnf_protein%2Cnf_dietary_fiber" +
                "%2Cnf_serving_size_qty%2Cnf_serving_size_unit&appId=";
        url = url + AppID2;
        url = url + "&appKey=";
        url = url + AppKey2;
        return url;
    }

    private String getURLString(String url){
        try {
            URL con = new URL(url);
            HttpURLConnection connect = (HttpURLConnection) con.openConnection();
            InputStreamReader in = new InputStreamReader(connect.getInputStream());

            StringBuilder sb = new StringBuilder();
            char[] buff = new char[1024];
            int read;
            while ((read = in.read(buff)) != -1) {
                sb.append(buff,0,read);
            }

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }


    private String searchID(){

        String url = getUrl();
        String result = getURLString(url);
        JsonElement parser2 = new JsonParser().parse(result);
        JsonObject jsobject2 = parser2.getAsJsonObject();
        //System.out.println(jsobject2);
        if(jsobject2.get("total") != null && jsobject2.get("total").getAsInt() > 0) {
            JsonArray jsonarray = jsobject2.getAsJsonArray("hits");
            jsobject2 = jsonarray.get(0).getAsJsonObject();
            JsonObject jsobject3 = jsobject2.getAsJsonObject("fields");

            if (jsobject3.get("type").getAsInt() == 1) {
                ID = jsobject2.get("_id").getAsString();

            }
        }
        return ID;

    }

    private void searchMenu(){
        String url = getUrlitems();
        String url_json = getURLString(url);
//        String url_json = "";
//        try{
//            Scanner in = new Scanner(new FileReader("C:\\Users\\Andrew\\Documents\\Android Studio\\Appetizer\\app\\src\\main\\java\\cs125\\winter2017\\uci\\appetizer\\Search\\menuSearchJSON"));
//            while(in.hasNext()){
//                url_json = url_json + in.next();
//            }
//        }
//        catch( FileNotFoundException e){
//            e.printStackTrace();
//        }
//        System.out.println(url_json);
        JsonElement parser2 = new JsonParser().parse(url_json);
        JsonObject jsobject2 = parser2.getAsJsonObject();
        //TODO: Search all hits
        if(jsobject2.get("total_hits").getAsInt() > 0){
            JsonArray hitArray = jsobject2.get("hits").getAsJsonArray();
            for (int i = 0; i < hitArray.size(); i++){
                JsonObject item = hitArray.get(i).getAsJsonObject().get("fields").getAsJsonObject();
                System.out.println(item);
                String name = "";
                double calorie = -1;
                double fat = -1;
                double cholesterol = -1;
                double sugar = -1;
                double carbs = -1;
                double sodium = -1;
                double fiber = -1;
                double protein = -1;
                double size = 1;
                String unit = "ea";


                if (!item.get("item_name").isJsonNull()) {
                    name = item.get("item_name").getAsString();
                }
                if (!item.get("nf_calories").isJsonNull()) {
                    calorie = Double.parseDouble(item.get("nf_calories").getAsString());
                }
                if (!item.get("nf_total_fat").isJsonNull()) {
                    fat = Double.parseDouble(item.get("nf_total_fat").getAsString());
                }
                if (!item.get("nf_cholesterol").isJsonNull()) {
                    cholesterol = Double.parseDouble(item.get("nf_cholesterol").getAsString());
                }
                if (!item.get("nf_sugars").isJsonNull()) {
                    sugar = Double.parseDouble(item.get("nf_sugars").getAsString());
                }
                if (!item.get("nf_total_carbohydrate").isJsonNull()) {
                    carbs = Double.parseDouble(item.get("nf_total_carbohydrate").getAsString());
                }
                if (!item.get("nf_sodium").isJsonNull()) {
                    sodium = Double.parseDouble(item.get("nf_sodium").getAsString());
                }
                if (!item.get("nf_dietary_fiber").isJsonNull()) {
                    fiber = Double.parseDouble(item.get("nf_dietary_fiber").getAsString());
                }
                if (!item.get("nf_protein").isJsonNull()) {
                    protein = Double.parseDouble(item.get("nf_protein").getAsString());
                }
                if (!item.get("nf_serving_size_qty").isJsonNull()) {
                    size = Double.parseDouble(item.get("nf_serving_size_qty").getAsString());
                }
                if (!item.get("nf_serving_size_unit").isJsonNull()) {
                    unit = item.get("nf_serving_size_unit").getAsString();
                }

                System.out.println(name + " " + calorie + " " + fat + " " +cholesterol + " " +
                        sugar + " " +carbs + " " + sodium + " " + fiber + " " +size + " " + unit);
                Menuitems.add(new MenuItem(name,calorie, fat, protein,
                        cholesterol, sugar, carbs, sodium, fiber, size, unit ));

            }

        }



    }

    public ArrayList<MenuItem> search(){

        String businessID = searchID();
        if (!businessID.equals("")){
            searchMenu();
        }
        return Menuitems;
    }
}
