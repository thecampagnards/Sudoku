package com.konstantin.sudoku.views.sudoku;

import android.content.Context;
import android.view.View;

/**
 * Created by konstantin on 13/02/17.
 */

public class BaseSudokuCell extends View {

    private int value;
    private boolean modifiable = true;
    private boolean select;

    public BaseSudokuCell(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNotModifiable() {
        modifiable = false;
    }

    public boolean isModifiable() {
        return modifiable;
    }

    public void setInitValue(int value) {
        this.value = value;
        invalidate();
    }

    public void setValue(int value) {
        if (modifiable) {
            this.value = value;
        }

        invalidate();
    }

    public void setSelect(boolean select) {
        if (modifiable) {
            this.select = select;
        }

        invalidate();
    }

    public boolean isSelect() {
        return select;
    }

    public int getValue() {
        return value;
    }

    //@TODO check pour les regions
    public void checkRegion(){
        //if(this.getContext().)
    }
}