package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alexis on 15/02/2018.
 */

public class Palmares extends Activity  {

    private TextView nomTournoi1;
    private TextView nomTournoi2;
    private TextView nomTournoi3;
    private TextView valeurRecord1;
    private TextView valeurRecord2;
    private TextView valeurRecord3;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView imagePalma;
    private TextView textPalma;
    private TextView anneePalma;
    private int anneeTolook=2017;
    private String textPalmaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palmares);

        nomTournoi1 = (TextView) findViewById(R.id.NomTournoi1);
        nomTournoi2 = (TextView) findViewById(R.id.NomTournoi2);
        nomTournoi3 = (TextView) findViewById(R.id.NomTournoi3);

        valeurRecord1 = (TextView) findViewById(R.id.ValeurRecord1);
        valeurRecord2 = (TextView) findViewById(R.id.ValeurRecord2);
        valeurRecord3 = (TextView) findViewById(R.id.ValeurRecord3);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        imagePalma =(ImageView) findViewById(R.id.imagePalma);

        textPalma = (TextView) findViewById(R.id.textePalma);

        anneePalma = (TextView) findViewById(R.id.anneePalma);


        Bitmap orTrophy = BitmapFactory.decodeResource(getResources(), R.drawable.ortrophy);
        Bitmap argentTrophy = BitmapFactory.decodeResource(getResources(), R.drawable.argenttrophy);
        Bitmap bronzeTrophy = BitmapFactory.decodeResource(getResources(), R.drawable.bronzetrophy);



        switch(Integer.toString(anneeTolook)){
            case "2017":
                anneeTolook=2017;
                textPalmaString="For this year, the team tried to keep his rank with Cityjoule at the Shell Eco Marathon. But with old fuel cells, had to settle for second place just behind Green Team Twente. Moreover, just after winning the Drive Europe Championship, Cityjoule achivied the second best time for qualification of Driver World Championship. It's this time who has set the final ranking, because the race having not been completed due to bad weather in London";

                nomTournoi1.setText("EducEco\nUrban Concept\nHydrogen");
                valeurRecord1.setText("949 Km/Ige");
                image1.setImageBitmap(orTrophy);

                nomTournoi2.setText("SEM\nUrban Concept\nHydrogen");
                valeurRecord2.setText("241,3 km/m3");
                image2.setImageBitmap(argentTrophy);

                nomTournoi3.setText("Driver World\nChampionship\n");
                valeurRecord3.setText("");
                image3.setImageBitmap(argentTrophy);

                Bitmap photo2017 = BitmapFactory.decodeResource(getResources(), R.drawable.imagetemp);
                imagePalma.setImageBitmap(photo2017);


                break;
            case "2016":
                anneeTolook=2016;
                textPalmaString="";


                break;
            case "2015":
                anneeTolook=2015;
                textPalmaString="";

                break;
            case "2014":
                anneeTolook=2014;
                textPalmaString="";

                break;
            case "2013":
                anneeTolook=2013;
                textPalmaString="";

                break;
        }

        anneePalma.setText(Integer.toString(anneeTolook));
        textPalma.setText(textPalmaString);
        textPalma.setGravity(Gravity.CENTER_HORIZONTAL);



        nomTournoi1.setGravity(Gravity.CENTER_HORIZONTAL);
        nomTournoi2.setGravity(Gravity.CENTER_HORIZONTAL);
        nomTournoi3.setGravity(Gravity.CENTER_HORIZONTAL);





    }

}
