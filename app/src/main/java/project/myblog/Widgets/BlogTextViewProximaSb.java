package project.myblog.Widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 11-12-2017.
 */
public class BlogTextViewProximaSb extends TextView {

    public BlogTextViewProximaSb(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/ProximaNovaSemibold.otf"));
    }
}
