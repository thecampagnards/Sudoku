package com.konstantin.sudoku;

import android.content.Context;

import com.konstantin.sudoku.views.sudoku.GameGrid;

/**
 * Created by konstantin on 13/02/17.
 */

public class GameEngine {

    private static GameEngine instance;

    private GameGrid grid = null;

    private int selectedPosX = -1, selectedPosY = -1;

    private GameEngine(){}

    public static GameEngine getInstance(){
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context){

        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();

        Sudoku = SudokuGenerator.getInstance().removeElements(Sudoku, 10);

        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
    }

    public void createGrid(Context context, int[][] Sudoku){

        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
    }

    public GameGrid getGrid(){
        return grid;
    }

    public void setSelectedPosition( int x , int y ){
        selectedPosX = x;
        selectedPosY = y;
        grid.setSelectedItem(selectedPosX,selectedPosY);
    }

    public void setNumber(int number ){
        if( selectedPosX != -1 && selectedPosY != -1 ){
            grid.setItem(selectedPosX,selectedPosY,number);
        }
        grid.checkGame();
    }
}
