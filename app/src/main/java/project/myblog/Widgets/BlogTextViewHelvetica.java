package project.myblog.Widgets;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class BlogTextViewHelvetica extends TextView {

    public BlogTextViewHelvetica(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLight.ttf"));
    }

}
