package cs125.winter2017.uci.appetizer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs125.winter2017.uci.appetizer.databinding.FragmentDiaryDayCardBinding;
import cs125.winter2017.uci.appetizer.food_diary.FoodDiaryEntry;

public class DiaryDayCardFragment extends Fragment implements View.OnClickListener {

    private static final String ENTRY = "ENTRY";

    private FragmentDiaryDayCardBinding binding;

    private NutrientEditorFragment nutrientDisplay;
    private FoodDiaryEntry entry;

    private OnDiaryCardEditListener editListener;

    // Required empty public constructor
    public DiaryDayCardFragment() {}

    // TODO: Rename and change types and number of parameters
    public static DiaryDayCardFragment newInstance(FoodDiaryEntry entry) {
        DiaryDayCardFragment fragment = new DiaryDayCardFragment();

        Bundle args = new Bundle();
        args.putParcelable(ENTRY, entry);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            entry = getArguments().getParcelable(ENTRY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_diary_day_card, container, false);

        binding.setName(entry.getName());
        binding.setCalories(entry.getCalorie());

        nutrientDisplay = (NutrientEditorFragment) getChildFragmentManager()
                .findFragmentById(R.id.diary_day_card_nutrients);
        nutrientDisplay.setValue(entry);
        binding.diaryDayCardNutrientsContainer.setVisibility(View.GONE);

        binding.diaryDayCardEdit.setOnClickListener(this);

        binding.getRoot().setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.diary_day_card_edit && editListener != null)
            editListener.onDiaryCardEdit(entry);
        else {
            if (binding.diaryDayCardNutrientsContainer.getVisibility() == View.GONE){
                binding.diaryDayCardNutrientsContainer.setVisibility(View.VISIBLE);
                getView().setActivated(true);
            } else {
                binding.diaryDayCardNutrientsContainer.setVisibility(View.GONE);
                getView().setActivated(false);
            }
        }
    }

    public void setEditListener(OnDiaryCardEditListener listener){
        editListener = listener;
    }

    public interface OnDiaryCardEditListener {
        void onDiaryCardEdit(FoodDiaryEntry entry);
    }
}
