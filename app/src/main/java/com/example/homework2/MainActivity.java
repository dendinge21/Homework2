package com.example.homework2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Main Activity sets the layout and manages events
 * @Author Olivia Dendinger
 * @Date February 19th, 2019
 */
public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    SeekBar redBar = null; //red seek bar
    TextView redText = null; // text that corresponds to red seek bar
    SeekBar greenBar = null; // green seek bar
    TextView greenText = null; //text that corresponds to green seek bar
    SeekBar blueBar = null; //blue seek bar
    TextView blueText = null; //text that corresponds to blue seek bar
    CustomDrawing current; //instance variable to keep track of most recent touched feature
    TextView nameOfDrawing; //title text
    SurfaceView drawing; //allows to invalidate
    int redValue; //the value that corresponds to red seek bar progress
    int greenValue; //the value that corresponds to green seek bar progress
    int blueValue; // the value that corresponds to blue seek bar progress
    CustomDrawing currentDrawing; //allows to get instance variables from here to


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redBar = findViewById(R.id.redSeekBar);
        redBar.setOnSeekBarChangeListener(this);

        redText = findViewById(R.id.redSeekBarText);
        redText.setText("0");

        greenBar = findViewById(R.id.greenSeekBar);
        greenBar.setOnSeekBarChangeListener(this);

        greenText = findViewById(R.id.greenSeekBarText);
        greenText.setText("0");

        blueBar = findViewById(R.id.blueSeekBar);
        blueBar.setOnSeekBarChangeListener(this);

        blueText = findViewById(R.id.blueSeekBarText);
        blueText.setText("0");

        nameOfDrawing = findViewById(R.id.currentDrawing);
        drawing = findViewById(R.id.drawing);
        drawing.setOnTouchListener(this);

         currentDrawing = findViewById(R.id.drawing);



    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar == redBar){
            //set text to correspond to seek bar value depending on which seekbar gets passed in
            redText.setText("" + progress);
            currentDrawing.setRedValue(progress); //
        }
        if(seekBar == greenBar){
           greenText.setText("" + progress);
           currentDrawing.setGreenValue(progress);
        }
        if(seekBar == blueBar) {
            blueText.setText("" + progress);
            currentDrawing.setBlueValue(progress);
        }


        //invalidate it so whenever a seekbar is moved it changes the most recently
        //touched feature's color
        drawing.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    /* ignore */
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    /* ignore */
    }

    /**
     * External Citation
     * Date: 14 February 2019
     * Problem: Setting text to correspond to SeekBar
     * Resource: Week 3: MainActivity.java -- Runtime Event Handling,
     * this was an example done in lecture
     * Solution: I used the example code from this lecture activity
     */


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //get the coordinates where the user touched the view
        int xTouch = (int)event.getX();
        int yTouch = (int)event.getY();

        //Dependent on where they touch set the text to the feature that they touched
        //and set the instance variable that keeps track of the most recent;y touched
        //feature to that feature
        if(xTouch >= 500 && xTouch <=1000 && yTouch <= 500 && yTouch >= 300) {
            nameOfDrawing.setText("Roof");
            currentDrawing.setCurrent("roof");
        }
        else if(xTouch >= 550 && xTouch <= 700  && yTouch >= 550 && yTouch <= 700){
            nameOfDrawing.setText("Window");
            currentDrawing.setCurrent("window");
        }
        else if(xTouch >= 800 && xTouch <= 950 && yTouch >= 550 && yTouch <= 700) {
            nameOfDrawing.setText("Window");
            currentDrawing.setCurrent("window");
        }
        else if(xTouch >= 675 && xTouch <= 825 && yTouch >= 825 && yTouch <= 1000) {
            nameOfDrawing.setText("Door");
            currentDrawing.setCurrent("door");
        }
        else if(xTouch >= 500 && xTouch <= 1000 && yTouch >= 500 && yTouch <= 1000){
            nameOfDrawing.setText("House");
            currentDrawing.setCurrent("house");
        }
        else if(xTouch >= 1100 && xTouch <= 1300 && yTouch >= 800 && yTouch <= 1000){
            nameOfDrawing.setText("Bush");
            currentDrawing.setCurrent("bush");
        }
        else if(xTouch >= 300 && xTouch <= 325 && yTouch >= 900 && yTouch <= 1000){
            nameOfDrawing.setText("MailBox");
            currentDrawing.setCurrent("mailbox");
        }
        else if(xTouch >= 325 && xTouch <= 350 && yTouch >= 900 && yTouch <= 925) {
            nameOfDrawing.setText("MailBox");
            currentDrawing.setCurrent("mailbox");
        }
        else if(xTouch >= 1400 && xTouch <= 1450 && yTouch >= 900 && yTouch <= 1000) {
            nameOfDrawing.setText("Fire Hydrant");
            currentDrawing.setCurrent("fire");
        }
        else {
            nameOfDrawing.setText("Sorry that is not a feature, click again!");
        }

        currentDrawing.sendText(nameOfDrawing.getText().toString());
        drawing.invalidate();
        return true;
    }



}

