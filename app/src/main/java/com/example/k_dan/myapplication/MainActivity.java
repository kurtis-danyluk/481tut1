package com.example.k_dan.myapplication;

        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v4.view.GestureDetectorCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.GestureDetector;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.TextView;
        import android.widget.Toast;

        import static java.lang.Boolean.FALSE;
        import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    private TextView output_text;                 // This is added for Text output
    private TextView watch_text;                    //Added to illustrate a functions use
    private GestureDetectorCompat DetectMe;         //This is what we're going to use to detect gestures

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output_text = (TextView) findViewById(R.id.outputText);  // Taking reference of text to be displayed on screen
        watch_text = (TextView) findViewById(R.id.placeText);       //The thing I'm implementing gestures for - this could be any view really

        //Create a gesture detector in this context and use the implementation defined by the class myGestureListener
        //More on myGestureListener in a moment- we're going to create it!
        DetectMe = new GestureDetectorCompat(this, new myGestureListener());

        //Here we override the default on touch listener for the text box we want to manipulate
        //We instead tell it to use the onTouch events as defined by DetectMe
        watch_text.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DetectMe.onTouchEvent(event);
                return true;
            }
        });




    }


    // Following functions are overrided to show text when a particular method called.
private class myGestureListener
            extends
                GestureDetector.SimpleOnGestureListener
            implements
                GestureDetector.OnDoubleTapListener {

    @Override
    public boolean onDown(MotionEvent e) {
        output_text.setText("onDown");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        output_text.setText("onSingleTapConfirmed");
        watch_text.setTextColor(Color.BLUE);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        output_text.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        output_text.setText("onDoubleTapEvent");
        watch_text.setTextColor(Color.RED);
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        output_text.setText("onSingleTapUp");
        return true;
    }


    @Override
    public void onShowPress(MotionEvent e) {
        output_text.setText("onShowPress");
        watch_text.setAllCaps(FALSE);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        output_text.setText("onLongPress");
        watch_text.setAllCaps(TRUE);
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        output_text.setText("onScroll");
        watch_text.setTextSize(watch_text.getTextSize() + distanceX + distanceY);
        if (watch_text.getTextSize() > 60)
            watch_text.setTextSize(60);
        else if (watch_text.getTextSize() < 20)
            watch_text.setTextSize(20);

        return true;
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final int SWIPE_MIN_DISTANCE = 120;
        output_text.setText("onFling");
        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE)
            watch_text.setTextColor(Color.BLACK);

        return true;
    }

}

}