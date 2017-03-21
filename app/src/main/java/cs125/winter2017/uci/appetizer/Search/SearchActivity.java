package cs125.winter2017.uci.appetizer.Search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

import cs125.winter2017.uci.appetizer.R;
import cs125.winter2017.uci.appetizer.daily_targets.DailyTargetActivity;
import cs125.winter2017.uci.appetizer.diet.DietaryRestrictionActivity;
import cs125.winter2017.uci.appetizer.food_diary.DiaryActivity;
import cs125.winter2017.uci.appetizer.location.GetLocationFragment;

public class SearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GetLocationFragment.OnLocationAcquireListener, OnEditorActionListener,
        View.OnClickListener, SearchPlacesRequest.ResponseListener, Response.ErrorListener {

    private GetLocationFragment locationFragment;

    private View progressView;
    private View filterView;
    private View resultsView;

    private ViewGroup resultsContainer;

    private TextView locationText;
    private EditText searchQueryText;

    private RadioGroup searchPrice;
    private RadioGroup mealTime;

    private ImageView searchIcon;

    private Location location;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setCustomView(R.layout.layout_search_bar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressView = findViewById(R.id.search_in_progress_container);
        filterView = findViewById(R.id.search_filters);
        resultsView = findViewById(R.id.search_results_container);

        resultsContainer = (ViewGroup) findViewById(R.id.search_results);

        filterView.setVisibility(View.VISIBLE);
        resultsView.setVisibility(View.GONE);
        progressView.setVisibility(View.GONE);

        searchQueryText = (EditText) findViewById(R.id.search_bar_query);
        searchQueryText.setOnEditorActionListener(this);

        searchPrice = (RadioGroup) findViewById(R.id.search_price);
        mealTime = (RadioGroup) findViewById(R.id.search_meal_time);

        locationText = (TextView) findViewById(R.id.search_current_location_text);
        locationFragment = (GetLocationFragment) getSupportFragmentManager()
                .findFragmentById(R.id.search_current_location_map);
        locationFragment.setLocationAcquireListener(this);

        queue = Volley.newRequestQueue(this);

        searchIcon = (ImageView) findViewById(R.id.search_bar_search);
        searchIcon.setOnClickListener(this);
        searchIcon.setActivated(false);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_diary:
                Intent mainActivityIntent = new Intent(this, DiaryActivity.class);
                startActivity(mainActivityIntent);
                break;
            case R.id.nav_daily_targets:
                Intent dailyGoalsIntent = new Intent(this, DailyTargetActivity.class);
                startActivity(dailyGoalsIntent);
                break;
            case R.id.nav_dietary_restriction:
                Intent dietaryRestrictionIntent = new Intent(this, DietaryRestrictionActivity.class);
                startActivity(dietaryRestrictionIntent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onLocationAcquire(GetLocationFragment locationFragment) {
        locationText.setText(locationFragment.getLocationString());
        location = locationFragment.getLocation();
    }

    @Override
    public void onClick(View v) {
        if (v.isActivated()){
            searchIcon.setActivated(false);

            filterView.setVisibility(View.VISIBLE);
            progressView.setVisibility(View.GONE);
            resultsView.setVisibility(View.GONE);

            searchQueryText.requestFocus();
            searchQueryText.selectAll();


            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchQueryText, 0);
        } else
            doSearch();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            doSearch();
            return true;
        }
        return false;
    }

    @Override
    public void onResponse(List<Restaurant> results) {
        progressView.setVisibility(View.GONE);
        filterView.setVisibility(View.GONE);
        resultsView.setVisibility(View.VISIBLE);

        resultsContainer.removeAllViews();

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup viewGroup;
        String dollarSigns;

        for (Restaurant result : results){
            dollarSigns = "";

            for (int i = 0; i < result.getPrice(); i++)
                dollarSigns += "$";

            viewGroup = (ViewGroup) inflater.inflate(R.layout.layout_search_result_card, resultsContainer, false);
            ((TextView) viewGroup.findViewById(R.id.search_result_name)).setText(result.getName());
            ((TextView) viewGroup.findViewById(R.id.search_result_price)).setText(dollarSigns);
            ((TextView) viewGroup.findViewById(R.id.search_result_address)).setText(result.getAddress());
            resultsContainer.addView(viewGroup);
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        new AlertDialog.Builder(this)
                .setMessage(error.toString())
                .setPositiveButton("Meow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .create()
                .show();
    }

    private void doSearch(){
        if (searchQueryText == getCurrentFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchQueryText.getWindowToken(), 0);
        }
         
        progressView.setVisibility(View.VISIBLE);
        filterView.setVisibility(View.GONE);
        resultsView.setVisibility(View.GONE);

        searchIcon.setActivated(true);

        String mealTimeValue = ((RadioButton) findViewById(mealTime.getCheckedRadioButtonId()))
                .getText().toString();
        int priceValue = ((RadioButton) findViewById(searchPrice.getCheckedRadioButtonId()))
                .getText().toString().length();

        queue.add(
                new SearchPlacesRequest.Builder()
                        .setQuery(searchQueryText.getText().toString())
                        .setLocation(location)
                        .setMealType(mealTimeValue)
                        .setPrice(priceValue)
                        .setListener(this)
                        .setErrorListener(this)
                        .build()
        );
    }
}
