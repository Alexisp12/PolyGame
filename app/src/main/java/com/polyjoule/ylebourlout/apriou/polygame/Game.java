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

import static com.polyjoule.ylebourlout.apriou.polygame.Menu.SETS;
import static com.polyjoule.ylebourlout.apriou.polygame.Menu.databaseReference;
import static com.polyjoule.ylebourlout.apriou.polygame.Menu.firebaseAuth;
import static com.polyjoule.ylebourlout.apriou.polygame.Menu.userInfo;


/**
 * Created by Alexis on 19/07/2017.
 */

public class Game extends Activity {
    private static GameView gameView;
    private static Context context;
    private static Context This;
    public static int nbVie = 3;
    public static int incrementCarburant = 10;
    public final static int GAINCARBURANT = 8; // En pourcentage !!! 10 à l'origine
    public final static int PERTECARBURANT = 6; // En pourcentage !!! 2 à l'orgine
    public final static int DEPLACEMENTBG = 11;
    public final static int DUREEAFFICHAGEGO = 100;
    public final static int COEFVEHICULESEVITES=10;
    private static MediaPlayer fondSonore = null;
    public static Boolean alertDialogDone = false;
    public static int highScore = -1;
    public static Toast toast;
    public static int rangJoueur;


    //firebase auth object
    //private static FirebaseAuth firebaseAuth;
    //defining a database reference
    //private static DatabaseReference databaseReference;
    //private static UserInformation[] users;
    // private static String[] userIUD;
    public static List<UserInformation> users = new ArrayList<UserInformation>();
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

        fondSonore = null;
        playSound(R.raw.son);



        highScore=userInfo.getHighScore();

        if(firebaseAuth.getCurrentUser()==null) {
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
        gameView = null;

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
            fondSonore.stop();
            fondSonore.release();
        }
    }

    public static void saveUserInformation(UserInformation user) {
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
            databaseReference.child("users").child(usr.getUid()).setValue(user);
            Log.d("usrnotnull","oui");
            Log.d("userUid",usr.getUid());
        }

        // Toast ?
    }

    public static void pullHighScore() {
        final FirebaseUser usr = firebaseAuth.getCurrentUser();
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl(databaseReference.toString());

//        // Attach a listener to read the data at our posts reference
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //users.clear();
//                //userUID.clear();
//                //Post post = dataSnapshot.getValue(Post.class);
//                Log.d("childrenCount",Long.toString(dataSnapshot.child("users").getChildrenCount()));
//
//                List<String> userUIDTempo = new ArrayList<String>();
//
//
//                while(dataSnapshot.child("users").getChildren().iterator().hasNext()) {
//                    userUIDTempo.add(dataSnapshot.child("users").getChildren().iterator().next().getKey());
//                    Log.d("while","while");
//                }
//
//                for(int k=0;k<userUIDTempo.size();k++){
//                    Log.d("userUIDTempo",userUIDTempo.get(k));
//                }
//
//                //userUID.add(dataSnapshot.child("users").getChildren().iterator().next().getKey());
//
//
//
//                for (int i = 0; i < dataSnapshot.child("users").getChildrenCount(); i++) {
//
//
//
//
//                        Log.d("userUIDsize",Integer.toString(userUIDTempo.size()));
//                        Log.d("userUID"+i,userUIDTempo.get(i));
//                        userUID.add(userUIDTempo.get(i));
//
//                        Log.d("contains",userUIDTempo.get(i)+" "+userUID.get(0));
//
////                    users.get(i).setPseudo(dataSnapshot.child("users").getValue(UserInformation.class).pseudo);
////                    users.get(i).setHighScore(dataSnapshot.child("users").getValue(UserInformation.class).highScore);
////                    Log.d("users",dataSnapshot.child("users").getValue().toString());
////                    Log.d("pseudo",dataSnapshot.child("users").getValue(UserInformation.class).pseudo);
//                    if(userUID.size()!=0) {
//                        // for (int j = 0; j < userUID.size(); j++) {
//                        Log.d("onDataChange", "here");
//                        Log.d("onDataChange", dataSnapshot.child("users").child(userUID.get(i)).getValue(UserInformation.class).getPseudo());
//                        if(users.size()!=0) {
//                            if (!users.get(i).getPseudo().equals((dataSnapshot.child("users").child(userUID.get(i)).getValue(UserInformation.class).pseudo))) {
//
//                                    Log.d("contains2", dataSnapshot.child("users").child(userUID.get(i)).getValue().toString() + " " + users.get(i));
//
//                                users.add(dataSnapshot.child("users").child(userUID.get(i)).getValue(UserInformation.class));
//                            } else {
//                                if(users.get(i).getHighScore()!=((dataSnapshot.child("users").child(userUID.get(i)).getValue(UserInformation.class).highScore))){
//                                    users.remove(i);
//                                    users.add(dataSnapshot.child("users").child(userUID.get(i)).getValue(UserInformation.class));
//                                }
//                            }
//                        } else {
//                            users.add(dataSnapshot.child("users").child(userUID.get(i)).getValue(UserInformation.class));
//                        }
//                        //}
//                    }
//                }
//
//
//                    // trie !!!
//
//               // makeToast();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                users.clear();
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {

                    if (users.size() != 0) {
                        for (int i = 0; i < users.size(); i++) {
                            if (!(users.get(i).getPseudo()).equals(eventSnapshot.getValue(UserInformation.class).pseudo)) {
                                users.add(eventSnapshot.getValue(UserInformation.class));
                            } else {
                                if (users.get(i).getHighScore() != (eventSnapshot.getValue(UserInformation.class).highScore)) {
                                    users.remove(i);
                                    users.add(eventSnapshot.getValue(UserInformation.class));
                                }
                            }
                        }
                    } else {
                        users.add(eventSnapshot.getValue(UserInformation.class));
                    }

                }

                Collections.sort(users, new UsersComparator());

                for (int i = 0; i < users.size(); i++) {
                    Log.e("users" + i, users.get(i).getPseudo());
                    if((users.get(i).getPseudo()).equals(userInfo.getPseudo())){
                        rangJoueur=i+1;
                    }
                }



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

    public static void toastHS() {
        Handler makeToastHandler = new Handler(Looper.getMainLooper());

        makeToastHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.cancel();
                }
                if (users != null) {

                    toast = Toast.makeText(This, "Nouveau record personnel !!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        gameView = null;
        pauseMusique();
        fondSonore = null;
        Intent retourMenuIntent = new Intent(Game.this, Menu.class);

        startActivity(retourMenuIntent);
    }
}
