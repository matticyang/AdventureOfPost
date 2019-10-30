package com.group0562.adventureofpost.shapeClicker;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.group0562.adventureofpost.Puzzles;


public class ShapeClicker extends Puzzles {
    /* bound indexes follow from left, right, up and down */
    static double[] bound;
    private Circle circle;
    private Paint paint;
    private double center_x;
    private double center_y;
    private double r;
    private boolean within = false;

    public ShapeClicker(long time, float radius, Paint p) {

        super(new ShapeClickerStats(time));
        this.paint = p;
        circle = new Circle(50, 50, radius, this.paint);
        circle.setLocation();
    }

    public static void setBound(double[] bound) {
        ShapeClicker.bound = bound;
    }

    // call this before update in front end
    void checkWithinBall(double cursor_x, double cursor_y) {
        this.within = circle.checkWithin(cursor_x, cursor_y);
        if(this.within){
            update();
            checkComplete();
        }
    }

    void draw(Canvas canvas){
        circle.draw(canvas);
    }

    @Override
    public void update() {
        super.update();
        if (this.within) {
            notifyObservers();
            circle.setLocation();
        }
    }

    @Override
    public void checkComplete() {
        if (this.puzzleStats.getPoints() >= 50) {
            this.setPuzzleComplete(true);
            this.puzzleStats.setPoints((int) this.puzzleStats.getTime());
        }
    }
}