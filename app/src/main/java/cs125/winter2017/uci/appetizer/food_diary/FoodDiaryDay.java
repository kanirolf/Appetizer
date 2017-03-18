package cs125.winter2017.uci.appetizer.food_diary;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.Comparator;
import java.util.TreeSet;

public class FoodDiaryDay extends TreeSet<FoodDiaryEntry> implements NutrientFactHolder {

    public static final DateTimeFormatter HUMAN_READABLE_FORMATTER = new DateTimeFormatterBuilder()
            .appendMonthOfYearText()
            .appendLiteral(' ')
            .appendDayOfMonth(2)
            .appendLiteral(", ")
            .appendYear(4, 4)
            .toFormatter();

    private final @NonNull DateTime Date;

    public FoodDiaryDay(@NonNull DateTime date){
        Date = date;
    }

    public String getHumanReadableDate(){
        return HUMAN_READABLE_FORMATTER.print(Date);
    }

    @NonNull
    public DateTime getDate(){
        return Date;
    }

    @Override
    public double getCalorie() {
        double totalCalories = 0;
        for (FoodDiaryEntry entry : this)
            totalCalories += entry.getCalorie();
        return totalCalories;
    }

    @Override
    public double getFat() {
        double totalFat = 0;
        for (FoodDiaryEntry entry : this)
            totalFat += entry.getFat();
        return totalFat;
    }

    @Override
    public double getProtein() {
        double totalProtein = 0;
        for (FoodDiaryEntry entry : this)
            totalProtein += entry.getProtein();
        return totalProtein;
    }

    @Override
    public double getCholesterol() {
        double totalCholesterol = 0;
        for (FoodDiaryEntry entry : this)
            totalCholesterol += entry.getCholesterol();
        return totalCholesterol;
    }

    @Override
    public double getSugar() {
        double totalSugar = 0;
        for (FoodDiaryEntry entry : this)
            totalSugar += entry.getSugar();
        return totalSugar;
    }

    @Override
    public double getCarbs() {
        double totalCarbs = 0;
        for (FoodDiaryEntry entry : this)
            totalCarbs += entry.getCarbs();
        return totalCarbs;
    }

    @Override
    public double getSodium() {
        double totalSodium = 0;
        for (FoodDiaryEntry entry : this)
            totalSodium += entry.getSodium();
        return totalSodium;
    }

    @Override
    public double getFiber() {
        int totalFiber = 0;
        for (FoodDiaryEntry entry : this)
            totalFiber += entry.getFiber();
        return totalFiber;
    }
}
