package com.konstantin.sudoku;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SudokuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        Intent intent = getIntent();

        String code = null;
        try{
            code = intent.getStringExtra(MainActivity.CODE_PARTIE);
        } catch (Exception e){}
        if(code != null){
            int[][] grid;
            try {
                grid = new SudokuApiTask().execute(code).get();
                GameEngine.getInstance().createGrid(this, grid);
                this.setTitle(this.getTitle() + " - " + code);

            } catch (InterruptedException e) {
                Toast.makeText(this, this.getString(R.string.erreur_api), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (ExecutionException e) {
                Toast.makeText(this, this.getString(R.string.erreur_api), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }else{
            GameEngine.getInstance().createGrid(this);
        }
    }

}
