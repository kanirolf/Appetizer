package cs125.winter2017.uci.appetizer.food_diary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.joda.time.DateTime;

import cs125.winter2017.uci.appetizer.nutrients.NutrientEditorFragment;
import cs125.winter2017.uci.appetizer.R;

public class DiaryEntryActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {


    public static final String ENTRY = "ENTRY";
    public static final String EDITING_ENTRY =
            "cs125.winter2017.uci.appetizer.food_diary.DiaryEntryActivity.EDITING_ENTRY";

    private NutrientEditorFragment nutrientEditor;

    private EditText nameField;
    private TextView dateField;

    private DateTime date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameField = (EditText) findViewById(R.id.diary_entry_edit_name);
        dateField = (TextView) findViewById(R.id.diary_entry_edit_date);

        nutrientEditor = (NutrientEditorFragment) getSupportFragmentManager()
                .findFragmentById(R.id.diary_entry_editor);
        nutrientEditor.setEditable(true);

        if (getIntent().getBooleanExtra(EDITING_ENTRY, false)){
            getSupportActionBar().setTitle(getString(R.string.title_activity_diary_entry_edit));

            FoodDiaryEntry entry = getIntent().getParcelableExtra(ENTRY);

            nameField.setText(entry.getName());

            date = (DateTime) entry.getDate();
            dateField.setText(FoodDiaryDay.HUMAN_READABLE_FORMATTER.print(date));
            dateField.setBackground(null);

            nutrientEditor.setValue(entry);
        } else {
            getSupportActionBar().setTitle(getString(R.string.title_activity_diary_entry_new));

            date = new DateTime();
            dateField.setText(FoodDiaryDay.HUMAN_READABLE_FORMATTER.print(date));
            dateField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View currentFocus = getCurrentFocus();
                    if (currentFocus != null)
                        currentFocus.clearFocus();
                    DatePickerDialog datePickerDialog = new DatePickerDialog( DiaryEntryActivity.this,
                            DiaryEntryActivity.this, date.getYear(), date.getMonthOfYear() - 1,
                            date.getDayOfMonth());
                    datePickerDialog.show();

                }
            });
        }

        findViewById(R.id.diary_entry_edit_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryEntryActivity.this.onSubmit();
            }
        });
    }

    @Override
    public void onBackPressed(){
        new ExitAlertDialogFragment().show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (getIntent().getBooleanExtra(EDITING_ENTRY, false))
            getMenuInflater().inflate(R.menu.menu_diary_entry_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                new ExitAlertDialogFragment().show(getSupportFragmentManager(), "dialog");
                return true;
            case R.id.diary_entry_edit_delete:
                new DeleteAlertDialogFragment().show(getSupportFragmentManager(), "dialog");
                return true;
    }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = date.withYear(year).withMonthOfYear(month + 1).withDayOfMonth(dayOfMonth);
        dateField.setText(FoodDiaryDay.HUMAN_READABLE_FORMATTER.print(date));
    }

    private void onCancel(){
        setResult(RESULT_CANCELED);
        finish();
    }

    private void onDelete(){
        Intent result = new Intent();
        result.putExtra("DELETE", true);

        setResult(RESULT_OK, result);
        finish();
    }

    private void onSubmit(){

        FoodDiaryEntry newEntry = new FoodDiaryEntry.Builder()
                .setName(nameField.getText().toString())
                .setDate(date)
                .setCalorie(nutrientEditor.getCalorie())
                .setFat(nutrientEditor.getFat())
                .setProtein(nutrientEditor.getProtein())
                .setCholesterol(nutrientEditor.getCholesterol())
                .setSugar(nutrientEditor.getSugar())
                .setSodium(nutrientEditor.getSodium())
                .setCarbs(nutrientEditor.getCarbs())
                .setFiber(nutrientEditor.getFiber())
                .build();

        Intent result = new Intent();
        result.putExtra(ENTRY, newEntry);

        setResult(RESULT_OK, result);
        finish();
    }

    public static class ExitAlertDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            return new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((DiaryEntryActivity) getActivity()).onCancel();
                    }
                })
                .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setMessage(R.string.diary_entry_unsaved)
                .create();
        }
    }

    public static class DeleteAlertDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            return new AlertDialog.Builder(getActivity())
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((DiaryEntryActivity) getActivity()).onDelete();
                        }
                    })
                    .setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .setMessage(R.string.diary_entry_delete_warning)
                    .create();
        }
    }

}
