package com.konstantin.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // corespond à la valeur du code pour recuperer la grille sur l'api
    public final static String CODE_PARTIE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSudokuActivity(View view) {
        // check du input code api
        EditText editText = (EditText)findViewById(R.id.partie_api_code);
        if(view.getId() == R.id.button_api && editText.getText().toString().isEmpty()){
            editText.setError(getString(R.string.partie_api_code_error));
            return;
        }
        // on prepare l'intent de la sudoku activity
        Intent intent = new Intent(this, SudokuActivity.class);
        if(view.getId() == R.id.button_api){
            intent.putExtra(CODE_PARTIE, editText.getText().toString());
        }
        startActivity(intent);
    }
}
