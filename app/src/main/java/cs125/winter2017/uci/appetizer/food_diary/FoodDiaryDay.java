package cs125.winter2017.uci.appetizer.food_diary;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Comparator;
import java.util.TreeSet;

public class FoodDiaryDay extends TreeSet<FoodDiaryEntry> implements NutrientFactHolder {

    public static final Comparator<FoodDiaryDay> FOOD_DIARY_DAY_COMPARATOR =
        new Comparator<FoodDiaryDay>() {
            @Override
            public int compare(FoodDiaryDay entry1, FoodDiaryDay entry2) {
                return Days.daysBetween(
                        entry1.getDate().toLocalDate(), entry2.getDate().toLocalDate()
                ).getDays();
            }
        };

    private DateTime date;

    public DateTime getDate(){
        return date;
    }

    @Override
    public int getCalorie() {
        int totalCalories = 0;
        for (FoodDiaryEntry entry : this)
            totalCalories += entry.getCalorie();
        return totalCalories;
    }

    @Override
    public int getFat() {
        int totalFat = 0;
        for (FoodDiaryEntry entry : this)
            totalFat += entry.getFat();
        return totalFat;
    }

    @Override
    public int getProtein() {
        int totalProtein = 0;
        for (FoodDiaryEntry entry : this)
            totalProtein += entry.getProtein();
        return totalProtein;
    }

    @Override
    public int getCholesterol() {
        int totalCholesterol = 0;
        for (FoodDiaryEntry entry : this)
            totalCholesterol += entry.getCholesterol();
        return totalCholesterol;
    }

    @Override
    public int getSugar() {
        int totalSugar = 0;
        for (FoodDiaryEntry entry : this)
            totalSugar += entry.getSugar();
        return totalSugar;
    }

    @Override
    public int getCarbs() {
        int totalCarbs = 0;
        for (FoodDiaryEntry entry : this)
            totalCarbs += entry.getCarbs();
        return totalCarbs;
    }

    @Override
    public int getSodium() {
        int totalSodium = 0;
        for (FoodDiaryEntry entry : this)
            totalSodium += entry.getSodium();
        return totalSodium;
    }

    @Override
    public int getFiber() {
        int totalFiber = 0;
        for (FoodDiaryEntry entry : this)
            totalFiber += entry.getFiber();
        return totalFiber;
    }
}
