package cs125.winter2017.uci.appetizer.Search;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Daryl Cui on 3/18/2017.
 */

public class SearchMenu {

    private String restaurantName;
    private String ID;
    private String AppID = "24e35392";
    private String AppKey = "b31b775b1586d5cef18054eadd23b48e";
    private String AppID2 = "463febfe";
    private String AppKey2 = "a60ae69ae343086a86778e86f3fb9851";

    public SearchMenu(String name){
        restaurantName = name;
    }

    public String getUrl(){
        String url = "https://api.nutritionix.com/v1_1/brand/search?query=";
        url = url + restaurantName.replaceAll(" ","+");
        url = url + "&min_score=1&appId=";
        url = url + AppID2;
        url = url + "&appKey=";
        url = url + AppKey2;
        return url;
    }

    private String get_places(String url){
        try {
            URL con = new URL(url);
            HttpURLConnection connect = (HttpURLConnection) con.openConnection();
            InputStreamReader in = new InputStreamReader(connect.getInputStream());
            String line = null;
            StringBuilder sb = new StringBuilder();
            char[] buff = new char[1024];
            int read;
            while ((read = in.read(buff)) != -1) {
                sb.append(buff,0,read);
            }

            String result = sb.toString();

            return(result);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";


    }

    public String search(){
        String url = getUrl();
        String results = get_places(url);
//        String results  =
        return results;
    }
}
