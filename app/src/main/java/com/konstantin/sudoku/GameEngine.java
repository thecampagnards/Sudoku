package com.konstantin.sudoku;

import android.content.Context;

import com.konstantin.sudoku.views.sudoku.GameGrid;

/**
 * Created by konstantin on 13/02/17.
 */

public class GameEngine {

    private static final int NB_DELETE = 3;
    private static GameEngine instance;
    private GameGrid grid = null;
    private int selectedPosX = -1, selectedPosY = -1;

    private GameEngine(){}

    public static GameEngine getInstance(){
        // on se fait un petit singleton car le jeu est unique
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context){

        // on genere notre grille
        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();
        // on supprime aleatoirement 10 elements de notre grille
        Sudoku = SudokuGenerator.getInstance().removeElements(Sudoku, NB_DELETE);
        // on init notre grille
        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
    }

    public void createGrid(Context context, int[][] Sudoku){
        // on init notre grille
        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
    }

    public GameGrid getGrid(){
        return grid;
    }

    public void setSelectedPosition( int x , int y ){
        // affecter à une case selectionnée la valeur selectionné pour l'affichage
        selectedPosX = x;
        selectedPosY = y;
        grid.setSelectedItem(selectedPosX,selectedPosY);
    }

    public void setNumber(int number ){
        // on affecte un notre à une case
        if( selectedPosX != -1 && selectedPosY != -1 ){
            grid.setItem(selectedPosX,selectedPosY,number);
        }
        // on check si le jeu est fini
        grid.checkGame();
    }
}
