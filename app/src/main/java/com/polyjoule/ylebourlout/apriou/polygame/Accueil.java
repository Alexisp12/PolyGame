package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Alexis on 11/08/2017.
 */

public class Accueil extends Activity {
    private static Context This;
    private static Activity mActivity;
    private static AccueilView accueilView;
    public static final int INCREMENTZOOMPANNEAUNANTES=1;
    public static final int FREQUENCEZOOMNANTES=2;
    public static final float INCREMENTROTATENANTES=0.4f;
    public static final int ROTATENANTESINIT=-12;
    public static final int VITESSEDEPLACEMENTNANTES=2;
    private static Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On cré un objet "AccueilView" qui est le code principal du jeu
        accueilView = new AccueilView(this);

        // et on l'affiche.
        setContentView(accueilView);

        This=this;
        mActivity=this;


    }

    public static void game(){
        mActivity.finish();
        Intent gameIntent = new Intent(This, Game.class);
        This.startActivity(gameIntent);
    }
    public static void param(){
        mActivity.finish();
        Intent loginIntent = new Intent(This, Login.class);
        This.startActivity(loginIntent);
    }
    public static void social(){
        Handler makeToastHandler = new Handler(Looper.getMainLooper());

        makeToastHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(This, "En construction.", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }
    public static void palmares(){
        Handler makeToastHandler = new Handler(Looper.getMainLooper());

        makeToastHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                }


                toast = Toast.makeText(This, "En construction.", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        accueilView = null;
        finish();
        Intent retourMenuIntent = new Intent(Accueil.this, Accueil.class);
        startActivity(retourMenuIntent);
    }
}