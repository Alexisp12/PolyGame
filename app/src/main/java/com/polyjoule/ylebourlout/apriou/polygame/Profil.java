package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.polyjoule.ylebourlout.apriou.polygame.Menu.firebaseAuth;
import static com.polyjoule.ylebourlout.apriou.polygame.Menu.userInfo;

/**
 * Created by Alexis on 21/07/2017.
 */

public class Profil extends Activity {
    private TextView emailView;
    private TextView pseudoView;
    private DatabaseReference databaseReference;

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

                emailView.setText(userInfo.getEmail());
                pseudoView.setText(userInfo.getPseudo());
                //Log.d("UserInfoPseudo",userInfo.getPseudo());

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
        Intent retourMenuIntent = new Intent(Profil.this, Menu.class);
        startActivity(retourMenuIntent);
    }

}
