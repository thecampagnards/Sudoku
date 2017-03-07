package com.konstantin.sudoku;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by konstantin on 13/02/17.
 */

public class SudokuGenerator {

    private static SudokuGenerator instance;
    private ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>();
    private Random rand = new Random();

    // encore un singleton car pas besoin de plusieurs instances
    private SudokuGenerator(){}

    public static SudokuGenerator getInstance(){
        if( instance == null ){
            instance = new SudokuGenerator();
        }
        return instance;
    }

    public int[][] generateGrid(){

        // methode pour generer une grille
        int[][] Sudoku = new int[9][9];
        int currentPos = 0;
        // on netoye notre grille etc
        clearGrid(Sudoku);

        while( currentPos < 81 ){

            // si la case est valide
            if( Available.get(currentPos).size() != 0 ){

                // on genere un nouyeau  nombre aleatoire d'une case du tableau
                int i = rand.nextInt(Available.get(currentPos).size());
                // on recupere la valeur de la case du tableau
                int number = Available.get(currentPos).get(i);

                // si pas de conflit
                if( !checkConflict(Sudoku, currentPos , number)){
                    int xPos = currentPos % 9;
                    int yPos = currentPos / 9;

                    // on mets dans notre grille le nombre
                    Sudoku[xPos][yPos] = number;

                    Available.get(currentPos).remove(i);
                    // on passe à la case suivante
                    currentPos++;
                }else{
                    Available.get(currentPos).remove(i);
                }

            }else{
                for( int i = 1 ; i <= 9 ; i++ ){
                    Available.get(currentPos).add(i);
                }
                currentPos--;
            }
        }


        return Sudoku;
    }

    public int[][] removeElements( int[][] Sudoku ){
        int i = 0;

        while( i < 3 ){
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if( Sudoku[x][y] != 0 ){
                Sudoku[x][y] = 0;
                i++;
            }
        }
        return Sudoku;

    }

    // methode pour supprimer des elements de notre grille
    // utilisé apres la generation de la grille
    public int[][] removeElements( int[][] Sudoku , int nb){
        int i = 0;

        while( i < nb ){
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if( Sudoku[x][y] != 0 ){
                Sudoku[x][y] = 0;
                i++;
            }
        }
        return Sudoku;

    }

    // methode pour netoyer l'ensemble
    private void clearGrid(int [][] Sudoku){
        Available.clear();

        for( int y =  0; y < 9 ; y++ ){
            for( int x = 0 ; x < 9 ; x++ ){
                Sudoku[x][y] = -1;
            }
        }

        for( int x = 0 ; x < 81 ; x++ ){
            Available.add(new ArrayList<Integer>());
            for( int i = 1 ; i <= 9 ; i++){
                Available.get(x).add(i);
            }
        }
    }

    // ensemble des methodes pour check le sudoku
    private boolean checkConflict( int[][] Sudoku , int currentPos , final int number){
        int xPos = currentPos % 9;
        int yPos = currentPos / 9;

        if( checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number) ){
            return true;
        }

        return false;
    }

    private boolean checkHorizontalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        for( int x = xPos - 1; x >= 0 ; x-- ){
            if( number == Sudoku[x][yPos]){
                return true;
            }
        }

        return false;
    }

    private boolean checkVerticalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        for( int y = yPos - 1; y >= 0 ; y-- ){
            if( number == Sudoku[xPos][y] ){
                return true;
            }
        }

        return false;
    }

    private boolean checkRegionConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for( int x = xRegion * 3 ; x < xRegion * 3 + 3 ; x++ ){
            for( int y = yRegion * 3 ; y < yRegion * 3 + 3 ; y++ ){
                if( ( x != xPos || y != yPos ) && number == Sudoku[x][y] ){
                    return true;
                }
            }
        }

        return false;
    }
}