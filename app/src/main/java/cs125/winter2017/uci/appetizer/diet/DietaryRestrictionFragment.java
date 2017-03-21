package cs125.winter2017.uci.appetizer.diet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import cs125.winter2017.uci.appetizer.R;

public class DietaryRestrictionFragment extends Fragment implements View.OnClickListener {

    private DietaryRestrictions restrictions;

    private ViewGroup restrictionList;

    private OnDietaryRestrictionEdit editListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data){
        ViewGroup view = (ViewGroup)
                inflater.inflate(R.layout.fragment_dietary_restriction,  container, false);

        restrictionList = (ViewGroup) view.findViewById(R.id.diet_restrictions_list);

        ViewGroup singleRestriction;
        for (DietaryRestriction restriction : DietaryRestriction.values()){
            singleRestriction = (ViewGroup)
                    inflater.inflate(R.layout.layout_single_restriction, container, false);
            singleRestriction.findViewById(R.id.diet_restriction_checked).setOnClickListener(this);
            singleRestriction.setTag(restriction);
            ((TextView) singleRestriction.findViewById(R.id.diet_restriction_label))
                    .setText(restriction.getHumanReadableString());
            restrictionList.addView(singleRestriction);
        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        load();
    }

    @Override
    public void onClick(View v) {
        boolean checked = ((CheckBox) v).isChecked();
        DietaryRestriction restriction = (DietaryRestriction) ((View) v.getParent()).getTag();

        if (checked){
            restrictions.addRestriction(restriction);
        } else {
            restrictions.removeRestriction(restriction);
        }

        if (editListener != null)
            editListener.onDietaryRestrictionEdit(restriction, checked);
    }

    public void setEditListener(OnDietaryRestrictionEdit editListener){
        this.editListener = editListener;
    }

    public void commit(){
        restrictions.commit();
    }

    public ViewGroup getRestrictionList(){
        return restrictionList;
    }

    private void load(){
        restrictions = DietaryRestrictions.loadFromContext(getActivity());

        View restrictionView;
        DietaryRestriction restriction;
        for (int i = 0; i < restrictionList.getChildCount(); i++){
            restrictionView = restrictionList.getChildAt(i);
            restriction = (DietaryRestriction) restrictionView.getTag();
            ((CheckBox) restrictionView.findViewById(R.id.diet_restriction_checked))
                    .setChecked(restrictions.hasRestriction(restriction));
        }
    }

    public interface OnDietaryRestrictionEdit {
        void onDietaryRestrictionEdit(DietaryRestriction restriction, boolean value);
    }

}
