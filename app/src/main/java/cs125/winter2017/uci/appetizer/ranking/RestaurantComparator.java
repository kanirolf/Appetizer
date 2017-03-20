package cs125.winter2017.uci.appetizer.ranking;

import java.util.Comparator;

import cs125.winter2017.uci.appetizer.Search.Restaurant;

/**
 * Created by Andrew on 3/19/2017.
 */

public class RestaurantComparator implements Comparator<Restaurant> {

    public int compare( Restaurant a, Restaurant b){
        return a.getRank() < b.getRank() ? 1 : a.getRank() == b.getRank() ? 0: -1;

    }
}
