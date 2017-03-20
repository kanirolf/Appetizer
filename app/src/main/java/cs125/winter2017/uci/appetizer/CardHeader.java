package cs125.winter2017.uci.appetizer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardHeader extends LinearLayout {

    private TextView titleView;
    private ImageView iconView;

    private String title;
    private Drawable icon;

    public CardHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOrientation(HORIZONTAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View innerViews = inflater.inflate(R.layout.layout_card_header, this);

        titleView = (TextView) innerViews.findViewById(R.id.card_header_title);
        iconView = (ImageView) innerViews.findViewById(R.id.card_header_icon);

        setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        titleView.setTextColor(ContextCompat.getColor(context, android.R.color.white));

        TypedArray convert = context.obtainStyledAttributes(attrs, R.styleable.CardHeader);

        setTitle(convert.getString(R.styleable.CardHeader_cardHeaderTitle));
        setIcon(convert.getDrawable(R.styleable.CardHeader_cardHeaderIcon));

        convert.recycle();
    }

    public void setTitle(String title){
        this.title = title;
        titleView.setText(title);
    }

    public String getTitle(){
        return title;
    }

    public void setIcon(Drawable icon){
        this.icon = icon;
        iconView.setImageDrawable(icon);
    }

    public Drawable getIcon(){
        return icon;
    }
}
