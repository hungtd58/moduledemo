package vn.ghtk.textviewcustomelib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewCustomeSoi extends android.support.v7.widget.AppCompatTextView {
    public TextViewCustomeSoi(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initConfig(context,attrs);
    }
    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewCustomeSoi, 0, 0);
        try {
            int style = a.getInteger(R.styleable.TextViewCustomeSoi_text_color, 0);
            setColor(style);
        } finally {
            a.recycle();
        }
    }

    private void setColor(int style) {
        switch (style){
            case 1:
                setTextColor(Color.BLUE);
                break;
            case 2:
                setTextColor(Color.RED);
                break;
            case 3:
                setTextColor(Color.YELLOW);
                break;
        }
    }
}
