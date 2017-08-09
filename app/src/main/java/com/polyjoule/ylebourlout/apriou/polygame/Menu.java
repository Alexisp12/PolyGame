package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {
    public static String SETS="SETS";
    private Button gameButton;
    private Button loginButton;
    private Context This;
    private String pseudo;
    public static UserInformation userInfo;
    //firebase auth object
    public static FirebaseAuth firebaseAuth;
    //defining a database reference
    public static DatabaseReference databaseReference;
    private ImageView bggif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        This=this.getApplicationContext();
        userInfo = new UserInformation();

        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        recupData();


        gameButton = (Button) findViewById(R.id.gamebutton);

        loginButton = (Button) findViewById(R.id.loginbutton);

        bggif = (ImageView) findViewById(R.id.menubg);

        //DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);

       // Drawable bgDrawable = Drawable.createFromPath("android.resource://com.polyjoule.ylebourlout.apriou.polygame/" + R.drawable.menugif);

        //bggif.getLayoutParams().height=metrics.heightPixels;
        //bggif.getLayoutParams().width=metrics.widthPixels;
        //Ion.with(bggif).resize(metrics.widthPixels,metrics.heightPixels).load("android.resource://com.polyjoule.ylebourlout.apriou.polygame/" + R.drawable.menugif);
        //Ion.with(bggif).load("android.resource://com.polyjoule.ylebourlout.apriou.polygame/" + R.drawable.menugif);




        gameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View startview){
                if(firebaseAuth.getCurrentUser()==null){
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(Menu.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(Menu.this);
                    }
                    builder.setTitle("Joueur non authentifié")
                            .setMessage("Souhaitez-vous accédez à la page d'authentification ?")
                            .setMessage("Un joueur non connecté n'aura pas accès au classement général.")
                            .setPositiveButton("Authentification", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent question1intent = new Intent(Menu.this, Login.class);
                                    startActivity(question1intent);
                                }
                            })
                            .setNegativeButton("Jouer", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent question1intent = new Intent(Menu.this, Game.class);
                                    startActivity(question1intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    SharedPreferences settings = getSharedPreferences(SETS, 0);

                    if(userInfo!=null) {
                        if(userInfo.getPseudo()==null) {
                            userInfo.setPseudo(settings.getString("pseudo", ""));
                            Log.d("pseudoNull","oui");
                        }
                        if(userInfo.getHighScore()==-1) {
                            userInfo.setHighScore(settings.getInt("highScore", 0));
                            Log.d("highscore-1","oui");
                        }
                    }

                    Intent question1intent = new Intent(Menu.this, Game.class);
                    startActivity(question1intent);
                }

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View startview){
                Intent question1intent = new Intent(Menu.this, Login.class);
                startActivity(question1intent);
            }
        });
    }

    public void recupData(){
        final FirebaseUser usr = firebaseAuth.getCurrentUser();
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl(databaseReference.toString());

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                userInfo = dataSnapshot.child("users").child(usr.getUid()).getValue(UserInformation.class);


                if(userInfo!=null) {
                    if (userInfo.getPseudo() == null) {
                        SharedPreferences settings = getSharedPreferences(SETS, 0);
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
        Intent retourMenuIntent = new Intent(Menu.this, Menu.class);

        startActivity(retourMenuIntent);
    }
}
