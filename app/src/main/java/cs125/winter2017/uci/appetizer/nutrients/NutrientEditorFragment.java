package cs125.winter2017.uci.appetizer.nutrients;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs125.winter2017.uci.appetizer.R;

public class NutrientEditorFragment extends Fragment implements NutrientFactHolder,
        NutrientSingleValueEditorFragment.OnValueEditListener {

    private NutrientSingleValueEditorFragment calorieEditor;
    private NutrientSingleValueEditorFragment fatEditor;
    private NutrientSingleValueEditorFragment proteinEditor;
    private NutrientSingleValueEditorFragment cholesterolEditor;
    private NutrientSingleValueEditorFragment sugarEditor;
    private NutrientSingleValueEditorFragment carbohydratesEditor;
    private NutrientSingleValueEditorFragment sodiumEditor;
    private NutrientSingleValueEditorFragment fiberEditor;

    private OnNutrientsEditListener editListener;
    private boolean settingValues;

    public NutrientEditorFragment() {
        settingValues = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrient_editor, container);

        FragmentManager fragmentManager = getChildFragmentManager();

        calorieEditor = (NutrientSingleValueEditorFragment)
                fragmentManager.findFragmentById(R.id.editor_calories);
        fatEditor = (NutrientSingleValueEditorFragment)
                fragmentManager.findFragmentById(R.id.editor_fat);
        proteinEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_protein);
        cholesterolEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_cholesterol);
        sugarEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_sugar);
        carbohydratesEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_carbohydrate);
        sodiumEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_sodium);
        fiberEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_fiber);

        calorieEditor.setEditListener(this);
        fatEditor.setEditListener(this);
        proteinEditor.setEditListener(this);
        cholesterolEditor.setEditListener(this);
        sugarEditor.setEditListener(this);
        carbohydratesEditor.setEditListener(this);
        sodiumEditor.setEditListener(this);
        fiberEditor.setEditListener(this);

        setEditable(false);

        return view;
    }

    public void setEditable(boolean editable){
        calorieEditor.setEditable(editable);
        fatEditor.setEditable(editable);
        proteinEditor.setEditable(editable);
        cholesterolEditor.setEditable(editable);
        sugarEditor.setEditable(editable);
        carbohydratesEditor.setEditable(editable);
        sodiumEditor.setEditable(editable);
        fiberEditor.setEditable(editable);
    }

    public void setValue(NutrientFactHolder nutrientFactHolder){
        settingValues = true;

        calorieEditor.setValue(nutrientFactHolder.getCalorie());
        fatEditor.setValue(nutrientFactHolder.getFat());
        proteinEditor.setValue(nutrientFactHolder.getProtein());
        cholesterolEditor.setValue(nutrientFactHolder.getCholesterol());
        sugarEditor.setValue(nutrientFactHolder.getSugar());
        carbohydratesEditor.setValue(nutrientFactHolder.getCarbs());
        sodiumEditor.setValue(nutrientFactHolder.getSodium());
        fiberEditor.setValue(nutrientFactHolder.getFiber());

        settingValues = false;
    }

    @Override
    public double getCalorie() {
        return calorieEditor.getValue();
    }

    @Override
    public double getFat() {
        return fatEditor.getValue();
    }

    @Override
    public double getProtein() {
        return proteinEditor.getValue();
    }

    @Override
    public double getCholesterol() {
        return cholesterolEditor.getValue();
    }

    @Override
    public double getSugar() {
        return sugarEditor.getValue();
    }

    @Override
    public double getCarbs() {
        return carbohydratesEditor.getValue();
    }

    @Override
    public double getSodium() {
        return sodiumEditor.getValue();
    }

    @Override
    public double getFiber() {
        return fiberEditor.getValue();
    }

    @Override
    public void onValueEdit(String nutrient, double value) {

        if (editListener != null && !settingValues)
            editListener.onNutrientEdit(nutrient, value);
    }

    public void setEditListener(OnNutrientsEditListener listener){
        editListener = listener;
    }

    public interface OnNutrientsEditListener {
        void onNutrientEdit(String nutrient, double value);
    }
}
