package cs125.winter2017.uci.appetizer.food_diary;

import android.support.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Calendar;
import java.util.TreeSet;

public class FoodDiary extends TreeSet<FoodDiaryDay> implements NutrientFactHolder {

	private int Calorie;
	private int Fat;
	private int Protein;
	private int Cholesterol;
	private int Sugar;
	private int Carbs;
	private int Sodium;
	private int Fiber;

	public FoodDiary(int Calorie, int Fat, int Protein, int Cholesterol, int Sugar, int Carbs,
                     int Sodium,int Fiber){
        super(FoodDiaryDay.FOOD_DIARY_DAY_COMPARATOR);

        setCalorie(Calorie);
		setFat(Fat);
		setProtein(Protein);
		setCholesterol(Cholesterol);
		setSugar(Sugar);
		setCarbs(Carbs);
		setSodium(Sodium);
		setFiber(Fiber);
	}

	@Override
	public int getCalorie()
	{
		return Calorie;
	}

	public void setCalorie(int calorie)
	{
		Calorie = calorie;
	}

    @Override
	public int getFat()
	{
		return Fat;
	}

	public void setFat(int fat)
	{
		Fat = fat;
	}

    @Override
	public int getProtein()
	{
		return Protein;
	}

	public void setProtein(int protein)
	{
		Protein = protein;
	}

    @Override
	public int getCholesterol()
	{
		return Cholesterol;
	}

	public void setCholesterol(int cholesterol)
	{
		Cholesterol = cholesterol;
	}

    @Override
	public int getSugar()
	{
		return Sugar;
	}

	public void setSugar(int sugar)
	{
		Sugar = sugar;
	}

    @Override
	public int getCarbs()
	{
		return Carbs;
	}

	public void setCarbs(int carbs)
	{
		Carbs = carbs;
	}

    @Override
	public int getSodium()
	{
		return Sodium;
	}

	public void setSodium(int sodium)
	{
		Sodium = sodium;
	}

    @Override
	public int getFiber()
	{
		return Fiber;
	}

	public void setFiber(int fiber)
	{
		Fiber = fiber;
	}

	@Nullable
	public FoodDiaryDay getTodaysEntries(){
        DateTime today = new DateTime();
        FoodDiaryDay mostRecentDiaryDay = first();

        int daysBetween = Days.daysBetween(
                today.toLocalDate(), mostRecentDiaryDay.getDate().toLocalDate()).getDays();
        return daysBetween == 0 ? mostRecentDiaryDay : null;
    }

}
