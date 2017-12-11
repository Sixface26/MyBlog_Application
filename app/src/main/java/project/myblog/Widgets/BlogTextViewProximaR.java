package project.myblog.Widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class BlogTextViewProximaR extends TextView {

        public BlogTextViewProximaR(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/ProximaNovaRegular.otf"));
        }

    }

