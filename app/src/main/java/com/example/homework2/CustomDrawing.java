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

/**
 * Draws the features dependent on the state of the seek bars
 */
public class CustomDrawing extends SurfaceView  {

    //instance variables
    private String current="";
    //seekbar progress values
    private int redValue;
    private int greenValue;
    private int blueValue;

    //paints for every object so it saves it
    protected Paint houseColor = new Paint();
    protected Paint roofColor = new Paint();
    protected Paint bushColor = new Paint();
    protected Paint mailBoxColor = new Paint();
    protected Paint hydrantColor = new Paint();
    protected Paint doorColor = new Paint();
    protected Paint windowColor = new Paint();

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

        //at the start initialize the drawing, all to the starting
        // black paint, if its not the very beginning,
        //update the drawing to what the user has set the colors
        //to
        if(redValue < 1 && greenValue <1 && blueValue <1){
            initDrawing(canvas);
        }
        else{
            updateDrawing(canvas);
        }

    }

    /**
     * updateDrawing - sets the color of the features
     * dependent on what is the most recently touched
     * @param canvas - canvas that draws on
     */
    public void updateDrawing(Canvas canvas){

        //black paint that does not fill
        Paint blackStartPaint = new Paint();
        blackStartPaint.setColor(0xFF000000);
        blackStartPaint.setStyle(Paint.Style.STROKE);
        blackStartPaint.setStrokeWidth(5.0f);

        //if the current draw is equal to an object then
        //set that obkects color to the progress of the
        //seek bars, and the rest to the initial black paint

        if(current.equals("House")){
            houseColor.setColor(Color.rgb(redValue,greenValue,blueValue));
            houseColor.setStyle(Paint.Style.STROKE);
            houseColor.setStrokeWidth(5.0f);
            drawHouse(canvas, houseColor);
            drawDoor(canvas, blackStartPaint);
            drawHydrant(canvas, blackStartPaint);
            drawMailBox(canvas, blackStartPaint);
            drawBush(canvas, blackStartPaint);
            drawWindows(canvas, blackStartPaint);
            drawRoof(canvas,blackStartPaint);
        }
        else if (current.equals("Roof")){
            roofColor.setColor(Color.rgb(redValue,greenValue,blueValue));
            drawRoof(canvas, roofColor);
            drawHouse(canvas, blackStartPaint);
            drawDoor(canvas, blackStartPaint);
            drawHydrant(canvas, blackStartPaint);
            drawMailBox(canvas, blackStartPaint);
            drawBush(canvas, blackStartPaint);
            drawWindows(canvas, blackStartPaint);
        }
        else if(current.equals("Door")){
            doorColor.setColor(Color.rgb(redValue,greenValue,blueValue));
            doorColor.setStyle(Paint.Style.STROKE);
            doorColor.setStrokeWidth(5.0f);
            drawRoof(canvas, blackStartPaint);
            drawHouse(canvas, blackStartPaint);
            drawDoor(canvas, doorColor);
            drawHydrant(canvas, blackStartPaint);
            drawMailBox(canvas, blackStartPaint);
            drawBush(canvas, blackStartPaint);
            drawWindows(canvas, blackStartPaint);
        }
        else if(current.equals("Fire Hydrant")){
            hydrantColor.setColor(Color.rgb(redValue,greenValue,blueValue));
            drawRoof(canvas, blackStartPaint);
            drawHouse(canvas, blackStartPaint);
            drawDoor(canvas, blackStartPaint);
            drawHydrant(canvas, hydrantColor);
            drawMailBox(canvas, blackStartPaint);
            drawBush(canvas, blackStartPaint);
            drawWindows(canvas, blackStartPaint);
        }
        else if(current.equals("MailBox")){
            mailBoxColor.setColor(Color.rgb(redValue,greenValue,blueValue));
            drawRoof(canvas, blackStartPaint);
            drawHouse(canvas, blackStartPaint);
            drawDoor(canvas, blackStartPaint);
            drawHydrant(canvas, blackStartPaint);
            drawMailBox(canvas, mailBoxColor);
            drawBush(canvas, blackStartPaint);
            drawWindows(canvas, blackStartPaint);
        }
        else if(current.equals("Bush")){
            bushColor.setColor(Color.rgb(redValue,greenValue,blueValue));
            drawRoof(canvas, blackStartPaint);
            drawHouse(canvas, blackStartPaint);
            drawDoor(canvas, blackStartPaint);
            drawHydrant(canvas, blackStartPaint);
            drawMailBox(canvas, blackStartPaint);
            drawBush(canvas, bushColor);
            drawWindows(canvas, blackStartPaint);
        }
        else if(current.equals("Window")){
            windowColor.setColor(Color.rgb(redValue,greenValue,blueValue));
            windowColor.setStyle(Paint.Style.STROKE);
            windowColor.setStrokeWidth(5.0f);
            drawRoof(canvas, blackStartPaint);
            drawHouse(canvas, blackStartPaint);
            drawDoor(canvas, blackStartPaint);
            drawHydrant(canvas, blackStartPaint);
            drawMailBox(canvas, blackStartPaint);
            drawBush(canvas, blackStartPaint);
            drawWindows(canvas, windowColor);
        }
        else {
            initDrawing(canvas);
        }
    }
    /** External Citation
     * Date: February 19th 2019
     * Problem: could not get the color to save so that it
     * would continue to draw that feature in the specified color
     * after another feature was clicked and changed/
     * Resource: Ben Pirkl
     * Solution: he guided me through the process of having instance variables
     * for every features color. 
     */

    /**
     * InitDrawing- initiates the drawing to all black paint
     * once the app starts up or initializes all the colors to
     * the updated color
     * @param canvas- canvas to draw on
     */

    public void initDrawing(Canvas canvas){
        //set background to white
        canvas.drawColor(0xFFFFFFFF);

        //create a black paint that does not fill
        Paint blackStartPaint = new Paint();
        blackStartPaint.setColor(0xFF000000);
        blackStartPaint.setStyle(Paint.Style.STROKE);
        blackStartPaint.setStrokeWidth(5.0f);

        //if the values of seek bars are at 0 then set to black
        //paint this is for initial drawing when app opens
        if(redValue < 1 && blueValue < 1 && greenValue <1) {
            this.houseColor.setColor(blackStartPaint.getColor());
            this.houseColor.setStyle(Paint.Style.STROKE);
            this.houseColor.setStrokeWidth(5.0f);
            this.windowColor.setColor(blackStartPaint.getColor());
            this.windowColor.setStyle(Paint.Style.STROKE);
            this.windowColor.setStrokeWidth(5.0f);
            this.roofColor.setColor(blackStartPaint.getColor());
            this.roofColor.setStyle(Paint.Style.STROKE);
            this.roofColor.setStrokeWidth(5.0f);
            this.bushColor.setColor(blackStartPaint.getColor());
            this.bushColor.setStyle(Paint.Style.STROKE);
            this.bushColor.setStrokeWidth(5.0f);
            this.doorColor.setColor(blackStartPaint.getColor());
            this.doorColor.setStyle(Paint.Style.STROKE);
            this.doorColor.setStrokeWidth(5.0f);
            this.hydrantColor.setColor(blackStartPaint.getColor());
            this.hydrantColor.setStyle(Paint.Style.STROKE);
            this.hydrantColor.setStrokeWidth(5.0f);
            this.mailBoxColor.setColor(blackStartPaint.getColor());
            this.mailBoxColor.setStyle(Paint.Style.STROKE);
            this.mailBoxColor.setStrokeWidth(5.0f);
        }

        //if seek bars do not equal 0 then set to respective
        //features colors
        drawHouse(canvas, houseColor);
        drawWindows(canvas, windowColor);
        drawRoof(canvas, roofColor);
        drawBush(canvas, bushColor);
        drawDoor(canvas, doorColor);
        drawHydrant(canvas, hydrantColor);
        drawMailBox(canvas, mailBoxColor);

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

    /**
     * SendText gets the name of object that is most
     * recently touched from the activity class
     * @param nameOfObject - name of feature that was
     *                     just touched
     */
    public void sendText(String nameOfObject){
        this.current = nameOfObject;
    }
}

