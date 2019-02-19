package com.example.homework2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomDrawing extends SurfaceView  {

    //instance variables
    private String current="";
    private int redValue;
    private int greenValue;
    private int blueValue;

    public CustomDrawing(Context context) {
        super(context);
        initialize();
    }

    public CustomDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public CustomDrawing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public CustomDrawing(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize(){
        setWillNotDraw(false);
    }



    @Override
    protected void onDraw(Canvas canvas){
        //set background to white
        canvas.drawColor(0xFFFFFFFF);

        //create a black paint color that does not fill the drawing
        Paint blackPaint = new Paint();
        blackPaint.setColor(0xFF000000);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(5.0f);

        //create a brown paint
        Paint brownPaint = new Paint();
        brownPaint.setColor(0xFFA52A2A);

        //create blue paint
        Paint bluePaint = new Paint();
        bluePaint.setColor(0xFF0000FF);

        //create yellow paint
        Paint yellowPaint = new Paint();
        yellowPaint.setColor(0xFFFFFF00);

        //using the values from the seekBars, create a paint that does not fill the drawing
        Paint seekBarPaint = new Paint();
        seekBarPaint.setColor(Color.rgb(redValue,greenValue,blueValue));
        seekBarPaint.setStyle(Paint.Style.STROKE);
        seekBarPaint.setStrokeWidth(5.0f);

        //using the values from the seekBars, create a paint that fills the drawing
        Paint fillSeekBarPaint = new Paint();
        fillSeekBarPaint.setColor(Color.rgb(redValue,greenValue,blueValue));

        /**
         *For each feature if its the most recently touched feature than set the color
         * to the paint that corresponds to the seek bar values
         */
        //draw the house
        if(current.equals("house")){
            drawHouse(canvas, seekBarPaint);
        }
        else{
            drawHouse(canvas,blackPaint);
        }

        //draw the roof
        if(current.equals("roof")){
            drawRoof(canvas, fillSeekBarPaint);
        }
        else{
            drawRoof(canvas, brownPaint);
        }

        //drawing the windows
        if(current.equals("window")){
            drawWindows(canvas, seekBarPaint);
        }
        else{
            drawWindows(canvas, blackPaint);
        }

        //drawing the door
        if(current.equals("door")){
            drawDoor(canvas,seekBarPaint);
        }
        else {
            drawDoor(canvas, blackPaint);
        }

        //drawing bush
        Paint greenPaint = new Paint();
        greenPaint.setColor(0xFF00FF00);

        if(current.equals("bush")){
            drawBush(canvas, fillSeekBarPaint);
        }
        else{
            drawBush(canvas, greenPaint);
        }


        //draw mailbox
        if(current.equals("mailbox")){
            drawMailBox(canvas,fillSeekBarPaint);
        }
        else{
            drawMailBox(canvas,bluePaint);
        }

        //draw fire hydrant
        if(current.equals("fire")){
            drawHydrant(canvas, fillSeekBarPaint);
        }
        else{
            drawHydrant(canvas, yellowPaint);
        }


    }

    /**
     * Allows the activity class to set the value of current
     * and be able to use it to determine what to draw
     * @param current - most recently touched feature
     */
    public void setCurrent(String current){
        this.current = current;
    }
   /**
    * Sets the redValue, allows activity class to set it
    * depending on if seekbar's progress changed
    * @param redValue - the red seekbars progress
    */
    public void setRedValue(int redValue){
        this.redValue = redValue;
    }

    /**
     * Sets the greenValue, allows activity class to set it
     * depending on if seekbar's progress changed
     * @param greenValue - the green seekbars progress
     */
    public void setGreenValue(int greenValue){
        this.greenValue = greenValue;
    }

    /**External Citation
     * Date: February 16 2019
     * Problem: Could not find a way to access instance variables
     * from activity class
     * Resource: https://stackoverflow.com/questions/17261815/how-to-access-variables-from-main-activity-to-surfaceview-android
     * Solution: used the idea of set methods from this example
     */

    /**
     * Sets the blueValue, allows activity class to set it
     * depending on if seekbar's progress changed
     * @param blueValue - the blue seekbars progress
     */
    public void setBlueValue(int blueValue){
        this.blueValue = blueValue;
    }

    /**
     * draws the main rectangle for the house
     * @param canvas - canvas its drawing on
     * @param paint - what color to draw the feature
     */
    public void drawHouse(Canvas canvas, Paint paint){
        canvas.drawRect(500.0f, 500.0f, 1000.0f, 1000.0f, paint);
    }

    /**
     * draws the roof as a triangle for the house
     * @param canvas - canvas its drawing on
     * @param paint - what color to draw the feature
     */
    public void drawRoof(Canvas canvas, Paint paint) {
        Point a = new Point(500, 500);
        Point b = new Point(1000, 500);
        Point c = new Point(750, 300);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, paint);
    }

    /**External Citation
     * Date: Feb 16 2019
     * Problem: Drawing a triangle since there is no drawTriangle
     * or drawPolygon method
     * Resource:https://stackoverflow.com/questions/20544668/how-to-draw-filled-triangle-on-android-canvas/22690364
     * Solution:used the code from this example
     */

    /**
     *draws the window for the house
     * @param canvas - canvas to draw on
     * @param paint - color that will be used to draw
     */
    public void drawWindows(Canvas canvas, Paint paint){
        canvas.drawRect(550.0f, 550.0f, 700.0f, 700.0f ,paint);
        canvas.drawRect(800.0f,550.0f, 950.0f, 700.0f , paint);
        canvas.drawLine(625.0f, 550.0f, 625.0f, 700.0f,paint);
        canvas.drawLine(875.0f, 550.0f, 875.0f, 700.0f,paint);
        canvas.drawLine(550.0f, 625.0f, 700.0f, 625.0f,paint);
        canvas.drawLine(800.0f, 625.0f, 950.0f, 625.0f,paint);
    }

    /**
     *draws the door with doorknob for the house
     * @param canvas - canvas to draw on
     * @param paint - color that will be used to draw
     */
    public void drawDoor(Canvas canvas, Paint paint){
        canvas.drawRect(675.0f, 825.0f, 825.0f , 1000.0f,paint);
        canvas.drawCircle(800.0f, 915.0f, 8.0f,paint);
    }

    /**
     *draws the bush next to the house
     * @param canvas - canvas to draw on
     * @param paint - color that will be used to draw
     */
    public void drawBush(Canvas canvas, Paint paint){
        canvas.drawCircle(1200.0f, 900.0f, 100.0f, paint);
    }

    /**
     *draws the mailbox for the house
     * @param canvas - canvas to draw on
     * @param paint - color that will be used to draw
     */
    public void drawMailBox(Canvas canvas, Paint paint){
        canvas.drawRect(300.0f,900.0f,325.0f,1000.0f,paint);
        canvas.drawRect(325.0f,900.0f, 350.0f, 925.0f, paint);
    }

    /**
     *draws the fire hydrant for the house
     * @param canvas - canvas to draw on
     * @param paint - color that will be used to draw
     */
    public void drawHydrant(Canvas canvas, Paint paint){
        canvas.drawRect(1400.0f, 900.0f, 1450.0f, 1000.0f,paint);
        canvas.drawCircle(1425.0f, 900.0f, 25.0f, paint );
    }

}

