package cs125.winter2017.uci.appetizer.diet;

public enum DietaryRestriction {
    NUT_ALLERGY,
    LACTOSE_INTOLERANT,
    VEGETARIAN,
    VEGAN;

    public String getHumanReadableString(){
        String unreadable = name().replace('_', ' ').toLowerCase();
        return unreadable.substring(0, 1).toUpperCase() + unreadable.substring(1);
    }
}
