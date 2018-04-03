package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Alexis on 15/02/2018.
 */

public class Palmares extends Activity  {

    private TextView nomTournoi1;
    private TextView nomTournoi2;
    private TextView nomTournoi3;
    private TextView nomTournoi1b;
    private TextView nomTournoi2b;
    private TextView valeurRecord1;
    private TextView valeurRecord2;
    private TextView valeurRecord3;
    private TextView valeurRecord1b;
    private TextView valeurRecord2b;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image1b;
    private ImageView image2b;
    private ImageView imagePalma;
    private ImageView imagePalmab;
    private TextView textPalma;
    private TextView textPalmab;
    private TextView anneePalma;
    private TextView anneePalmab;
    private int anneeTolook=2017;
    private String textPalmaString;
    private String textPalmabString;
    private LinearLayout clickDroit;
    private LinearLayout clickGauche;
    private LinearLayout LLpalmaresb;
    private LinearLayout LLpalmares;
    private final int ANNEEMAX=2017;
    private final int ANNEEMIN=2012;
    private Bitmap photo2017;
    private Bitmap photo2016;
    private Bitmap photo2015;
    private Bitmap photo2014;
    private Bitmap photo2013;
    private Bitmap photo2012;
    private Bitmap orTrophy;
    private Bitmap argentTrophy;
    private Bitmap bronzeTrophy;
    private Bitmap WRPIC;
    private Bitmap WRPIC2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palmares);

        clickDroit = (LinearLayout) findViewById(R.id.clickDroitLL);
        clickGauche = (LinearLayout) findViewById(R.id.clickGaucheLL);
        LLpalmaresb = (LinearLayout) findViewById(R.id.LLPalmaresCoupeb);
        LLpalmares = (LinearLayout) findViewById(R.id.LLPalmaresCoupe);


        nomTournoi1 = (TextView) findViewById(R.id.NomTournoi1);
        nomTournoi2 = (TextView) findViewById(R.id.NomTournoi2);
        nomTournoi3 = (TextView) findViewById(R.id.NomTournoi3);
        nomTournoi1b = (TextView) findViewById(R.id.NomTournoi1b);
        nomTournoi2b = (TextView) findViewById(R.id.NomTournoi2b);

        valeurRecord1 = (TextView) findViewById(R.id.ValeurRecord1);
        valeurRecord2 = (TextView) findViewById(R.id.ValeurRecord2);
        valeurRecord3 = (TextView) findViewById(R.id.ValeurRecord3);
        valeurRecord1b = (TextView) findViewById(R.id.ValeurRecord1b);
        valeurRecord2b = (TextView) findViewById(R.id.ValeurRecord2b);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image1b = (ImageView) findViewById(R.id.image1b);
        image2b = (ImageView) findViewById(R.id.image2b);
        imagePalma =(ImageView) findViewById(R.id.imagePalma);
        imagePalmab =(ImageView) findViewById(R.id.imagePalmab);


        textPalma = (TextView) findViewById(R.id.textePalma);
        //textPalmab = (TextView) findViewById(R.id.textePalmab);

        anneePalma = (TextView) findViewById(R.id.anneePalma);
        anneePalmab = (TextView) findViewById(R.id.anneePalmab);


        orTrophy = BitmapFactory.decodeResource(getResources(), R.drawable.ortrophy);
        argentTrophy = BitmapFactory.decodeResource(getResources(), R.drawable.argenttrophy);
        bronzeTrophy = BitmapFactory.decodeResource(getResources(), R.drawable.bronzetrophy);
        WRPIC = BitmapFactory.decodeResource(getResources(), R.drawable.wor2);
        WRPIC2 = BitmapFactory.decodeResource(getResources(), R.drawable.wor2);



        clickDroit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View droitView) {
                if(anneeTolook==ANNEEMAX){

                } else {
                    anneeTolook++;
                    updateyear();
                    Log.d("AnneeToLook",Integer.toString(anneeTolook));
                }
            }
        });

        clickGauche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View gaucheView) {
                if(anneeTolook==ANNEEMIN){

                } else {
                    anneeTolook--;
                    updateyear();
                    Log.d("AnneeToLook",Integer.toString(anneeTolook));
                }
            }
        });

        photo2017 = BitmapFactory.decodeResource(getResources(), R.drawable.photof);
        photo2016 = BitmapFactory.decodeResource(getResources(), R.drawable.photoe);
        photo2015 = BitmapFactory.decodeResource(getResources(), R.drawable.photod);
        photo2014 = BitmapFactory.decodeResource(getResources(), R.drawable.photoc);
        photo2013 = BitmapFactory.decodeResource(getResources(), R.drawable.photob);
        photo2012 = BitmapFactory.decodeResource(getResources(), R.drawable.photoa);


        updateyear();




    }

    public void updateyear(){
        switch(Integer.toString(anneeTolook)){
            case "2017":


                LLpalmaresb.setVisibility(View.GONE);
                LLpalmares.setVisibility(View.VISIBLE);

                imagePalmab.setVisibility(View.GONE);
                imagePalma.setVisibility(View.VISIBLE);

//                textPalmab.setVisibility(View.GONE);
                textPalma.setVisibility(View.VISIBLE);

                textPalmabString="";

                anneeTolook=2017;
                textPalmaString="For this year, the team tried to keep his rank with Cityjoule at the Shell Eco Marathon. But with old fuel cells, had to settle for second place just behind Green Team Twente. Moreover, just after winning the Drive Europe Championship, Cityjoule achivied the second best time for qualification of Driver World Championship. It's this time who has set the final ranking, because the race having not been completed due to bad weather in London";

                nomTournoi1.setText("EducEco\nUrban Concept\nHydrogen");
                valeurRecord1.setText("949 Km/lee");
                image1.setImageBitmap(orTrophy);

                nomTournoi2.setText("SEM\nUrban Concept\nHydrogen");
                valeurRecord2.setText("800 km/lee");
                image2.setImageBitmap(argentTrophy);

                nomTournoi3.setText("Driver World\nChampionship\n");
                valeurRecord3.setText("");
                image3.setImageBitmap(argentTrophy);


                imagePalma.setImageBitmap(photo2017);


                break;
            case "2016":


                LLpalmaresb.setVisibility(View.VISIBLE);
                LLpalmares.setVisibility(View.GONE);

                imagePalmab.setVisibility(View.VISIBLE);
                imagePalma.setVisibility(View.GONE);

//                textPalmab.setVisibility(View.VISIBLE);
                textPalma.setVisibility(View.GONE);

                textPalmaString="";

                anneeTolook=2016;
                textPalmabString="";

                nomTournoi1b.setText("EducEco\nUrban Concept\nHydrogen");
                valeurRecord1b.setText("1205 km/lee");
                image1b.setImageBitmap(orTrophy);

                nomTournoi2b.setText("Shell Eco Marathon\nUrban Concept\n Hydrogen");
                valeurRecord2b.setText("1158 km/lee");
                image2b.setImageBitmap(orTrophy);


                imagePalmab.setImageBitmap(photo2016);


                break;
            case "2015":


                LLpalmaresb.setVisibility(View.VISIBLE);
                LLpalmares.setVisibility(View.GONE);

                imagePalmab.setVisibility(View.VISIBLE);
                imagePalma.setVisibility(View.GONE);

           //     textPalmab.setVisibility(View.VISIBLE);
                textPalma.setVisibility(View.GONE);

                textPalmaString="";


                anneeTolook=2015;
                textPalmabString="";

                nomTournoi1b.setText("EducEco\nUrban Concept\nSolar pannel");
                valeurRecord1b.setText("12 788 km/lee");
                image1b.setImageBitmap(WRPIC);

                nomTournoi2b.setText("Shell Eco Marathon\nUrban Concept\nHydrogen");
                valeurRecord2b.setText("1114 km/lee");
                image2b.setImageBitmap(orTrophy);


                imagePalmab.setImageBitmap(photo2015);

                break;
            case "2014":

                LLpalmaresb.setVisibility(View.VISIBLE);
                LLpalmares.setVisibility(View.GONE);

                imagePalmab.setVisibility(View.VISIBLE);
                imagePalma.setVisibility(View.GONE);

               // textPalmab.setVisibility(View.VISIBLE);
                textPalma.setVisibility(View.GONE);

                textPalmaString="";

                anneeTolook=2014;
                textPalmabString="";

                nomTournoi1b.setText("EducEco\nPrototype\nHydrogen");
                valeurRecord1b.setText("6329 km/lee");
                image1b.setImageBitmap(WRPIC);

                nomTournoi2b.setText("Shell Eco Marathon\nUrban Concept\nHydrogen");
                valeurRecord2b.setText("1354 km/lee");
                image2b.setImageBitmap(WRPIC2);

                imagePalmab.setImageBitmap(photo2014);

                break;
            case "2013":
                LLpalmaresb.setVisibility(View.VISIBLE);
                LLpalmares.setVisibility(View.GONE);
                imagePalmab.setVisibility(View.VISIBLE);
                imagePalma.setVisibility(View.GONE);

               // textPalmab.setVisibility(View.VISIBLE);
                textPalma.setVisibility(View.GONE);

                textPalmaString="";

                anneeTolook=2013;
                textPalmabString="";

                nomTournoi1b.setText("EducEco\nUrban Concept\nHydrogen");
                valeurRecord1b.setText("1430 km/lee");
                image1b.setImageBitmap(orTrophy);

                nomTournoi2b.setText("Shell Eco Marathon\nUrban Concept\nHydrogen");
                valeurRecord2b.setText("1311 km/lee");
                image2b.setImageBitmap(orTrophy);


                imagePalmab.setImageBitmap(photo2013);
                break;
            case "2012":


                LLpalmaresb.setVisibility(View.VISIBLE);
                LLpalmares.setVisibility(View.GONE);
                imagePalmab.setVisibility(View.VISIBLE);
                imagePalma.setVisibility(View.GONE);
               // textPalmab.setVisibility(View.VISIBLE);
                textPalma.setVisibility(View.GONE);

                textPalmaString="";

                anneeTolook=2012;
                textPalmabString="";

                nomTournoi1b.setText("EducEco\nPrototype\nAll-electric");
                valeurRecord1b.setText("10 017 km/lee");
                image1b.setImageBitmap(WRPIC);

                nomTournoi2b.setText("Shell Eco Marathon\nPrototype\nHydrogen");
                valeurRecord2b.setText("4810 km/lee");
                image2b.setImageBitmap(orTrophy);


                imagePalmab.setImageBitmap(photo2012);
                break;
        }

        anneePalma.setText(Integer.toString(anneeTolook));
        anneePalmab.setText(Integer.toString(anneeTolook));
        textPalma.setText(textPalmaString);
        textPalma.setGravity(Gravity.CENTER_HORIZONTAL);
       // textPalmab.setText(textPalmaString);
        //textPalmab.setGravity(Gravity.CENTER_HORIZONTAL);






        nomTournoi1.setGravity(Gravity.CENTER_HORIZONTAL);
        nomTournoi2.setGravity(Gravity.CENTER_HORIZONTAL);
        nomTournoi3.setGravity(Gravity.CENTER_HORIZONTAL);

        nomTournoi1b.setGravity(Gravity.CENTER_HORIZONTAL);
        nomTournoi2b.setGravity(Gravity.CENTER_HORIZONTAL);

        valeurRecord1.setGravity(Gravity.CENTER_HORIZONTAL);
        valeurRecord2.setGravity(Gravity.CENTER_HORIZONTAL);
        valeurRecord3.setGravity(Gravity.CENTER_HORIZONTAL);
        valeurRecord1b.setGravity(Gravity.CENTER_HORIZONTAL);
        valeurRecord2b.setGravity(Gravity.CENTER_HORIZONTAL);

        LLpalmaresb.setGravity(Gravity.CENTER_HORIZONTAL);
        LLpalmares.setGravity(Gravity.CENTER_HORIZONTAL);



    }

    @Override
    public void onBackPressed() {
        Intent retourMenuIntent = new Intent(Palmares.this, Accueil.class);

        startActivity(retourMenuIntent);
    }

}
