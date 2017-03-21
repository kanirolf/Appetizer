package cs125.winter2017.uci.appetizer.diet;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class DietaryRestrictions {

    private static final String PREF_FILE = "cs125.winter2017.uci.appetizer.DIETARY_RESTRICTIONS";

    public static DietaryRestrictions loadFromContext(Context context){
        return new DietaryRestrictions(context);
    }

    private final SharedPreferences prefs;
    private final Set<DietaryRestriction> restrictions;

    private DietaryRestrictions(Context context){
        prefs = context.getSharedPreferences(PREF_FILE, 0);
        restrictions = new HashSet<DietaryRestriction>();

        for (String prefAsString : prefs.getStringSet("RESTRICTIONS", new HashSet<String>()))
            addRestriction(DietaryRestriction.valueOf(prefAsString));
    }

    public void addRestriction(DietaryRestriction dietaryRestriction){
        restrictions.add(dietaryRestriction);
    }

    public boolean hasRestriction(String restriction){
        return hasRestriction(DietaryRestriction.valueOf(restriction));
    }

    public boolean hasRestriction(DietaryRestriction restriction){
        return restrictions.contains(restriction);
    }

    public void removeRestriction(String restriction){
        removeRestriction(DietaryRestriction.valueOf(restriction));
    }

    public void removeRestriction(DietaryRestriction restriction){
        restrictions.remove(restriction);
    }

    public void commit(){
        Set<String> prefsAsString = new HashSet<>();
        for (DietaryRestriction restriction : restrictions)
            prefsAsString.add(restriction.name());

        prefs.edit().putStringSet("RESTRICTIONS", prefsAsString).apply();
    }

}
