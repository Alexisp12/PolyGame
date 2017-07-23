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
//                if(userInfo.getPseudo()=="") {
//                    //On instancie notre layout en tant que View
//                    LayoutInflater factory = LayoutInflater.from(Menu.this);
//                    final View alertDialogView = factory.inflate(R.layout.alertdialogchoixpseudo, null);
//
//                    //Création de l'AlertDialog
//                    AlertDialog.Builder adb = new AlertDialog.Builder(Menu.this);
//
//                    //On affecte la vue personnalisé que l'on a crée à notre AlertDialog
//                    adb.setView(alertDialogView);
//
//                    //On donne un titre à l'AlertDialog
//                    adb.setTitle("Choix du pseudo");
//
//                    //On modifie l'icône de l'AlertDialog pour le fun ;)
//                    adb.setIcon(android.R.drawable.ic_dialog_alert);
//
//                    //On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
//                    adb.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            //Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
//                            EditText et = (EditText) alertDialogView.findViewById(R.id.pseudoEditText);
//                            if (et != null) {
//                                userInfo.setPseudo(et.getText().toString());
//
//                                SharedPreferences settings = getSharedPreferences(SETS, 0);
//                                SharedPreferences.Editor editor = settings.edit();
//                                //pseudo=et.getText().toString();
//                                editor.putString("pseudo", et.getText().toString());
//                                editor.commit();
//
//                                Intent question1intent = new Intent(Menu.this, Game.class);
//                                startActivity(question1intent);
//                            }
//
//                        }
//                    });
//
//                    //On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
//                    adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            //Lorsque l'on cliquera sur annuler on quittera l'application
//
//                        }
//                    });
//                    adb.show();
//                } else {
//                    Log.d("pseudo",userInfo.getPseudo());
//                    Intent question1intent = new Intent(Menu.this, Game.class);
//                    startActivity(question1intent);
//                }
                //Log.d("pseudo",userInfo.getPseudo());

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

                //Log.d("UserInfoPseudo",userInfo.getPseudo());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
