package cs125.winter2017.uci.appetizer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Locale;

import cs125.winter2017.uci.appetizer.food_diary.FoodDiary;
import cs125.winter2017.uci.appetizer.food_diary.FoodDiaryEntry;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout diaryFeed = (LinearLayout) findViewById(R.id.diary_feed);
        FoodDiary foodDiary = getFoodDiary();
        FoodDiaryAdapter foodDiaryAdapter = new FoodDiaryAdapter(this, 0,
                foodDiary.toArray(new FoodDiaryEntry[]{}));
        for (int i = 0; i < foodDiary.size(); i++)
            diaryFeed.addView(foodDiaryAdapter.getView(i, null, null));
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
            case R.id.nav_home:
                break;
            case R.id.nav_search:
                break;
            case R.id.nav_settings:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // TODO : actually fetch diary data
    private FoodDiary getFoodDiary(){
        return null;
    }

    private static class FoodDiaryAdapter extends ArrayAdapter<FoodDiaryEntry>{

        public FoodDiaryAdapter(@NonNull Context context, @LayoutRes int resource,
                                @NonNull FoodDiaryEntry[] objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent){
            FoodDiaryEntry currentEntry = getItem(position);
            TextView dateTextView;

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater)
                        getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.layout_diary_entry_summary, null);

                dateTextView = (TextView) convertView.findViewById(R.id.diary_date);
                convertView.setTag(dateTextView);
            } else
                dateTextView = (TextView) convertView.getTag();


            DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
            dateTextView.setText(formatter.format(currentEntry.getDate()));

            return convertView;
        }

    }
}
