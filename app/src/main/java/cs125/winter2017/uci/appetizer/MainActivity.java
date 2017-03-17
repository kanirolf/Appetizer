package cs125.winter2017.uci.appetizer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // TODO : actually fetch diary data
    private static final List<Calendar> DIARY_DATA = Arrays.asList(new Calendar[]{
            new GregorianCalendar(2017, 3, 8),
            new GregorianCalendar(2017, 3, 9),
            new GregorianCalendar(2017, 3, 10)
        }
    );

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
        ArrayAdapter<Calendar> diaryFeedAdapter = new ArrayAdapter<Calendar>(this, 0, DIARY_DATA){

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent){
                Calendar currentDate = getItem(position);
                TextView dateTextView;

                if (convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.layout_diary_entry_summary, null);

                    dateTextView = (TextView) convertView.findViewById(R.id.diary_date);
                    convertView.setTag(dateTextView);
                } else
                    dateTextView = (TextView) convertView.getTag();


                DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
                dateTextView.setText(formatter.format(currentDate.getTime()));

                if (position < getCount() - 1){
                    ViewGroup.LayoutParams layoutParams = ((LinearLayout) convertView).getLayoutParams();
                }


                return convertView;
            }
        };

        for (int i = 0; i < DIARY_DATA.size(); i++)
            diaryFeed.addView(diaryFeedAdapter.getView(i, null, null));
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
}
