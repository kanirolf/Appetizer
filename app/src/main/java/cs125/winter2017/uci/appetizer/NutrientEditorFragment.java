package cs125.winter2017.uci.appetizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs125.winter2017.uci.appetizer.nutrients.NutrientFactHolder;

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

    public NutrientEditorFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrient_editor, container);

        FragmentManager fragmentManager = getChildFragmentManager();

        calorieEditor = (NutrientSingleValueEditorFragment)
                fragmentManager.findFragmentById(R.id.editor_calories);
        calorieEditor.setEditListener(this);
        fatEditor = (NutrientSingleValueEditorFragment)
                fragmentManager.findFragmentById(R.id.editor_fat);
        fatEditor.setEditListener(this);
        proteinEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_protein);
        proteinEditor.setEditListener(this);
        cholesterolEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_cholesterol);
        cholesterolEditor.setEditListener(this);
        sugarEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_sugar);
        sugarEditor.setEditListener(this);
        carbohydratesEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_carbohydrate);
        carbohydratesEditor.setEditListener(this);
        sodiumEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_sodium);
        sodiumEditor.setEditListener(this);
        fiberEditor = (NutrientSingleValueEditorFragment)
                        fragmentManager.findFragmentById(R.id.editor_fiber);
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
        calorieEditor.setValue(nutrientFactHolder.getCalorie());
        fatEditor.setValue(nutrientFactHolder.getFat());
        proteinEditor.setValue(nutrientFactHolder.getProtein());
        cholesterolEditor.setValue(nutrientFactHolder.getCholesterol());
        sugarEditor.setValue(nutrientFactHolder.getSugar());
        carbohydratesEditor.setValue(nutrientFactHolder.getCarbs());
        sodiumEditor.setValue(nutrientFactHolder.getSodium());
        fiberEditor.setValue(nutrientFactHolder.getFiber());
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
        if (editListener != null)
            editListener.onNutrientEdit(nutrient, value);
    }

    public void setEditListener(OnNutrientsEditListener listener){
        editListener = listener;
    }

    public interface OnNutrientsEditListener {
        void onNutrientEdit(String nutrient, double value);
    }
}
