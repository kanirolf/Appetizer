package cs125.winter2017.uci.appetizer.daily_targets;

import android.content.Context;
import android.content.SharedPreferences;

import cs125.winter2017.uci.appetizer.nutrients.NutrientFactHolder;

public class DailyTargets implements NutrientFactHolder {

    private static final String PREF_FILE = "cs125.winter2017.uci.appetizer.DAILY_TARGETS";

    public static DailyTargets loadFromContext(Context context){
        return new DailyTargets(context);
    }

    private final SharedPreferences dailyTargetPrefs;

    private DailyTargets(Context context){
        this.dailyTargetPrefs = context.getSharedPreferences(PREF_FILE, 0);
    }

    @Override
    public double getCalorie() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("calorie", 0));
    }

    public void setCalorie(double calorie) {
        dailyTargetPrefs.edit() .putLong("calorie", Double.doubleToRawLongBits(calorie)).apply();
    }

    @Override
    public double getFat() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("fat", 0));
    }

    public void setFat(double fat) {
        dailyTargetPrefs.edit().putLong("fat", Double.doubleToRawLongBits(fat)).apply();
    }

    @Override
    public double getProtein() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("protein", 0));
    }

    public void setProtein(double protein) {
        dailyTargetPrefs.edit().putLong("protein", Double.doubleToRawLongBits(protein)).apply();
    }

    @Override
    public double getCholesterol() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("cholesterol", 0));
    }

    public void setCholesterol(double cholesterol) {
        dailyTargetPrefs.edit().putLong("cholesterol",
                Double.doubleToRawLongBits(cholesterol)).apply();
    }

    @Override
    public double getSugar() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("sugar", 0));
    }

    public void setSugar(double sugar) {
        dailyTargetPrefs.edit().putLong("sugar", Double.doubleToRawLongBits(sugar)).apply();
    }

    @Override
    public double getCarbs() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("carbohydrates", 0));
    }

    public void setCarbs(double carbs) {
        dailyTargetPrefs.edit().putLong("carbohydrates", Double.doubleToRawLongBits(carbs)).apply();
    }

    @Override
    public double getSodium() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("sodium", 0));
    }

    public void setSodium(double sodium) {
        dailyTargetPrefs.edit().putLong("sodium", Double.doubleToRawLongBits(sodium)).apply();
    }

    @Override
    public double getFiber() {
        return Double.longBitsToDouble(dailyTargetPrefs.getLong("fiber", 0));
    }

    public void setFiber(double fiber) {
        dailyTargetPrefs.edit().putLong("fiber", Double.doubleToRawLongBits(fiber)).apply();
    }
}
