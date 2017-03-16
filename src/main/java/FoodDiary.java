import java.util.ArrayList;
import java.util.HashMap;

public class FoodDiary
{
	private int Calorie;
	private int Fat;
	private int Protein;
	private int Cholesterol;
	private int Sugar;
	private int Carbs;
	private int Sodium;
	private int Fiber;
	private int currentCalorie;
	private int currentFat;
	private int currentProtein;
	private int currentCholesterol;
	private int currentSugar;
	private int currentCarbs;
	private int currentSodium;
	private int currentFiber;
	private HashMap<String,ArrayList<Entry>> Entries;
	
	public FoodDiary(int Calorie,int Fat,int Protein,int Cholesterol,int Sugar,int Carbs,int Sodium,int Fiber){
		setCalorie(Calorie);
		setFat(Fat);
		setProtein(Protein);
		setCholesterol(Cholesterol);
		setSugar(Sugar);
		setCarbs(Carbs);
		setSodium(Sodium);
		setFiber(Fiber);
		Entries = new HashMap<String,ArrayList<Entry>>();
	}
	
	public void Enter(String name,int serving,int cal,int fat,int protein,int cholesterol,int sugar,int carbs,int sodium,int fiber){
		Entry entry = new Entry(name,serving,cal,fat,protein,cholesterol,sugar,carbs,sodium,fiber);
		if(!Entries.containsKey(entry.returnDate()))
			Entries.put(entry.returnDate(), new ArrayList<Entry>());
		Entries.get(entry.returnDate()).add(entry);

		setCurrentCalorie(Calorie - serving*cal);
		setCurrentFat(Fat - serving*fat);
		setCurrentProtein(Protein - serving*protein);
		setCurrentCholesterol(Cholesterol - serving*cholesterol);
		setCurrentSugar(Sugar - serving*sugar);
		setCurrentCarbs(Carbs - serving*carbs);
		setCurrentSodium(Sodium - serving*sodium);
		setCurrentFiber(Fiber - serving*fiber);
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



	public int getCurrentCalorie()
	{
		return currentCalorie;
	}



	public void setCurrentCalorie(int currentCalorie)
	{
		this.currentCalorie = currentCalorie;
	}



	public int getCurrentFat()
	{
		return currentFat;
	}



	public void setCurrentFat(int currentFat)
	{
		this.currentFat = currentFat;
	}



	public int getCurrentProtein()
	{
		return currentProtein;
	}



	public void setCurrentProtein(int currentProtein)
	{
		this.currentProtein = currentProtein;
	}



	public int getCurrentCholesterol()
	{
		return currentCholesterol;
	}



	public void setCurrentCholesterol(int currentCholesterol)
	{
		this.currentCholesterol = currentCholesterol;
	}



	public int getCurrentSugar()
	{
		return currentSugar;
	}



	public void setCurrentSugar(int currentSugar)
	{
		this.currentSugar = currentSugar;
	}



	public int getCurrentCarbs()
	{
		return currentCarbs;
	}



	public void setCurrentCarbs(int currentCarbs)
	{
		this.currentCarbs = currentCarbs;
	}



	public int getCurrentSodium()
	{
		return currentSodium;
	}



	public void setCurrentSodium(int currentSodium)
	{
		this.currentSodium = currentSodium;
	}



	public int getCurrentFiber()
	{
		return currentFiber;
	}



	public void setCurrentFiber(int currentFiber)
	{
		this.currentFiber = currentFiber;
	}
	
}
