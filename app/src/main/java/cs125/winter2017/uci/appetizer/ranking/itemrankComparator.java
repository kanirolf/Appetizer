package cs125.winter2017.uci.appetizer.ranking;



import java.util.Comparator;
import java.util.HashMap;

import cs125.winter2017.uci.appetizer.Search.MenuItem;

/**
 * Created by Andrew on 3/19/2017.
 */

public class itemrankComparator implements Comparator<MenuItem> {



    public int compare( MenuItem a, MenuItem b){
        return a.getRank() < b.getRank() ? 1 : a.getRank() == b.getRank() ? 0: -1;
    }

}
