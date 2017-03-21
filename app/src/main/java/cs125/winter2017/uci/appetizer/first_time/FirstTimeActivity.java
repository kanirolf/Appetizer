package cs125.winter2017.uci.appetizer.first_time;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs125.winter2017.uci.appetizer.R;
import cs125.winter2017.uci.appetizer.daily_targets.DailyTargetFragment;
import cs125.winter2017.uci.appetizer.diet.DietaryRestrictionFragment;
import cs125.winter2017.uci.appetizer.food_diary.DiaryActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FirstTimeActivity extends AppCompatActivity implements View.OnClickListener {

    DietaryRestrictionFragment restrictionFragment;
    DailyTargetFragment targetFragment;

    View nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        setContentView(R.layout.activity_first_time);

        FragmentManager fragmentManager = getSupportFragmentManager();

        restrictionFragment = (DietaryRestrictionFragment)
                fragmentManager.findFragmentById(R.id.first_time_diet_restrictions);
        targetFragment = (DailyTargetFragment)
                fragmentManager.findFragmentById(R.id.first_time_daily_targets);

        nextButton = findViewById(R.id.first_time_submit);
        nextButton.setOnClickListener(this);

        if (getCurrentFocus() != null)
            getCurrentFocus().clearFocus();
    }

    @Override
    public void onClick(View v) {
        restrictionFragment.commit();
        targetFragment.commit();

        getSharedPreferences("cs125.winter2017.uci.appetizer", 0)
                .edit().putBoolean("FIRST_LAUNCH", false).apply();

        Intent diaryActivityIntent = new Intent(this, DiaryActivity.class);
        startActivity(diaryActivityIntent);
        finish();
    }
}
