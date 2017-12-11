package project.myblog.Widgets;

/**
 * Created by user on 18-04-2017.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class BlogEditText extends EditText{

    public BlogEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/ProximaNovaRegular.otf"));
    }
}
