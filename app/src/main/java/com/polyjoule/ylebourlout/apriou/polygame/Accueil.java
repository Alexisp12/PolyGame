package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexis on 11/08/2017.
 */

public class Accueil extends Activity {
    public static String SETS="SETS";
    private static Context This;
    private static Activity mActivity;
    private static AccueilView accueilView;
    public static final int INCREMENTZOOMPANNEAUNANTES=1;
    public static final int FREQUENCEZOOMNANTES=2;
    public static final float INCREMENTROTATENANTES=0.4f;
    public static final int ROTATENANTESINIT=-12;
    public static final int VITESSEDEPLACEMENTNANTES=2;
    private static Toast toast;
    public static UserInformation userInfo;
    //firebase auth object
    public static FirebaseAuth firebaseAuth;
    //defining a database reference
    public static DatabaseReference databaseReference;
    public static List<UserInformation> users = new ArrayList<UserInformation>();

    private static MediaPlayer fondSonore = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On cré un objet "AccueilView" qui est le code principal du jeu
        accueilView = new AccueilView(this);

        // et on l'affiche.
        setContentView(accueilView);

        This=this;
        mActivity=this;

        fondSonore = null;
        playSound(R.raw.accueil);

        userInfo = new UserInformation();

        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        recupData();


    }
    private void playSound(int resId) {
        if (fondSonore != null) {
            fondSonore.stop();
            fondSonore.release();
        }
        fondSonore = MediaPlayer.create(this, resId);
        fondSonore.setLooping(true);

        fondSonore.start();
    }

    public static void game(){

        recupData();

        if(firebaseAuth.getCurrentUser()==null){
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(This, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(This);
            }
            builder.setTitle("Authentification")
                    //.setMessage("Do you want to log in ?")
                    .setMessage("It is possible to play being disconected but you will not be able to see your rank :-)")
                    .setPositiveButton("Log in", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(fondSonore!=null) {
                                fondSonore.stop();
                            }
                            fondSonore = null;
                            mActivity.finish();
                            Intent loginIntent = new Intent(This, Login.class);
                            This.startActivity(loginIntent);
                        }
                    })
                    .setNegativeButton("Play", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(fondSonore!=null) {
                                fondSonore.stop();
                            }
                            fondSonore = null;
                            mActivity.finish();
                            Intent gameIntent = new Intent(This, Game.class);
                            This.startActivity(gameIntent);
                        }
                    })
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            SharedPreferences settings = This.getSharedPreferences(SETS, 0);

            if(userInfo!=null) {
                if(userInfo.getPseudo()==null) {
                    userInfo.setPseudo(settings.getString("pseudo", ""));
                    Log.d("pseudoNull","oui");
                }
                if(userInfo.getHighScore()==-1) {
                    userInfo.setHighScore(settings.getInt("highScore", -1));
                    Log.d("highscore-1","oui");
                }
            }
            if(fondSonore!=null) {
                fondSonore.stop();
            }
            fondSonore = null;
            mActivity.finish();
            Intent gameIntent = new Intent(This, Game.class);
            This.startActivity(gameIntent);
        }





    }
    public static void param(){

        if(fondSonore!=null) {
            fondSonore.stop();
        }
        fondSonore = null;
        mActivity.finish();
        Intent loginIntent = new Intent(This, Login.class);
        This.startActivity(loginIntent);
    }
    public static void social(){

        if(fondSonore!=null) {
            fondSonore.stop();
        }
        fondSonore = null;
        mActivity.finish();
        Intent socialIntent = new Intent(This, SocialActivity.class);
        This.startActivity(socialIntent);

    }
    public static void palmares(){
        if(fondSonore!=null) {
            fondSonore.stop();
        }
        fondSonore = null;
        mActivity.finish();
        Intent socialIntent = new Intent(This, Palmares.class);
        This.startActivity(socialIntent);

//        Handler makeToastHandler = new Handler(Looper.getMainLooper());
//
//        makeToastHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (toast != null) {
//                    toast.cancel();
//                }
//
//
//                toast = Toast.makeText(This, "En construction.", Toast.LENGTH_SHORT);
//                toast.show();
//
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//        accueilView = null;
//        finish();
//        Intent retourMenuIntent = new Intent(Accueil.this, Accueil.class);
//        startActivity(retourMenuIntent);
//    }
    public static void recupData(){
        final FirebaseUser usr = firebaseAuth.getCurrentUser();
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl(databaseReference.toString());

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(usr!=null) {
                    userInfo = dataSnapshot.child("users").child(usr.getUid()).getValue(UserInformation.class);
                }

                if(userInfo!=null) {
                    if (userInfo.getPseudo() == null) {
                        SharedPreferences settings = This.getSharedPreferences(SETS, 0);
                        userInfo.setPseudo(settings.getString("pseudo", ""));
                        userInfo.setHighScore(settings.getInt("highScore", 0));
                    }

                    Log.d("UserInfoPseudo", userInfo.getPseudo());
                }


//                    if((userInfo.getPseudo()).equals("")) {
//                        SharedPreferences settings = getSharedPreferences(SETS, 0);
//                        userInfo.setPseudo(settings.getString("pseudo", ""));
//                        userInfo.setHighScore(settings.getInt("highScore", 0));
//                    }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void onResume() {
        super.onResume();

        fondSonore.start();
    }

    @Override
    public void onPause() {
        super.onPause();

        if(fondSonore!=null) {
            fondSonore.pause();
        }
        //fondSonore = null;

    }
}