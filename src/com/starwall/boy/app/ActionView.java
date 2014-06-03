package com.starwall.boy.app;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Antony on 14-6-2.
 */
public class ActionView extends TextView {

    private GestureDetector gd;
    private int scrollingOffset;

    public ActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gd = new GestureDetector(context, new InnerGestureListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(0, scrollingOffset);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return gd.onTouchEvent(event);
    }

    class InnerGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            scrollingOffset += -distanceY;
            invalidate();
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}