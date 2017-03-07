package com.konstantin.sudoku.views.sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.konstantin.sudoku.GameEngine;

/**
 * Created by konstantin on 13/02/17.
 */

public class SudokuGridView extends GridView {

    private final Context context;

    public SudokuGridView(final Context context , AttributeSet attrs) {
        super(context,attrs);

        this.context = context;
        // on lui ajoute notre adapter
        SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);
        setAdapter(gridViewAdapter);

        // on ajoute un onclick sur la grille
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int x = position % 9;
                int y = position / 9;
                // on envoi les position à notre gameengine pour trouver la case selectionnée
                GameEngine.getInstance().setSelectedPosition(x, y);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    class SudokuGridViewAdapter extends BaseAdapter {

        private Context context;

        public SudokuGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            // nombre d'element 9*9
            return 81;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // on retourne la valeur de la cellule en fonction de la position
            return GameEngine.getInstance().getGrid().getItem(position);
        }
    }
}
