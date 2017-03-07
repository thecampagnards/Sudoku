package com.konstantin.sudoku.views.sudoku;

import android.app.Activity;
import android.content.Context;
import android.widget.Chronometer;
import android.widget.Toast;

import com.konstantin.sudoku.R;
import com.konstantin.sudoku.SudokuChecker;

/**
 * Created by konstantin on 13/02/17.
 */

public class GameGrid {
    private SudokuCell[][] Sudoku = new SudokuCell[9][9];

    private Context context;

    public GameGrid( Context context ){
        // on creer notre grille
        this.context = context;
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                Sudoku[x][y] = new SudokuCell(context, x, y);
            }
        }
    }

    public void setGrid( int[][] grid ){
        // on ajoute des etats au cellules de la grille
        // sa valeur, modifiable ou non
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                Sudoku[x][y].setInitValue(grid[x][y]);
                if( grid[x][y] != 0 ){
                    Sudoku[x][y].setNotModifiable();
                }
            }
        }
    }

    public SudokuCell[][] getGrid(){
        return Sudoku;
    }

    public SudokuCell getItem(int x , int y ){
        return Sudoku[x][y];
    }

    public SudokuCell getItem( int position ){
        int x = position % 9;
        int y = position / 9;

        return Sudoku[x][y];
    }

    public void setSelectedItem( int x , int y){
        for( int i = 0 ; i < 9 ; i++ ){
            for( int j = 0 ; j < 9 ; j++ ){
                Sudoku[i][j].setSelect(false);
            }
        }
        Sudoku[x][y].setSelect(true);
    }

    public void setItem( int x , int y , int number ){
        Sudoku[x][y].setValue(number);
    }

    public void checkGame(){
        int [][] sudGrid = new int[9][9];
        // on rempli notre tableau des valeurs de la grille
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++ ){
                sudGrid[x][y] = getItem(x,y).getValue();
            }
        }

        // on check notre tableau
        if( SudokuChecker.getInstance().checkSudoku(sudGrid)){
            // on affiche un message de reussite
            Toast.makeText(context, context.getString(R.string.message_victoire), Toast.LENGTH_LONG).show();
            // on stop le chrono
            Chronometer chronometer = (Chronometer) ((Activity)context).getWindow().getDecorView().findViewById(R.id.Chronometer);
            chronometer.stop();
        }
    }
}