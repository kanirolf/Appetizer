package cs125.winter2017.uci.appetizer.food_diary;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class MockFoodDiary {
    private MockFoodDiary(){}

    public static final FoodDiary MOCK_DIARY;
    static {
        MOCK_DIARY = new FoodDiary(
                2000, 10, 10, 10, 10, 10, 10, 10
        );

        DateTime today = new DateTime();
        FoodDiaryDay day1 = new FoodDiaryDay(today);

        day1.addAll(Arrays.asList(
            new FoodDiaryEntry.Builder()
                    .setDate(today.withHourOfDay(9))
                    .setName("Eggs")
                    .setServings(2)
                    .setCalorie(90)
                    .setFat(7)
                    .setCholesterol(184)
                    .setSodium(95)
                    .setCarbs(0.4)
                    .setFiber(0)
                    .setSugar(0.6)
                    .setProtein(6)
                    .build(),
            new FoodDiaryEntry.Builder()
                    .setDate(today.withHourOfDay(2))
                    .setName("Ramen")
                    .setServings(1)
                    .setCalorie(188)
                    .setFat(7)
                    .setCholesterol(0)
                    .setCarbs(27)
                    .setFiber(1)
                    .setSugar(0.7)
                    .setProtein(4.5)
                    .setSodium(875)
                    .build()
        ));

        DateTime yesterday = today.minusDays(1);
        FoodDiaryDay day2 = new FoodDiaryDay(yesterday);

        DateTime evening = yesterday.withHourOfDay(18);
        day2.addAll(Arrays.asList(
            new FoodDiaryEntry.Builder()
                .setDate(evening)
                .setName("Hamburger")
                .setServings(1)
                .setCalorie(354)
                .setFat(17)
                .setCholesterol(56)
                .setSodium(497)
                .setCarbs(29)
                .setFiber(1.1)
                .setSugar(5)
                .setProtein(20)
                .build(),
            new FoodDiaryEntry.Builder()
                .setDate(evening)
                .setName("French Fries")
                .setServings(1)
                .setFat(17)
                .setCholesterol(0)
                .setSodium(246)
                .setCarbs(48)
                .setFiber(4.4)
                .setSugar(0.4)
                .setProtein(4)
                .build()
        ));

        MOCK_DIARY.put(today, day1);
        MOCK_DIARY.put(yesterday, day2);
    }
}
