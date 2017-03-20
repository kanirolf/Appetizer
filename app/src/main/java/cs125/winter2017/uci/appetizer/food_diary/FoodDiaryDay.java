package cs125.winter2017.uci.appetizer.food_diary;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.Arrays;
import java.util.TreeSet;

import cs125.winter2017.uci.appetizer.nutrients.NutrientFactHolder;

public class FoodDiaryDay implements NutrientFactHolder, Parcelable {

    public static final Creator<FoodDiaryDay> CREATOR = new Creator<FoodDiaryDay>() {
        @Override
        public FoodDiaryDay createFromParcel(Parcel in) {
            return new FoodDiaryDay(in);
        }

        @Override
        public FoodDiaryDay[] newArray(int size) {
            return new FoodDiaryDay[size];
        }
    };

    public static final DateTimeFormatter HUMAN_READABLE_FORMATTER = new DateTimeFormatterBuilder()
            .appendMonthOfYearText()
            .appendLiteral(' ')
            .appendDayOfMonth(2)
            .appendLiteral(", ")
            .appendYear(4, 4)
            .toFormatter();

    private final @NonNull DateTime Date;
    public final @NonNull TreeSet<FoodDiaryEntry> entries;

    public FoodDiaryDay(@NonNull DateTime date){
        Date = date;
        entries = new TreeSet<>();
    }

    protected FoodDiaryDay(Parcel in) {
        Date = (DateTime) in.readSerializable();
        entries = new TreeSet<FoodDiaryEntry>(Arrays.asList(
                (FoodDiaryEntry[])in.readParcelableArray(FoodDiaryEntry.class.getClassLoader())
        ));
    }

    public String getHumanReadableDate(){
        return HUMAN_READABLE_FORMATTER.print(Date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(Date);
        dest.writeParcelableArray(entries.toArray(new FoodDiaryEntry[]{}), flags);
    }

    @NonNull
    public DateTime getDate(){
        return Date;
    }

    @Override
    public double getCalorie() {
        double totalCalories = 0;
        for (FoodDiaryEntry entry : entries)
            totalCalories += entry.getCalorie();
        return totalCalories;
    }

    @Override
    public double getFat() {
        double totalFat = 0;
        for (FoodDiaryEntry entry : entries)
            totalFat += entry.getFat();
        return totalFat;
    }

    @Override
    public double getProtein() {
        double totalProtein = 0;
        for (FoodDiaryEntry entry : entries)
            totalProtein += entry.getProtein();
        return totalProtein;
    }

    @Override
    public double getCholesterol() {
        double totalCholesterol = 0;
        for (FoodDiaryEntry entry : entries)
            totalCholesterol += entry.getCholesterol();
        return totalCholesterol;
    }

    @Override
    public double getSugar() {
        double totalSugar = 0;
        for (FoodDiaryEntry entry : entries)
            totalSugar += entry.getSugar();
        return totalSugar;
    }

    @Override
    public double getCarbs() {
        double totalCarbs = 0;
        for (FoodDiaryEntry entry : entries)
            totalCarbs += entry.getCarbs();
        return totalCarbs;
    }

    @Override
    public double getSodium() {
        double totalSodium = 0;
        for (FoodDiaryEntry entry : entries)
            totalSodium += entry.getSodium();
        return totalSodium;
    }

    @Override
    public double getFiber() {
        int totalFiber = 0;
        for (FoodDiaryEntry entry : entries)
            totalFiber += entry.getFiber();
        return totalFiber;
    }

    public void add(FoodDiaryEntry entry){
        entries.add(entry);
    }

    public void remove(FoodDiaryEntry entry){
        entries.remove(entry);
    }

}
