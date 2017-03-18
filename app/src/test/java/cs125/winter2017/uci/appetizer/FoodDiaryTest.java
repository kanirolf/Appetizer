package cs125.winter2017.uci.appetizer;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import cs125.winter2017.uci.appetizer.food_diary.FoodDiary;
import cs125.winter2017.uci.appetizer.food_diary.FoodDiaryDay;

public class FoodDiaryTest {

    private FoodDiary testFoodDiary;

    @Before
    public void setup(){
        testFoodDiary = new FoodDiary(2000, 10, 10, 10, 10, 10, 10, 10);
    }

    @Test
    public void TestGetTodaysEntries(){
        final DateTime today = new DateTime();
        final FoodDiaryDay todaysEntries = new FoodDiaryDay(today);

        testFoodDiary.put(today, todaysEntries);

        Assert.assertEquals(todaysEntries, testFoodDiary.getTodaysEntries());
    }
}
