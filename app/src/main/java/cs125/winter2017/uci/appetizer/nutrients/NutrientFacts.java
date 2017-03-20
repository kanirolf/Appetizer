package cs125.winter2017.uci.appetizer.nutrients;

import android.os.Parcel;
import android.os.Parcelable;

public class NutrientFacts implements NutrientFactHolder, Parcelable {

    public static final Parcelable.Creator<NutrientFacts> CREATOR =
        new Parcelable.Creator<NutrientFacts>() {
            @Override
            public NutrientFacts createFromParcel(Parcel source) {
                return new NutrientFacts.Builder()
                        .setCalorie(source.readDouble())
                        .setFat(source.readDouble())
                        .setProtein(source.readDouble())
                        .setCholesterol(source.readDouble())
                        .setSugar(source.readDouble())
                        .setCarbs(source.readDouble())
                        .setSodium(source.readDouble())
                        .setFiber(source.readDouble())
                        .build();
            }

            @Override
            public NutrientFacts[] newArray(int size) {
                return new NutrientFacts[size];
            }
        };


    private double calorie;
    private double fat;
    private double protein;
    private double cholesterol;
    private double sugar;
    private double carbs;
    private double sodium;
    private double fiber;

    private NutrientFacts(Builder builder){
        this.calorie = builder.calorie;
        this.fat = builder.fat;
        this.protein = builder.protein;
        this.cholesterol = builder.cholesterol;
        this.sugar = builder.sugar;
        this.carbs = builder.carbs;
        this.sodium = builder.sodium;
        this.fiber = builder.fiber;
    }

    public static class Builder {

        private double calorie;
        private double fat;
        private double protein;
        private double cholesterol;
        private double sugar;
        private double carbs;
        private double sodium;
        private double fiber;

        public Builder(){
            calorie = 0;
            fat = 0;
            protein = 0;
            cholesterol= 0;
            sugar= 0;
            carbs = 0;
            sodium = 0;
            fiber = 0;
        }

        public Builder setCalorie(double calorie){
            this.calorie = calorie;
            return this;
        }

        public Builder setFat(double fat){
            this.fat = fat;
            return this;
        }

        public Builder setProtein(double protein){
            this.protein = protein;
            return this;
        }

        public Builder setCholesterol(double cholesterol){
            this.cholesterol = cholesterol;
            return this;
        }

        public Builder setSugar(double sugar){
            this.sugar = sugar;
            return this;
        }

        public Builder setCarbs(double carbs){
            this.carbs = carbs;
            return this;
        }

        public Builder setSodium(double sodium){
            this.sodium = sodium;
            return this;
        }

        public Builder setFiber(double fiber){
            this.fiber = fiber;
            return this;
        }

        public NutrientFacts build (){
            return new NutrientFacts(this);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(calorie);
        dest.writeDouble(fat);
        dest.writeDouble(protein);
        dest.writeDouble(cholesterol);
        dest.writeDouble(sugar);
        dest.writeDouble(carbs);
        dest.writeDouble(sodium);
        dest.writeDouble(fiber);
    }

    @Override
    public double getCalorie() {
        return calorie;
    }

    @Override
    public double getFat() {
        return fat;
    }

    @Override
    public double getProtein() {
        return protein;
    }

    @Override
    public double getCholesterol() {
        return cholesterol;
    }

    @Override
    public double getSugar() {
        return sugar;
    }

    @Override
    public double getCarbs() {
        return carbs;
    }

    @Override
    public double getSodium() {
        return sodium;
    }

    @Override
    public double getFiber() {
        return fiber;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }
}
