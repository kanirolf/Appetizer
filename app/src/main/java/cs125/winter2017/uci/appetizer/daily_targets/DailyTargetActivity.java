package cs125.winter2017.uci.appetizer.daily_targets;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;

import cs125.winter2017.uci.appetizer.diet.DietaryRestrictionActivity;
import cs125.winter2017.uci.appetizer.food_diary.DiaryActivity;
import cs125.winter2017.uci.appetizer.nutrients.NutrientEditorFragment;
import cs125.winter2017.uci.appetizer.R;
import cs125.winter2017.uci.appetizer.Search.SearchActivity;
import cs125.winter2017.uci.appetizer.nutrients.NutrientSingleValueEditorFragment;

public class DailyTargetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NutrientEditorFragment.OnNutrientsEditListener, View.OnClickListener {

    private DailyTargetFragment targetEditor;
    private View submitButton;

    private HashSet<NutrientSingleValueEditorFragment> editedValues;

    public DailyTargetActivity(){
        editedValues = new HashSet<>();
    }

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

        targetEditor = (DailyTargetFragment)
                getSupportFragmentManager().findFragmentById(R.id.daily_target_editor);
        targetEditor.setEditListener(this);

        submitButton = findViewById(R.id.daily_target_edit_submit);
        hideSubmit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!editedValues.isEmpty()) {
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

        switch (item.getItemId()) {
            case R.id.nav_diary:
                moveAwayIntent = new Intent(this, DiaryActivity.class);
                break;
            case R.id.nav_search:
                moveAwayIntent = new Intent(this, SearchActivity.class);
                break;
            case R.id.nav_dietary_restriction:
                moveAwayIntent = new Intent(this, DietaryRestrictionActivity.class);
                break;
        }

        if (moveAwayIntent != null) {
            final Intent continueIntent = moveAwayIntent;
            if (!editedValues.isEmpty())
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

        DailyTargets dailyTargets = DailyTargets.loadFromContext(this);

        NutrientSingleValueEditorFragment toModify = null;
        FragmentManager fragmentManager =
                targetEditor.getNutrientEditor().getChildFragmentManager();

        boolean changed = false;

        if (nutrient.equals(getString(R.string.calories_label))) {
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_calories);
            changed = targetEditor.getCalorie() != dailyTargets.getCalorie();
        } else if (nutrient.equals(getString(R.string.fat_label))) {
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_fat);
            changed = targetEditor.getFat() != dailyTargets.getFat();
        } else if (nutrient.equals(getString(R.string.protein_label))){
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_protein);
            changed = targetEditor.getProtein() != dailyTargets.getProtein();
        } else if (nutrient.equals(getString(R.string.cholesterol_label))){
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_cholesterol);
            changed = targetEditor.getCholesterol() != dailyTargets.getCholesterol();
        } else if (nutrient.equals(getString(R.string.sugar_label))){
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_sugar);
            changed = targetEditor.getSugar() != dailyTargets.getSugar();
        } else if (nutrient.equals(getString(R.string.carbohydrates_label))){
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_carbohydrate);
            changed = targetEditor.getCarbs() != dailyTargets.getCarbs();
        } else if (nutrient.equals(getString(R.string.sodium_label))){
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_sodium);
            changed = targetEditor.getSodium() != dailyTargets.getSodium();
        } else if (nutrient.equals(getString(R.string.fiber_label))){
            toModify = (NutrientSingleValueEditorFragment)
                    fragmentManager.findFragmentById(R.id.editor_fiber);
            changed = targetEditor.getFiber() != dailyTargets.getFiber();
        }

        if (toModify == null)
            return;

        View editedView;
        ViewGroup modifiedView = (ViewGroup) toModify.getView();
        if (changed){
            if (modifiedView.getTag() == null) {
                editedView = getLayoutInflater().inflate(R.layout.layout_nutrient_edited, modifiedView, false);
                modifiedView.addView(editedView);
                modifiedView.setTag(editedView);
                editedValues.add(toModify);
            }
        } else {
            editedView = (View) modifiedView.getTag();
            if (editedView != null){
                modifiedView.removeView(editedView);
                modifiedView.setTag(null);
            }
            editedValues.remove(toModify);
        }

        if (editedValues.isEmpty())
            hideSubmit();
        else
            showSubmit();
    }

    @Override
    public void onClick(View v) {
        targetEditor.commit();

        for (NutrientSingleValueEditorFragment editorFragment : editedValues) {
            ViewGroup modifiedView = (ViewGroup) editorFragment.getView();
            View editedView = (View) modifiedView.getTag();
            if (editedView != null) {
                modifiedView.removeView(editedView);
                modifiedView.setTag(null);
            }
        }

        editedValues.clear();
        hideSubmit();

        new AlertDialog.Builder(this)
                .setMessage(R.string.daily_targets_saved)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .create()
                .show();
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
