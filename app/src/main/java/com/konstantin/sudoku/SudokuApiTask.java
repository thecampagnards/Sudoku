package com.konstantin.sudoku;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by konstantin on 06/03/17.
 */

public class SudokuApiTask extends AsyncTask<String, Void, int[][]> {

    @Override
    protected int[][] doInBackground(String... params) {

        HttpURLConnection con = null;

        try {
            // url de l'api on ajoute le code de grille dans l'url
            URL url = new URL("http://sudoku-generator.konstantin-sidorenko.fr?" + params[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // on check le status de la requete
            switch (con.getResponseCode()) {
                // 200 ou 201 ok
                case 200 & 201:
                    // on traite le json
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }

                    int[][] result = new int [10][10];
                    JSONArray jsonArray = new JSONArray(sb.toString());
                    // on parcours le json pour le mettre dans notre tableau de int
                    if (jsonArray != null) {
                        for (int i = 0 ; i < 9 ; i++){
                            JSONArray colonne = new JSONArray(jsonArray.get(i).toString());
                            for (int j = 0 ; j < 9 ; j++) {
                                // si on a un case non null
                                if(colonne.get(j).toString() != "null"){
                                    result[i][j] = Integer.parseInt(colonne.get(j).toString());
                                }
                            }
                        }
                        return result;
                    }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            // on ferme la connexion à la fin
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
