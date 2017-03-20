package cs125.winter2017.uci.appetizer.food_diary;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Seconds;

import cs125.winter2017.uci.appetizer.nutrients.NutrientFactHolder;
import cs125.winter2017.uci.appetizer.nutrients.NutrientFacts;

public class FoodDiaryEntry implements Comparable<FoodDiaryEntry>, NutrientFactHolder, Parcelable {

    public static final Parcelable.Creator<FoodDiaryEntry> CREATOR =
            new Parcelable.Creator<FoodDiaryEntry>() {

                @Override
                public FoodDiaryEntry createFromParcel(Parcel source) {
                    return new FoodDiaryEntry(source);
                }

                @Override
                public FoodDiaryEntry[] newArray(int size) {
                    return new FoodDiaryEntry[size];
                }
            };

    private final @NonNull DateTime date;
	private @NonNull String Name;

    private int Id;
    private double Servings;
	private NutrientFacts nutrientFacts;

    private FoodDiaryEntry(Builder builder){
        date = builder.Date;
        Name = builder.Name;

        Id = builder.Id;
        Servings = builder.Servings;
        nutrientFacts = builder.nutrientFacts;
    }

    private FoodDiaryEntry(Parcel parcel){
        date = (DateTime) parcel.readSerializable();
        Name = parcel.readString();

        Id = parcel.readInt();
        Servings = parcel.readDouble();
        nutrientFacts = parcel.readParcelable(NutrientFacts.class.getClassLoader());
    }

    @Override
    public int compareTo(@NonNull FoodDiaryEntry other) {
        int secondDiff = Seconds.secondsBetween(this.getDate(), other.getDate()).getSeconds();
        return secondDiff != 0 ? secondDiff : this.getName().compareTo(other.getName());
    }

    public static class Builder {
        private @NonNull DateTime Date;
        private @NonNull String Name;

        private int Id;
        private double Servings;
        private NutrientFacts.Builder nutrientFactsBuilder;
        private NutrientFacts nutrientFacts;

        public Builder(){
            Date = new DateTime();
            Name = "";

            Id = -1;
            Servings = 1;
            nutrientFactsBuilder = new NutrientFacts.Builder();
        }

        public Builder setId(int id) {
            Id = id;
            return this;
        }

        public Builder setDate(@NonNull DateTime date){
            Date = date;
            return this;
        }

        public Builder setName(@NonNull String name){
            Name = name;
            return this;
        }

        public Builder setServings(double servings) {
            Servings = servings;
            return this;
        }

        public Builder setCalorie(double calorie) {
            nutrientFactsBuilder.setCalorie(calorie);
            return this;
        }

        public Builder setFat(double fat) {
            nutrientFactsBuilder.setFat(fat);
            return this;
        }

        public Builder setProtein(double protein) {
            nutrientFactsBuilder.setProtein(protein);
            return this;
        }

        public Builder setCholesterol(double cholesterol) {
            nutrientFactsBuilder.setCholesterol(cholesterol);
            return this;
        }

        public Builder setSugar(double sugar) {
            nutrientFactsBuilder.setSugar(sugar);
            return this;
        }

        public Builder setCarbs(double carbs) {
            nutrientFactsBuilder.setCarbs(carbs);
            return this;
        }

        public Builder setSodium(double sodium) {
            nutrientFactsBuilder.setSodium(sodium);
            return this;
        }

        public Builder setFiber(double fiber) {
            nutrientFactsBuilder.setFiber(fiber);
            return this;
        }

        public FoodDiaryEntry build(){
            nutrientFacts = nutrientFactsBuilder.build();
            return new FoodDiaryEntry(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(date);
        dest.writeString(Name);
        dest.writeInt(Id);
        dest.writeDouble(Servings);
        dest.writeParcelable(nutrientFacts, flags);
    }
	
	public String returnDate(){
		return Integer.toString(date.get(DateTimeFieldType.year())) + "-" +
                Integer.toString(date.get(DateTimeFieldType.monthOfYear())) + "-" +
                Integer.toString(date.get(DateTimeFieldType.dayOfMonth()));
	}

	public int getId() { return Id; }

    public void setId(int id) { Id = id; }

    @NonNull
    public DateTime getDate() {
        return date;
    }

    @NonNull
    public String getName() {
        return Name;
    }

    public void setName(@NonNull String name) {
        Name = name;
    }

    public double getServings() {
        return Servings;
    }

    public void setServings(double servings) {
        Servings = servings;
    }

    @Override
    public double getCalorie() {
        return nutrientFacts.getCalorie();
    }

    public void setCalorie(double calorie) {
        nutrientFacts.setCalorie(calorie);
    }

    @Override
    public double getFat() {
        return nutrientFacts.getFat();
    }

    public void setFat(double fat) {
        nutrientFacts.setFat(fat);
    }

    @Override
    public double getProtein() {
        return nutrientFacts.getProtein();
    }

    public void setProtein(double protein) {
        nutrientFacts.setProtein(protein);
    }

    @Override
    public double getCholesterol() {
        return nutrientFacts.getCholesterol();
    }

    public void setCholesterol(double cholesterol) {
        nutrientFacts.setCholesterol(cholesterol);
    }

    @Override
    public double getSugar() {
        return nutrientFacts.getSugar();
    }

    public void setSugar(double sugar) {
        nutrientFacts.setSugar(sugar);
    }

    @Override
    public double getCarbs() {
        return nutrientFacts.getCarbs();
    }

    public void setCarbs(double carbs) {
        nutrientFacts.setCarbs(carbs);
    }

    @Override
    public double getSodium() {
        return nutrientFacts.getSodium();
    }

    public void setSodium(double sodium) {
        nutrientFacts.setSodium(sodium);
    }

    @Override
    public double getFiber() {
        return nutrientFacts.getFiber();
    }

    public void setFiber(double fiber) {
        nutrientFacts.setFiber(fiber);
    }

}
