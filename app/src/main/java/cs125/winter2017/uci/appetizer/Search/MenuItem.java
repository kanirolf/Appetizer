package cs125.winter2017.uci.appetizer.Search;

/**
 * Created by Daryl Cui on 3/19/2017.
 */

public class MenuItem {
    private String Name;
    private double Calorie;
    private double Fat;
    private double Protein;
    private double Cholesterol;
    private double Sugar;
    private double Carbs;
    private double Sodium;
    private double Fiber;
    private double Rank;
    private double Serving_size;
    private String Serving_unit;

    public MenuItem(String name,double calorie,double fat,double protein,double cholesterol,double sugar,double carbs,double sodium,double fiber, double size, String unit){
        setName(name);
        setCalorie(calorie);
        setFat(fat);
        setProtein(protein);
        setCholesterol(cholesterol);
        setSugar(sugar);
        setCarbs(carbs);
        setSodium(sodium);
        setFiber(fiber);
        Serving_size = size;
        Serving_unit = unit;

    }

    public String toString(){
        return "Name :" + Name + "   Calories: " + Calorie + "    Rank: " + Rank + "\n";
    }

    public void setRank(double rank){ this.Rank = rank;}

    public void setName(String name) {this.Name = name;}

    public void setCalorie(double calorie) {
        this.Calorie = calorie;
    }

    public void setFat(double fat) {
        this.Fat = fat;
    }

    public void setProtein(double protein) {
        this.Protein = protein;
    }

    public void setCholesterol(double cholesterol) {
        this.Cholesterol = cholesterol;
    }

    public void setSugar(double sugar) {
        this.Sugar = sugar;
    }

    public void setCarbs(double carbs){
        this.Carbs = carbs;
    }

    public void setSodium(double sodium){
        this.Sodium = sodium;
    }

    public void setFiber(double fiber){
        this.Fiber = fiber;
    }

    public String getName(){
        return Name;
    }

    public double getRank(){return Rank;}

    public double getCalorie(){
        return Calorie;
    }

    public double getFat(){
        return Fat;
    }

    public double getProtein() {
        return Protein;
    }

    public double getCholesterol(){
        return Cholesterol;
    }

    public double getSugar(){
        return Sugar;
    }

    public double getCarbs(){
        return Carbs;
    }

    public double getSodium(){
        return Sodium;
    }

    public double getFibers() {
        return Fiber;
    }

    public double getServing_size() {return Serving_size;}

    public String getServing_unit(){return Serving_unit;}

}
