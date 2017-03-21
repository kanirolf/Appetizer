package cs125.winter2017.uci.appetizer.food_diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import cs125.winter2017.uci.appetizer.R;
import cs125.winter2017.uci.appetizer.Search.SearchActivity;
import cs125.winter2017.uci.appetizer.daily_targets.DailyTargetActivity;
import cs125.winter2017.uci.appetizer.daily_targets.DailyTargets;
import cs125.winter2017.uci.appetizer.diet.DietaryRestrictionActivity;
import cs125.winter2017.uci.appetizer.first_time.FirstTimeActivity;

public class DiaryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DiaryDayFragment.OnDiaryDayEditListener {

    private static final int NEW_ENTRY = 0;
    private static final int EDIT_ENTRY = 1;

    private CardView diaryAddEntry;
    private LinearLayout diaryFeed;

    private TextView diaryFeedEmpty;
    private TextView diaryOverviewNutrientValue;
    private TextView diaryOverviewNutrientTarget;
    private TextView diaryOverviewNutrientUnits;

    private FoodDiaryEntry entryToEdit;

    private FoodDiaryDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("cs125.winter2017.uci.appetizer", 0);
        if (preferences.getBoolean("FIRST_LAUNCH", true)){
            Intent firstLaunchIntent = new Intent(this, FirstTimeActivity.class);
            startActivity(firstLaunchIntent);
            finish();
            return;
        }

        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_diary);

        dbHelper = new FoodDiaryDBHelper(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        diaryAddEntry = (CardView) findViewById(R.id.diary_add_entry);
        diaryAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addEntryIntent = new Intent(DiaryActivity.this, DiaryEntryActivity.class);
                addEntryIntent.putExtra(DiaryEntryActivity.EDITING_ENTRY, false);
                startActivityForResult(addEntryIntent, NEW_ENTRY);
            }
        });

        diaryOverviewNutrientValue = (TextView) findViewById(R.id.diary_overview_nutrient_value);
        diaryOverviewNutrientTarget = (TextView) findViewById(R.id.diary_overview_nutrient_target);
        diaryOverviewNutrientUnits = (TextView) findViewById(R.id.diary_overview_nutrient_units);

        diaryFeedEmpty = (TextView) findViewById(R.id.diary_feed_empty);
        diaryFeed = (LinearLayout) findViewById(R.id.diary_feed);
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateDiaryOverview();
        updateFoodDiaryList();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId())
        {
            case R.id.nav_search:
                Intent restaurantSearchIntent = new Intent(this, SearchActivity.class);
                startActivity(restaurantSearchIntent);
                break;
            case R.id.nav_daily_targets:
                Intent dailyGoalsIntent = new Intent(this, DailyTargetActivity.class);
                startActivity(dailyGoalsIntent);
                break;
            case R.id.nav_dietary_restriction:
                Intent dietaryRestrictionsIntent = new Intent(this, DietaryRestrictionActivity.class);
                startActivity(dietaryRestrictionsIntent );
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDiaryDayEdit(FoodDiaryDay day, FoodDiaryEntry entry) {
        entryToEdit = entry;

        Intent editIntent = new Intent(this, DiaryEntryActivity.class);
        editIntent.putExtra(DiaryEntryActivity.ENTRY, entryToEdit);
        editIntent.putExtra(DiaryEntryActivity.EDITING_ENTRY, true);

        startActivityForResult(editIntent, EDIT_ENTRY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode == RESULT_CANCELED)
            return;

        boolean delete = data.getBooleanExtra("DELETE", false);
        FoodDiaryEntry entry = data.getParcelableExtra(DiaryEntryActivity.ENTRY);

        switch (requestCode) {
            case NEW_ENTRY:
                FoodDiary.getInstance().addEntry(dbHelper, entry);
                break;
            case EDIT_ENTRY:
                if (entryToEdit != null) {
                    if (delete)
                        FoodDiary.getInstance().removeEntry(dbHelper, entryToEdit);
                    else {
                        entryToEdit.setName(entry.getName());
                        entryToEdit.setCalorie(entry.getCalorie());
                        entryToEdit.setFat(entry.getFat());
                        entryToEdit.setCarbs(entry.getCarbs());
                        entryToEdit.setFiber(entry.getFiber());
                        entryToEdit.setCholesterol(entry.getCholesterol());
                        entryToEdit.setProtein(entry.getProtein());
                        entryToEdit.setSodium(entry.getSodium());
                        entryToEdit.setSugar(entry.getSugar());

                        FoodDiary.getInstance().editEntry(dbHelper, entryToEdit);
                    }
                    entryToEdit = null;
                }
                break;
        }
    }

    // TODO: add the name of the nutrient you are tracking to the card
    private void updateDiaryOverview(){
        FoodDiaryDay todaysNutrients = FoodDiary.getInstance().getTodaysEntries(dbHelper);
        diaryOverviewNutrientValue.setText(
                String.format(Locale.getDefault(), "%d", (int) todaysNutrients.getCalorie()));

        diaryOverviewNutrientTarget.setText(
                String.format(Locale.getDefault(), "%d",
                        (int) DailyTargets.loadFromContext(this).getCalorie()));
        diaryOverviewNutrientUnits.setText(getString(R.string.calorie_units));

    }

    private void updateFoodDiaryList(){

        diaryFeed.removeAllViews();

        FoodDiary foodDiary = FoodDiary.getInstance();
        if (foodDiary.numEntries() == 0){
            diaryFeedEmpty.setVisibility(View.VISIBLE);
            return;
        }

        diaryFeedEmpty.setVisibility(View.GONE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DiaryDayFragment dayFragment;

        for (FoodDiaryDay foodDiaryDay : foodDiary.getDaysBeforeNow(dbHelper, 5)){
            if (foodDiaryDay.entries.isEmpty())
                continue;

            dayFragment = DiaryDayFragment.newInstance(foodDiaryDay);
            dayFragment.setEditListener(this);
            transaction.add(R.id.diary_feed, dayFragment);
        }

        transaction.commit();
    }

}
