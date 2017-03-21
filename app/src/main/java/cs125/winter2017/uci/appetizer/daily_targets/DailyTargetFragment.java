package cs125.winter2017.uci.appetizer.daily_targets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs125.winter2017.uci.appetizer.R;
import cs125.winter2017.uci.appetizer.nutrients.NutrientEditorFragment;
import cs125.winter2017.uci.appetizer.nutrients.NutrientFactHolder;

public class DailyTargetFragment extends Fragment implements NutrientFactHolder,
        NutrientEditorFragment.OnNutrientsEditListener {

    private NutrientEditorFragment nutrientEditor;

    private DailyTargets dailyTargets;
    private NutrientEditorFragment.OnNutrientsEditListener editListener;

    // Required default.
    public DailyTargetFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.fragment_daily_target, container);

        nutrientEditor = (NutrientEditorFragment)
                getChildFragmentManager().findFragmentById(R.id.daily_target_nutrient_editor);
        nutrientEditor.setEditable(true);
        nutrientEditor.setEditListener(this);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        load();
    }

    @Override
    public double getCalorie() {
        return nutrientEditor.getCalorie();
    }

    @Override
    public double getFat() {
        return nutrientEditor.getFat();
    }

    @Override
    public double getProtein() {
        return nutrientEditor.getProtein();
    }

    @Override
    public double getCholesterol() {
        return nutrientEditor.getCholesterol();
    }

    @Override
    public double getSugar() {
        return nutrientEditor.getSugar();
    }

    @Override
    public double getCarbs() {
        return nutrientEditor.getCarbs();
    }

    @Override
    public double getSodium() {
        return nutrientEditor.getSodium();
    }

    @Override
    public double getFiber() {
        return nutrientEditor.getFiber();
    }

    public void setEditListener(NutrientEditorFragment.OnNutrientsEditListener editListener){
        this.editListener = editListener;
    }

    @Override
    public void onNutrientEdit(String nutrient, double value) {
        if (editListener != null)
            editListener.onNutrientEdit(nutrient, value);
    }

    public NutrientEditorFragment getNutrientEditor(){
        return nutrientEditor;
    }

    public void load(){
        dailyTargets = DailyTargets.loadFromContext(getActivity());
        nutrientEditor.setValue(DailyTargets.loadFromContext(getActivity()));
    }

    public void commit(){
        dailyTargets.setCalorie(getCalorie());
        dailyTargets.setFat(getFat());
        dailyTargets.setCarbs(getCarbs());
        dailyTargets.setFiber(getFiber());
        dailyTargets.setSodium(getSodium());
        dailyTargets.setCholesterol(getCholesterol());
        dailyTargets.setProtein(getProtein());
        dailyTargets.setSugar(getSugar());
    }

}
