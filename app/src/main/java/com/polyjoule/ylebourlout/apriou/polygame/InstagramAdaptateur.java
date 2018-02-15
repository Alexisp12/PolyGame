package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.net.URL;
import java.util.List;

/**
 * Created by Alexis on 14/02/2018.
 */

public class InstagramAdaptateur extends BaseAdapter {

    // Une liste de personnes
    private List<String> pseudo;
    private List<String> status;
    private List<Bitmap> pp;
    private URL urlpp=null;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    private Thread thread;


    public InstagramAdaptateur(Context context, List<Bitmap> aPP) {
        mContext = context;
        //pseudo = aPseudo;
        //status = aStatus;
        pp=aPP;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return pp.size();
    }

    public Object getItem(int position) {
        return pp.get(position);
    }

    public long getItemId(int position) {
        return position;
    }



    public View getView(final int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (RelativeLayout) mInflater.inflate(R.layout.instagram_update, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        //TextView tv_pseudo = (TextView)layoutItem.findViewById(R.id.userPseudo);
        //TextView tv_status = (TextView)layoutItem.findViewById(R.id.userStatus);
        ImageView iv_pp = (ImageView) layoutItem.findViewById(R.id.userImg);

        //(3) : Renseignement des valeurs
        //tv_pseudo.setText(pseudo.get(position));
        //tv_status.setText(status.get(position));


        // Gestion PP
        iv_pp.setImageBitmap(pp.get(position));

//        //On retourne l'item créé.
        return layoutItem;
    }

}


