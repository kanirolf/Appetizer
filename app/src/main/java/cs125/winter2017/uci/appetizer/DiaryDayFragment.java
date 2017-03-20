package cs125.winter2017.uci.appetizer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs125.winter2017.uci.appetizer.databinding.FragmentDiaryDayBinding;
import cs125.winter2017.uci.appetizer.food_diary.FoodDiaryDay;
import cs125.winter2017.uci.appetizer.food_diary.FoodDiaryEntry;

public class DiaryDayFragment extends Fragment implements
        DiaryDayCardFragment.OnDiaryCardEditListener {

    private static final String ENTRY = "ENTRY";

    private FoodDiaryDay day;

    private OnDiaryDayEditListener editListener;

    // Required empty public constructor
    public DiaryDayFragment() {}

    public static DiaryDayFragment newInstance(FoodDiaryDay day) {
        DiaryDayFragment fragment = new DiaryDayFragment();

        Bundle args = new Bundle();
        args.putParcelable(ENTRY, day);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            day = getArguments().getParcelable(ENTRY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDiaryDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary_day, container, false);
        binding.setDate(day.getDate());

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        DiaryDayCardFragment cardFragment;

        for (FoodDiaryEntry entry : day.entries){
            cardFragment = DiaryDayCardFragment.newInstance(entry);
            cardFragment.setEditListener(this);
            transaction.add(R.id.diary_day, cardFragment);
        }

        transaction.commit();
        return binding.getRoot();
    }

    @Override
    public void onDiaryCardEdit(FoodDiaryEntry entry) {
        editListener.onDiaryDayEdit(day, entry);
    }

    public void setEditListener(OnDiaryDayEditListener listener){
        editListener = listener;
    }

    public interface OnDiaryDayEditListener {
        void onDiaryDayEdit(FoodDiaryDay day, FoodDiaryEntry entry);
    }

}
