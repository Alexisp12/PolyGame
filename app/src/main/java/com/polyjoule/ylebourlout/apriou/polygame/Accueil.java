package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Alexis on 11/08/2017.
 */

public class Accueil extends Activity {
    private static AccueilView accueilView;
    public static final int INCREMENTZOOMPANNEAUNANTES=1;
    public static final int FREQUENCEZOOMNANTES=2;
    public static final float INCREMENTROTATENANTES=0.4f;
    public static final int ROTATENANTESINIT=-12;
    public static final int VITESSEDEPLACEMENTNANTES=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On cré un objet "AccueilView" qui est le code principal du jeu
        accueilView = new AccueilView(this);

        // et on l'affiche.
        setContentView(accueilView);


    }


    @Override
    public void onBackPressed() {
        accueilView = null;
        Intent retourMenuIntent = new Intent(Accueil.this, Accueil.class);
        startActivity(retourMenuIntent);
    }
}