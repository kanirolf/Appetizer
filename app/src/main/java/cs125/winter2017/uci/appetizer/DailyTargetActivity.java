package cs125.winter2017.uci.appetizer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import cs125.winter2017.uci.appetizer.daily_targets.DailyTargets;

public class DailyTargetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NutrientEditorFragment.OnNutrientsEditListener {

    private NutrientEditorFragment nutrientEditor;

    private DailyTargets dailyTargets;

    private boolean changed;

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
        nutrientEditor.setEditListener(this);

        findViewById(R.id.daily_target_edit_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

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
        } else if (changed) {
            new AlertDialog.Builder(this)
                    .setMessage("There are unsaved changes to the nutrient targets. Leave anyway?")
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DailyTargetActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create()
                    .show();
        } else
            super.onBackPressed();


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent moveAwayIntent = null;

        switch (item.getItemId()){
            case R.id.nav_home:
                moveAwayIntent = new Intent(this, MainActivity.class);
                break;
            case R.id.nav_search:
                break;
        }

        if (moveAwayIntent != null) {
            final Intent continueIntent = moveAwayIntent;
            if (changed)
                new AlertDialog.Builder(this)
                        .setMessage("There are unsaved changes to the nutrient targets. Leave anyway?")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DailyTargetActivity.this.startActivity(continueIntent);
                            }
                        })
                        .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create()
                        .show();
             else
                 startActivity(continueIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onNutrientEdit(String nutrient, double value) {
        changed = true;
    }

    public void onSubmit(){

        dailyTargets.setCalorie(nutrientEditor.getCalorie());
        dailyTargets.setFat(nutrientEditor.getFat());
        dailyTargets.setProtein(nutrientEditor.getProtein());
        dailyTargets.setCholesterol(nutrientEditor.getCholesterol());
        dailyTargets.setSugar(nutrientEditor.getSugar());
        dailyTargets.setCarbs(nutrientEditor.getCarbs());
        dailyTargets.setSodium(nutrientEditor.getSodium());
        dailyTargets.setFiber(nutrientEditor.getFiber());

        new AlertDialog.Builder(this)
            .setMessage(R.string.daily_targets_saved)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    changed = false;
                }
            })
            .create()
            .show();
    }

}
