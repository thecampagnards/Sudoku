package com.konstantin.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SudokuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        Intent intent = getIntent();

        // on check si on récupère le code api si il y en à un
        String code = null;
        try{
            code = intent.getStringExtra(MainActivity.CODE_PARTIE);
        } catch (Exception e){}

        if(code != null){
            int[][] grid;
            try {
                // on recupere la grid via l'api task
                grid = new SudokuApiTask().execute(code).get();
                GameEngine.getInstance().createGrid(this, grid);
                // on met le code de l'api dans le title
                this.setTitle(this.getTitle() + " - " + code);
            } catch (InterruptedException e) {
                // si il y a une erreur on affiche un message toast d'erreur
                Toast.makeText(this, this.getString(R.string.erreur_api), Toast.LENGTH_LONG).show();
            } catch (ExecutionException e) {
                // si il y a une erreur on affiche un message toast d'erreur
                Toast.makeText(this, this.getString(R.string.erreur_api), Toast.LENGTH_LONG).show();
            }
        }else{
            // sinon on creer une grille random
            GameEngine.getInstance().createGrid(this);
        }

        // on prepare le chrono
        Chronometer chronometer = (Chronometer) findViewById(R.id.Chronometer);
        chronometer.setFormat(this.getString(R.string.chrono_format));
        chronometer.start();
    }

}
