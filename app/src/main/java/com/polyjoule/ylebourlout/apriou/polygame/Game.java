package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.SETS;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.databaseReference;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.firebaseAuth;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.userInfo;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.users;


/**
 * Created by Alexis on 19/07/2017.
 */

public class Game extends Activity {
    private static GameView gameView;
    private static Context context;
    private static Context This;
    public static int nbVie = 3;
    public static int incrementCarburant = 10;
    public final static int GAINCARBURANT = 100; // En pourcentage !!! anciennement  8
    public final static int PERTECARBURANT = 12; // En pourcentage !!! anciennement 6
    public final static int DEPLACEMENTBG = 9;
    //public final static int VITESSEDEPLACEMENTENNEMI=7;
    //public final static int VITESSEDEPLACEMENTENNEMI2=VITESSEDEPLACEMENTENNEMI;//+VITESSEDEPLACEMENTENNEMI/2;
    //public final static int VITESSEDEPLACEMENTENNEMI3=VITESSEDEPLACEMENTENNEMI;//-VITESSEDEPLACEMENTENNEMI;
    //public final static int VITESSEDEPLACEMENTENNEMI4=VITESSEDEPLACEMENTENNEMI;//+3*VITESSEDEPLACEMENTENNEMI/4;
    public final static int DUREEAFFICHAGEGO = 100;
    public final static int COEFVEHICULESEVITES=10; // Rajoute 10 au score pour chaque véhicule évité
    public final static double RATIOTABLEAUSCORE=1.7;
    public final static double RATIOSTART=2.08;
    public final static int DUREEAFFICHAGETOTALPANNEAUX=3; //6 //3
    public final static int DUREEEXPLOFINAL=0; // 70 origine
    public final static int NBENNEMIPARCOLONNEMAX=3;
    public final static int NBVEHICULESENNEMIS=6;
    public static Boolean pause=false;
    private static MediaPlayer fondSonore = null;
    public static Boolean alertDialogDone = false;
    public static int highScore = -1;
    public static Toast toast;
    public static int rangJoueur;
    private static Activity mActivity;

    //firebase auth object
    //private static FirebaseAuth firebaseAuth;
    //defining a database reference
    //private static DatabaseReference databaseReference;
    //private static UserInformation[] users;
    // private static String[] userIUD;

    public static List<String> userUID = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On cré un objet "GameView" qui est le code principal du jeu
        gameView = new GameView(this);

        // et on l'affiche.
        setContentView(gameView);

        context = this.getApplicationContext();
        This = this;
        mActivity=this;

        fondSonore = null;
        playSound(R.raw.son);


        if(userInfo!=null) {
            highScore = userInfo.getHighScore();
        }

        if(firebaseAuth==null) {
            SharedPreferences settings = getSharedPreferences(SETS, 0);
            highScore = settings.getInt("highscore", 0);
        }

        //users = new UserInformation[100];
        // userIUD = new String [100];

        //Game.saveUserInformation(userInfo);


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

    public static void restart() {
        if(fondSonore!=null) {
            fondSonore.stop();
        }
        fondSonore = null;
        //gameView = null;

        mActivity.finish();
        Intent restart = new Intent(This, Game.class);
        This.startActivity(restart);
    }

    public static void update(int highScore) {
        SharedPreferences settings = This.getSharedPreferences(SETS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("highScore", highScore);
        editor.commit();
    }

    public static void pauseMusique() {
        if (fondSonore != null) {
            fondSonore.pause();
        }
    }

    public static void startMusique() {
        if(fondSonore!=null) {
            fondSonore.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (fondSonore != null) {
            pause=true;
            fondSonore.pause();
        }
    }

    public static void saveUserInformation(int hs) {
        //getting the current logged in user
        FirebaseUser usr = firebaseAuth.getCurrentUser();

        //saving data to firebase database
        /*
        * first we are creating a new child in firebase with the
        * unique id of logged in user
        * and then for that user under the unique id we are saving data
        * for saving data we are using setvalue method this method takes a normal java object
        * */
        if (usr != null) {
            databaseReference.child("users").child(usr.getUid()).child("highScore").setValue(hs);
            Log.d("usrnotnull","oui");
            Log.d("userUid",usr.getUid());
        }


        // Toast ?
    }

    public static void pullHighScore() {
        if(firebaseAuth!=null) {
            final FirebaseUser usr = firebaseAuth.getCurrentUser();
            // Get a reference to our posts
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReferenceFromUrl(databaseReference.toString());

            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    users.clear();
                    for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                        if (!users.contains(eventSnapshot.getValue(UserInformation.class))) {
                            users.add(eventSnapshot.getValue(UserInformation.class));
                        } else {
                            for (int i = 0; i < users.size(); i++) {
                                if (users.get(i).getHighScore() != eventSnapshot.getValue(UserInformation.class).getHighScore()) {
                                    users.remove(i);
                                    users.add(eventSnapshot.getValue(UserInformation.class));
                                }
                            }

                        }

                    }

                    Collections.sort(users, new UsersComparator());

//                    if (users != null && userInfo != null) {
//                        for (int i = 0; i < users.size(); i++) {
////                    Log.e("users" + i, users.get(i).getPseudo());
//                            if (userInfo.getPseudo() != null && users.get(i).getPseudo() != null) {
//                                if ((users.get(i).getPseudo()).equals(userInfo.getPseudo())) {
//                                    rangJoueur = i + 1;
//                                }
//                            }
//                        }
//                    }


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public static void toastHS() {
        Handler makeToastHandler = new Handler(Looper.getMainLooper());

        makeToastHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                }
                if (users != null) {

                    toast = Toast.makeText(This, "New record !!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    public static void toastNoMoreCarbu() {
        Handler makeToastHandler = new Handler(Looper.getMainLooper());

        makeToastHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                }
                if (users != null) {

                    toast = Toast.makeText(This, "You don't have enough Hydrogen :(", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    public static void seeClassement(){
//        gameView = null;
//        pauseMusique();
//        fondSonore = null;
//
//
//        //Intent intent = getIntent();
//        mActivity.finish();


        Intent seeClassementIntent = new Intent(This, Classement_Activity.class);

        This.startActivity(seeClassementIntent);
    }

    @Override
    public void onBackPressed() {
        gameView = null;
        pauseMusique();
        fondSonore = null;


        //Intent intent = getIntent();
        mActivity.finish();

        Intent retourMenuIntent = new Intent(Game.this, Accueil.class);

        startActivity(retourMenuIntent);
    }
}
