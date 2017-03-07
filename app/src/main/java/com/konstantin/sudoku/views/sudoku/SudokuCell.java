package com.konstantin.sudoku.views.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by konstantin on 13/02/17.
 */

// notre cellule qui herite de notre cellule de base
public class SudokuCell extends BaseSudokuCell {

    private Paint mPaint;
    private int x;
    private int y;

    public SudokuCell( Context context ){
        super(context);
        mPaint = new Paint();
    }

    public SudokuCell(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!this.isModifiable()){
            canvas.drawColor(Color.rgb(238, 238, 238));
        }

        if(this.isSelect()){
            canvas.drawColor(Color.rgb(92, 107, 192));
        }
        drawNumber(canvas);
        drawLines(canvas);
        drawRegion(canvas);
    }

    private void drawNumber(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(60);
        mPaint.setStyle(Paint.Style.FILL);

        Rect bounds = new Rect();
        // pour centrer dans la case la valeur
        mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);

        if( getValue() != 0 ){
            canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width())/2, (getHeight() + bounds.height())/2	, mPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    private void drawRegion(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(12);
        mPaint.setStyle(Paint.Style.STROKE);

        if(((x + 1)% 3) == 0){
            // droite
            canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), mPaint);
        }
        if(((y + 1)% 3) == 0){
            // bas
            canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint);
        }
        if(y == 0){
            // ligne du haut
            canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        }
        if(x == 0){
            // ligne du bas
            canvas.drawLine(0, 0, 0, getHeight(), mPaint);
        }
    }
}
