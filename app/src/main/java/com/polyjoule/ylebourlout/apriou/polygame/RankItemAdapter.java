package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

/**
 * Created by Alexis on 09/01/2018.
 */

public class RankItemAdapter extends BaseAdapter {

    // Une liste de personnes
    private List<UserInformation> UsersInfo;
    private URL urlpp=null;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    private Thread thread;


    public RankItemAdapter(Context context, List<UserInformation> usersInfo) {
        mContext = context;
        UsersInfo=usersInfo;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return UsersInfo.size();
    }

    public Object getItem(int position) {
        return UsersInfo.get(position);
    }

    public long getItemId(int position) {
        return position;
    }



    public View getView(final int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (RelativeLayout) mInflater.inflate(R.layout.rank_item, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_rank = (TextView)layoutItem.findViewById(R.id.userRank);
        TextView tv_pseudo = (TextView)layoutItem.findViewById(R.id.userPseudo);
        TextView tv_hs = (TextView) layoutItem.findViewById(R.id.userHsRanked);
        TextView tv_tiret = (TextView) layoutItem.findViewById(R.id.tiret);

        //(3) : Renseignement des valeurs
        if(UsersInfo.get(position)!=null) {
            tv_rank.setText(Integer.toString(position + 1));

            tv_pseudo.setText(UsersInfo.get(position).getPseudo());
            tv_hs.setText(Integer.toString(UsersInfo.get(position).getHighScore()));
//            if(position==0){
//                tv_rank.setTextColor(Color.rgb(201,137,16));
//                tv_pseudo.setTextColor(Color.rgb(201,137,16));
//                tv_hs.setTextColor(Color.rgb(201,137,16));
//                tv_tiret.setTextColor(Color.rgb(201,137,16));
//            } else {
//                if(position==1){
//                    tv_rank.setTextColor(Color.rgb(168,168,168));
//                    tv_pseudo.setTextColor(Color.rgb(168,168,168));
//                    tv_hs.setTextColor(Color.rgb(168,168,168));
//                    tv_tiret.setTextColor(Color.rgb(168,168,168));
//                } else {
//                    if(position==2){
//                        tv_rank.setTextColor(Color.rgb(150,90,56));
//                        tv_pseudo.setTextColor(Color.rgb(150,90,56));
//                        tv_hs.setTextColor(Color.rgb(150,90,56));
//                        tv_tiret.setTextColor(Color.rgb(150,90,56));
//                    } else {
//
//                    }
//                }
//            }


        }
        // Gestion PP
        //iv_pp.setImageBitmap(pp.get(0));//position));

//        //On retourne l'item créé.
        return layoutItem;
    }

}


