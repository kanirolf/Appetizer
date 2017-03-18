package cs125.winter2017.uci.appetizer.food_diary;

import android.support.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Comparator;
import java.util.TreeMap;

public class FoodDiary extends TreeMap<DateTime, FoodDiaryDay> implements NutrientFactHolder {

    public static Comparator<DateTime> SAME_DAY_COMPARATOR = new Comparator<DateTime>() {
        @Override
        public int compare(DateTime first, DateTime second) {
            return Days.daysBetween(first.toLocalDate(), second.toLocalDate()).getDays();
        }
    };

	private double Calorie;
	private double Fat;
	private double Protein;
	private double Cholesterol;
	private double Sugar;
	private double Carbs;
	private double Sodium;
	private double Fiber;

	public FoodDiary(int Calorie, int Fat, int Protein, int Cholesterol, int Sugar, int Carbs,
                     int Sodium, int Fiber){
        super(SAME_DAY_COMPARATOR);

        setCalorie(Calorie);
		setFat(Fat);
		setProtein(Protein);
		setCholesterol(Cholesterol);
		setSugar(Sugar);
		setCarbs(Carbs);
		setSodium(Sodium);
		setFiber(Fiber);
	}

	@Nullable
	public FoodDiaryDay getTodaysEntries(){
        return this.get(new DateTime());
    }

    @Override
    public double getCalorie() {
        return Calorie;
    }

    public void setCalorie(double calorie) {
        Calorie = calorie;
    }

    @Override
    public double getFat() {
        return Fat;
    }

    public void setFat(double fat) {
        Fat = fat;
    }

    @Override
    public double getProtein() {
        return Protein;
    }

    public void setProtein(double protein) {
        Protein = protein;
    }

    @Override
    public double getCholesterol() {
        return Cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        Cholesterol = cholesterol;
    }

    @Override
    public double getSugar() {
        return Sugar;
    }

    public void setSugar(double sugar) {
        Sugar = sugar;
    }

    @Override
    public double getCarbs() {
        return Carbs;
    }

    public void setCarbs(double carbs) {
        Carbs = carbs;
    }

    @Override
    public double getSodium() {
        return Sodium;
    }

    public void setSodium(double sodium) {
        Sodium = sodium;
    }

    @Override
    public double getFiber() {
        return Fiber;
    }

    public void setFiber(double fiber) {
        Fiber = fiber;
    }
}
