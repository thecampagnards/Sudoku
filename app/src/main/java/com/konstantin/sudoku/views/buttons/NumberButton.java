package com.konstantin.sudoku.views.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.konstantin.sudoku.GameEngine;

/**
 * Created by konstantin on 13/02/17.
 */

// notre bouton de base
public class NumberButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {

    private int number;

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().setNumber(number);
    }

    public void setNumber(int number){
        this.number = number;
    }
}