import java.util.Calendar;

public class Entry
{
	private String Name;
	private int Calorie;
	private int Fat;
	private int Protein;
	private int Cholesterol;
	private int Sugar;
	private int Carbs;
	private int Sodium;
	private int Fiber;
	private Calendar date;
	
	public Entry(String Name,int serving,int cal,int fat,int protein,int cholesterol,int sugar,int carbs,int sodium,int fiber){
		setName(Name);
		setCalorie(serving*cal);
		setFat(serving*fat);
		setProtein(serving*protein);
		setCholesterol(serving*cholesterol);
		setSugar(serving*sugar);
		setCarbs(serving*carbs);
		setSodium(serving*sodium);
		setFiber(serving*Fiber);
		date = Calendar.getInstance();
	}
	
	public String returnDate(){
		return Integer.toString(date.get(Calendar.YEAR)) + "-" + Integer.toString(date.get(Calendar.MONTH)) + "-" + Integer.toString(date.get(Calendar.DAY_OF_MONTH));
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public int getCalorie()
	{
		return Calorie;
	}

	public void setCalorie(int calorie)
	{
		Calorie = calorie;
	}

	public int getFat()
	{
		return Fat;
	}

	public void setFat(int fat)
	{
		Fat = fat;
	}

	public int getProtein()
	{
		return Protein;
	}

	public void setProtein(int protein)
	{
		Protein = protein;
	}

	public int getCholesterol()
	{
		return Cholesterol;
	}

	public void setCholesterol(int cholesterol)
	{
		Cholesterol = cholesterol;
	}

	public int getSugar()
	{
		return Sugar;
	}

	public void setSugar(int sugar)
	{
		Sugar = sugar;
	}

	public int getCarbs()
	{
		return Carbs;
	}

	public void setCarbs(int carbs)
	{
		Carbs = carbs;
	}

	public int getSodium()
	{
		return Sodium;
	}

	public void setSodium(int sodium)
	{
		Sodium = sodium;
	}

	public int getFiber()
	{
		return Fiber;
	}

	public void setFiber(int fiber)
	{
		Fiber = fiber;
	}

	public Calendar getDate()
	{
		return date;
	}

	public void setDate(Calendar date)
	{
		this.date = date;
	}
}
