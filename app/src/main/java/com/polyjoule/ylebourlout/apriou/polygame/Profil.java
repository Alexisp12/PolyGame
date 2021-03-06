package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.SETS;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.firebaseAuth;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.userInfo;

/**
 * Created by Alexis on 21/07/2017.
 */

public class Profil extends AppCompatActivity implements View.OnClickListener {
    private TextView emailView;
    private TextView pseudoView;
    private TextView signout;
    private TextView contactus;
    private DatabaseReference databaseReference;
    private String problemeData="Error 404, please contact your administrator";

    //firebase auth object
    //private static FirebaseAuth firebaseAuth;
    //defining a database reference
    //private static DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        emailView = (TextView) findViewById(R.id.email);
        pseudoView = (TextView) findViewById(R.id.pseudo);
        signout = (TextView) findViewById(R.id.signout);
        contactus = (TextView) findViewById(R.id.contactus);

        signout.setOnClickListener(this);

        contactus.setOnClickListener(this);

        //getting the current logged in user

        final FirebaseUser usr = firebaseAuth.getCurrentUser();


        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.getReferenceFromUrl(databaseReference.toString());

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                userInfo = dataSnapshot.child("users").child(usr.getUid()).getValue(UserInformation.class);

                if(userInfo!=null) {
                    if (userInfo.getPseudo() != null) {
                        emailView.setText(userInfo.getEmail());
                        pseudoView.setText(userInfo.getPseudo());

                        SharedPreferences settings = getSharedPreferences(SETS, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("email",userInfo.getEmail());
                        editor.putString("pseudo", userInfo.getPseudo());
                        editor.putInt("highScore", userInfo.getHighScore());
                        editor.commit();


                        Log.d("UserInfoPseudo", userInfo.getPseudo());
                        Log.d("UserInfoHighScore", Integer.toString(userInfo.getHighScore()));
                    } else {
                        SharedPreferences settings = getSharedPreferences(SETS, 0);
                        userInfo.setPseudo(settings.getString("pseudo", ""));
                        userInfo.setHighScore(settings.getInt("highScore", 0));
                        userInfo.setEmail(settings.getString("email", ""));
                        emailView.setText(userInfo.getEmail());
                        pseudoView.setText(userInfo.getPseudo());

//                    pseudoView.setText(problemeData);
                    }
                } else {
                    SharedPreferences settings = getSharedPreferences(SETS, 0);
                    userInfo.setPseudo(settings.getString("pseudo", ""));
                    userInfo.setHighScore(settings.getInt("highScore", 0));
                    userInfo.setEmail(settings.getString("email", ""));

                    final FirebaseUser usr = firebaseAuth.getCurrentUser();
                    databaseReference.child("users").child(usr.getUid()).setValue(userInfo);

                    emailView.setText(userInfo.getEmail());
                    pseudoView.setText(userInfo.getPseudo());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.getCode());
            }
        });



        //saving data to firebase database
        /*
        * first we are creating a new child in firebase with the
        * unique id of logged in user
        * and then for that user under the unique id we are saving data
        * for saving data we are using setvalue method this method takes a normal java object
        * */
        if(usr!=null) {
            // databaseReference.child("users").child(usr.getUid()).setValue(userInfo);
        }




        // Toast ?

    }
    @Override
    public void onBackPressed (){
        Intent retourMenuIntent = new Intent(Profil.this, Accueil.class);
        startActivity(retourMenuIntent);
    }

    @Override
    public void onClick(View view) {

        if (view == signout) {
            firebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, Login.class));
        }

        if(view == contactus){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            //, Uri.fromParts(
            //"mailto","polyjoule@univ-nantes.fr", null));
            //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[Application mobile]");
            //mailIntent.putExtra(Intent.EXTRA_EMAIL, "polyjoule@univ-nantes.fr");

            emailIntent.setData(Uri.parse("mailto:polyjoule@univ-nantes.fr"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "polyjoule@univ-nantes.fr");
            //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[Application mobile]");

            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
                //startActivity(Intent.createChooser(emailIntent, "Contact us"));

            } else {
                Log.d("emailIntent","null");
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        //getting the current logged in user

        final FirebaseUser usr = firebaseAuth.getCurrentUser();


        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.getReferenceFromUrl(databaseReference.toString());

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                userInfo = dataSnapshot.child("users").child(usr.getUid()).getValue(UserInformation.class);

                if(userInfo!=null) {
                    if (userInfo.getPseudo() != null) {
                        emailView.setText(userInfo.getEmail());
                        pseudoView.setText(userInfo.getPseudo());

                        SharedPreferences settings = getSharedPreferences(SETS, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("email",userInfo.getEmail());
                        editor.putString("pseudo", userInfo.getPseudo());
                        editor.putInt("highScore", userInfo.getHighScore());
                        editor.commit();


                        Log.d("UserInfoPseudo", userInfo.getPseudo());
                        Log.d("UserInfoHighScore", Integer.toString(userInfo.getHighScore()));
                    } else {
                        SharedPreferences settings = getSharedPreferences(SETS, 0);
                        userInfo.setPseudo(settings.getString("pseudo", ""));
                        userInfo.setHighScore(settings.getInt("highScore", 0));
                        userInfo.setEmail(settings.getString("email", ""));
                        emailView.setText(userInfo.getEmail());
                        pseudoView.setText(userInfo.getPseudo());

//                    pseudoView.setText(problemeData);
                    }
                } else {
                    SharedPreferences settings = getSharedPreferences(SETS, 0);
                    userInfo.setPseudo(settings.getString("pseudo", ""));
                    userInfo.setHighScore(settings.getInt("highScore", 0));
                    userInfo.setEmail(settings.getString("email", ""));

                    final FirebaseUser usr = firebaseAuth.getCurrentUser();
                    databaseReference.child("users").child(usr.getUid()).setValue(userInfo);

                    emailView.setText(userInfo.getEmail());
                    pseudoView.setText(userInfo.getPseudo());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }




    }

