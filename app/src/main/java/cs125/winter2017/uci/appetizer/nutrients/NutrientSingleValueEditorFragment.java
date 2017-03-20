package cs125.winter2017.uci.appetizer.nutrients;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import cs125.winter2017.uci.appetizer.R;
import cs125.winter2017.uci.appetizer.databinding.FragmentNutrientSingleValueEditorBinding;

public class NutrientSingleValueEditorFragment extends Fragment implements TextWatcher,
        View.OnFocusChangeListener {

    FragmentNutrientSingleValueEditorBinding binding;

    private OnValueEditListener editListener;

    private String label;
    private String units;
    private boolean editable;

    private double value;

    // Required empty public constructor
    public NutrientSingleValueEditorFragment() {
        editable = false;
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState){
        super.onInflate(context, attrs, savedInstanceState);

        TypedArray convert = context.obtainStyledAttributes(attrs, R.styleable.NutrientDisplay);

        label = convert.getString(R.styleable.NutrientDisplay_label);
        units = convert.getString(R.styleable.NutrientDisplay_units);

        convert.recycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nutrient_single_value_editor,
                container, false);

        binding.setLabel(label);
        binding.setUnits(units);

        setEditable(false);
        setValue(0);

        return binding.getRoot();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        String valueString = s.toString();
        try {
            value = Double.parseDouble(valueString);
        } catch (NumberFormatException e){
            value = 0;
        }

        if (editListener != null)
            editListener.onValueEdit(label, value);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus && binding.nutrientValueEditable.getText().toString().isEmpty())
            binding.nutrientValueEditable.setText("0");
    }

    public void setEditable(boolean editable){
        binding.setEditable(editable);
        if (binding.getEditable())
            binding.nutrientValueEditable.addTextChangedListener(this);
        else
            binding.nutrientValueEditable.removeTextChangedListener(this);
    }

    public void setValue(double value){
        String toDisplay;

        if (value % 1 == 0)
            toDisplay = String.format(Locale.getDefault(), "%d", (int) value);
        else
            toDisplay = String.format(Locale.getDefault(), "%.2f", value);

        binding.nutrientValueEditable.setText(toDisplay);
        binding.nutrientValueNoneditable.setText(toDisplay);

        if (binding.getEditable())
            this.value = value;
    }

    public double getValue(){
        return value;
    }

    public void setEditListener(OnValueEditListener listener){
        editListener = listener;
    }

    public interface OnValueEditListener {
        void onValueEdit(String label, double value);
    }
}
