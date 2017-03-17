package cs125.winter2017.uci.appetizer.food_diary;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

public class FoodDiaryEntry implements NutrientFactHolder {

    private DateTime Date;
	private String Name;

    private int Servings;
	private int Calorie;
	private int Fat;
	private int Protein;
	private int Cholesterol;
	private int Sugar;
	private int Carbs;
	private int Sodium;
	private int Fiber;

    public FoodDiaryEntry(DateTime date, String name, int Servings, int Calorie, int Fat,
                          int Protein, int Cholesterol, int Sugar, int Carbs, int Sodium,
                          int Fiber) {

        Date = date;
        Name = name;

        setServings(Servings);
        setCalorie(Calorie);
        setFat(Fat);
        setProtein(Protein);
        setCholesterol(Cholesterol);
        setSugar(Sugar);
        setCarbs(Carbs);
        setSodium(Sodium);
        setFiber(Fiber);
    }
	
	public String returnDate(){
		return Integer.toString(Date.get(DateTimeFieldType.year())) + "-" +
                Integer.toString(Date.get(DateTimeFieldType.monthOfYear())) + "-" +
                Integer.toString(Date.get(DateTimeFieldType.dayOfMonth()));
	}

    public DateTime getDate()
    {
        return Date;
    }

	public String getName()
	{
		return Name;
	}

	public int getServings(){
        return Servings;
	}

	@Override
	public int getCalorie()
	{
		return Calorie;
	}

	@Override
	public int getFat()
	{
		return Fat;
	}

    @Override
	public int getProtein()
	{
		return Protein;
	}

    @Override
	public int getCholesterol()
	{
		return Cholesterol;
	}

    @Override
	public int getSugar()
	{
		return Sugar;
	}

    @Override
	public int getCarbs()
	{
		return Carbs;
	}

    @Override
	public int getSodium()
	{
		return Sodium;
	}

    @Override
	public int getFiber()
	{
		return Fiber;
	}

    public void setName(String name) {
        Name = name;
    }

    public void setServings(int servings) {
        Servings = servings;
    }

    public void setCalorie(int calorie){
        Calorie = calorie;
    }

    public void setFat(int fat){
        Fat = fat;
    }

    public void setProtein(int protein) {
        Protein = protein;
    }

    public void setCholesterol(int cholesterol) {
        Cholesterol = cholesterol;
    }

    public void setSugar(int sugar) {
        Sugar = sugar;
    }

    public void setCarbs(int carbs) {
        Carbs = carbs;
    }

    public void setSodium(int sodium) {
        Sodium = sodium;
    }

    public void setFiber(int fiber) {
        Fiber = fiber;
    }
}
