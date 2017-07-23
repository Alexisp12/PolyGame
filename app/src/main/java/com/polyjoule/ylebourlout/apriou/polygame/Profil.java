package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import static com.polyjoule.ylebourlout.apriou.polygame.Menu.databaseReference;
import static com.polyjoule.ylebourlout.apriou.polygame.Menu.firebaseAuth;
import static com.polyjoule.ylebourlout.apriou.polygame.Menu.userInfo;

/**
 * Created by Alexis on 21/07/2017.
 */

public class Profil extends Activity {
    private TextView emailView;
    private TextView pseudoView;
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

        emailView.setText(userInfo.getEmail());
        pseudoView.setText(userInfo.getPseudo());



        //getting the current logged in user
        FirebaseUser usr = firebaseAuth.getCurrentUser();

        //saving data to firebase database
        /*
        * first we are creating a new child in firebase with the
        * unique id of logged in user
        * and then for that user under the unique id we are saving data
        * for saving data we are using setvalue method this method takes a normal java object
        * */
        if(usr!=null) {
            databaseReference.child("users").child(usr.getUid()).setValue(userInfo);
        }



        // Toast ?

    }
    @Override
    public void onBackPressed (){
        Intent retourMenuIntent = new Intent(Profil.this, Menu.class);
        startActivity(retourMenuIntent);
    }
}
