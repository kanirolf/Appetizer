package cs125.winter2017.uci.appetizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cs125.winter2017.uci.appetizer.daily_targets.DailyTargets;

public class DailyTargetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NutrientEditorFragment nutrientEditor;

    private DailyTargets dailyTargets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nutrientEditor = (NutrientEditorFragment)
                getSupportFragmentManager().findFragmentById(R.id.daily_target_editor);
        nutrientEditor.setEditable(true);
    }

    @Override
    public void onResume(){
        super.onResume();

        dailyTargets = DailyTargets.loadFromContext(this);
        nutrientEditor.setValue(dailyTargets);
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

    @Override
    public void onPause(){
        super.onPause();

        dailyTargets.setCalorie(nutrientEditor.getCalorie());
        dailyTargets.setFat(nutrientEditor.getFat());
        dailyTargets.setProtein(nutrientEditor.getProtein());
        dailyTargets.setCholesterol(nutrientEditor.getCholesterol());
        dailyTargets.setSugar(nutrientEditor.getSugar());
        dailyTargets.setCarbs(nutrientEditor.getCarbs());
        dailyTargets.setSodium(nutrientEditor.getSodium());
        dailyTargets.setFiber(nutrientEditor.getFiber());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_search:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
