package cs125.winter2017.uci.appetizer.diet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.HashSet;

import cs125.winter2017.uci.appetizer.R;
import cs125.winter2017.uci.appetizer.Search.SearchActivity;
import cs125.winter2017.uci.appetizer.daily_targets.DailyTargetActivity;
import cs125.winter2017.uci.appetizer.food_diary.DiaryActivity;
import cs125.winter2017.uci.appetizer.nutrients.NutrientSingleValueEditorFragment;

public class DietaryRestrictionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, DietaryRestrictionFragment.OnDietaryRestrictionEdit {

    private DietaryRestrictionFragment dietaryRestrictionEditor;

    private View submitButton;

    private HashSet<ViewGroup> editedValues;

    public DietaryRestrictionActivity(){
        editedValues = new HashSet<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietary_restriction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dietaryRestrictionEditor = (DietaryRestrictionFragment) getSupportFragmentManager()
                .findFragmentById(R.id.diet_restriction_editor);
        dietaryRestrictionEditor.setEditListener(this);

        submitButton = findViewById(R.id.diet_restriction_submit);
        hideSubmit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            new AlertDialog.Builder(this)
                    .setMessage("There are unsaved changes to the dietary restrictions. Leave anyway?")
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DietaryRestrictionActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .create()
                    .show();


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent moveAwayIntent = null;
        switch (item.getItemId())
        {
            case R.id.nav_diary:
                moveAwayIntent = new Intent(this, DiaryActivity.class);
                break;
            case R.id.nav_search:
                moveAwayIntent = new Intent(this, SearchActivity.class);
                break;
            case R.id.nav_daily_targets:
                moveAwayIntent = new Intent(this, DailyTargetActivity.class);
                break;
        }

        if (moveAwayIntent != null){
            final Intent continueIntent = moveAwayIntent;
            if (!editedValues.isEmpty())
                new AlertDialog.Builder(this)
                        .setMessage("There are unsaved changes to the dietary restrictions. Leave anyway?")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DietaryRestrictionActivity.this.startActivity(continueIntent);
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
    public void onClick(View v) {
        dietaryRestrictionEditor.commit();

        View editedView;
        for (ViewGroup restrictionView : editedValues) {
            editedView = restrictionView.findViewById(R.id.diet_restriction_edited);
            if (editedView != null)
                restrictionView.removeView(editedView);
        }

        editedValues.clear();
        hideSubmit();

        new AlertDialog.Builder(this)
                .setMessage(R.string.diet_restrictions_saved)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onDietaryRestrictionEdit(DietaryRestriction restriction, boolean value) {
        DietaryRestrictions dietaryRestrictions = DietaryRestrictions.loadFromContext(this);
        ViewGroup restrictionView = (ViewGroup)
                dietaryRestrictionEditor.getRestrictionList().findViewWithTag(restriction);

        View editedView;
        if (value != dietaryRestrictions.hasRestriction(restriction)){
            editedView = restrictionView.findViewById(R.id.diet_restriction_edited);
            if (editedView == null) {
                getLayoutInflater().inflate(R.layout.layout_dietary_restriction_edited,
                        restrictionView);
                editedValues.add(restrictionView);
            }
        } else {
            editedView = restrictionView.findViewById(R.id.diet_restriction_edited);
            if (editedView != null)
                restrictionView.removeView(editedView);
            editedValues.remove(restrictionView);
        }

        if (editedValues.isEmpty())
            hideSubmit();
        else
            showSubmit();

    }

    private void hideSubmit(){
        submitButton.setVisibility(View.GONE);
        submitButton.setOnClickListener(null);
    }

    private void showSubmit(){
        submitButton.setVisibility(View.VISIBLE);
        submitButton.setOnClickListener(this);
    }

}
