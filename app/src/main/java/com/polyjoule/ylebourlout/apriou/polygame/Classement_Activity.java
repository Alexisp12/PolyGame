package com.polyjoule.ylebourlout.apriou.polygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;

import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.databaseReference;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.firebaseAuth;
import static com.polyjoule.ylebourlout.apriou.polygame.Accueil.users;

/**
 * Created by Alexis on 20/07/2017.
 */

public class Classement_Activity extends AppCompatActivity implements View.OnClickListener {


    //defining views
    private TextView pseudo1tv;
    private TextView score1tv;
    private TextView pseudo2tv;
    private TextView score2tv;
    private TextView pseudo3tv;
    private TextView score3tv;
    private TextView pseudo4tv;
    private TextView score4tv;
    private TextView pseudo5tv;
    private TextView score5tv;
    private TextView pseudo6tv;
    private TextView score6tv;
    private TextView pseudo7tv;
    private TextView score7tv;
    private TextView pseudo8tv;
    private TextView score8tv;
    private TextView pseudo9tv;
    private TextView score9tv;
    private TextView pseudo10tv;
    private TextView score10tv;
    private TextView refresh;
    private String refreshText="Click to refresh";
    private String nonAuth="Identification is necessary to see rating";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classement_activity);

        //initializing views
        pseudo1tv  = (TextView) findViewById(R.id.pseudo1tv);
        score1tv  = (TextView) findViewById(R.id.score1tv);
        pseudo2tv  = (TextView) findViewById(R.id.pseudo2tv);
        score2tv  = (TextView) findViewById(R.id.score2tv);
        pseudo3tv  = (TextView) findViewById(R.id.pseudo3tv);
        score3tv  = (TextView) findViewById(R.id.score3tv);
        pseudo4tv  = (TextView) findViewById(R.id.pseudo4tv);
        score4tv  = (TextView) findViewById(R.id.score4tv);
        pseudo5tv  = (TextView) findViewById(R.id.pseudo5tv);
        score5tv  = (TextView) findViewById(R.id.score5tv);
        pseudo6tv  = (TextView) findViewById(R.id.pseudo6tv);
        score6tv  = (TextView) findViewById(R.id.score6tv);
        pseudo7tv  = (TextView) findViewById(R.id.pseudo7tv);
        score7tv  = (TextView) findViewById(R.id.score7tv);
        pseudo8tv  = (TextView) findViewById(R.id.pseudo8tv);
        score8tv  = (TextView) findViewById(R.id.score8tv);
        pseudo9tv  = (TextView) findViewById(R.id.pseudo9tv);
        score9tv  = (TextView) findViewById(R.id.score9tv);
        pseudo10tv  = (TextView) findViewById(R.id.pseudo10tv);
        score10tv  = (TextView) findViewById(R.id.score10tv);
        refresh  = (TextView) findViewById(R.id.refresh);

        if(firebaseAuth!=null) {
            if (firebaseAuth.getCurrentUser() != null) {
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


                if (users.size() != 0) {
                    refresh.setText("");
                    if (users.size() > 0 && users.get(0).getHighScore() != -1) {
                        pseudo1tv.setText("1 - " + users.get(0).getPseudo());
                        score1tv.setText(Integer.toString(users.get(0).getHighScore()));
                    }
                    if (users.size() > 1 && users.get(1).getHighScore() != -1) {
                        pseudo2tv.setText("2 - " + users.get(1).getPseudo());
                        score2tv.setText(Integer.toString(users.get(1).getHighScore()));
                    }

                    if (users.size() > 2 && users.get(2).getHighScore() != -1) {
                        pseudo3tv.setText("3 - " + users.get(2).getPseudo());
                        score3tv.setText(Integer.toString(users.get(2).getHighScore()));
                    }
                    if (users.size() > 3 && users.get(3).getHighScore() != -1) {
                        pseudo4tv.setText("4 - " + users.get(3).getPseudo());
                        score4tv.setText(Integer.toString(users.get(3).getHighScore()));
                    }
                    if (users.size() > 4 && users.get(4).getHighScore() != -1) {
                        pseudo5tv.setText("5 - " + users.get(4).getPseudo());
                        score5tv.setText(Integer.toString(users.get(4).getHighScore()));
                    }
                    if (users.size() > 5 && users.get(5).getHighScore() != -1) {
                        pseudo6tv.setText("6 - " + users.get(5).getPseudo());
                        score6tv.setText(Integer.toString(users.get(5).getHighScore()));
                    }
                    if (users.size() > 6 && users.get(6).getHighScore() != -1) {
                        pseudo7tv.setText("7 - " + users.get(6).getPseudo());
                        score7tv.setText(Integer.toString(users.get(6).getHighScore()));
                    }
                    if (users.size() > 7 && users.get(7).getHighScore() != -1) {
                        pseudo8tv.setText("8 - " + users.get(7).getPseudo());
                        score8tv.setText(Integer.toString(users.get(7).getHighScore()));
                    }
                    if (users.size() > 8 && users.get(8).getHighScore() != -1) {
                        pseudo9tv.setText("9 - " + users.get(8).getPseudo());
                        score9tv.setText(Integer.toString(users.get(8).getHighScore()));
                    }
                    if (users.size() > 9 && users.get(9).getHighScore() != -1) {
                        pseudo10tv.setText("10 - " + users.get(9).getPseudo());
                        score10tv.setText(Integer.toString(users.get(9).getHighScore()));
                    }
                } else {
                    refresh.setText(refreshText);
                }
            } else {
                refresh.setText(nonAuth);
            }
        }










//        //attaching click listener
//        buttonSignIn.setOnClickListener(this);
//        textViewSignup.setOnClickListener(this);
    }

    //method for user login
    private void userLogin(){

    }

    @Override
    public void onClick(View view) {





//        if(view == buttonSignIn){
//            userLogin();
//        }
//
//        if(view == textViewSignup){
//            finish();
//            startActivity(new Intent(this, Registration.class));
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentX = (int)event.getX();
        int currentY = (int)event.getY();

        switch (event.getAction()) {

            // code exécuté lorsque le doigt touche l'écran.
            case MotionEvent.ACTION_DOWN:

                Log.d("test","click");

                if(firebaseAuth!=null) {
                    if (firebaseAuth.getCurrentUser() != null) {
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


                        if (users.size() != 0) {
                            refresh.setText("");
                            if (users.size() > 0 && users.get(0).getHighScore() != -1) {
                                pseudo1tv.setText("1 - " + users.get(0).getPseudo());
                                score1tv.setText(Integer.toString(users.get(0).getHighScore()));
                            }
                            if (users.size() > 1 && users.get(1).getHighScore() != -1) {
                                pseudo2tv.setText("2 - " + users.get(1).getPseudo());
                                score2tv.setText(Integer.toString(users.get(1).getHighScore()));
                            }

                            if (users.size() > 2 && users.get(2).getHighScore() != -1) {
                                pseudo3tv.setText("3 - " + users.get(2).getPseudo());
                                score3tv.setText(Integer.toString(users.get(2).getHighScore()));
                            }
                            if (users.size() > 3 && users.get(3).getHighScore() != -1) {
                                pseudo4tv.setText("4 - " + users.get(3).getPseudo());
                                score4tv.setText(Integer.toString(users.get(3).getHighScore()));
                            }
                            if (users.size() > 4 && users.get(4).getHighScore() != -1) {
                                pseudo5tv.setText("5 - " + users.get(4).getPseudo());
                                score5tv.setText(Integer.toString(users.get(4).getHighScore()));
                            }
                            if (users.size() > 5 && users.get(5).getHighScore() != -1) {
                                pseudo6tv.setText("6 - " + users.get(5).getPseudo());
                                score6tv.setText(Integer.toString(users.get(5).getHighScore()));
                            }
                            if (users.size() > 6 && users.get(6).getHighScore() != -1) {
                                pseudo7tv.setText("7 - " + users.get(6).getPseudo());
                                score7tv.setText(Integer.toString(users.get(6).getHighScore()));
                            }
                            if (users.size() > 7 && users.get(7).getHighScore() != -1) {
                                pseudo8tv.setText("8 - " + users.get(7).getPseudo());
                                score8tv.setText(Integer.toString(users.get(7).getHighScore()));
                            }
                            if (users.size() > 8 && users.get(8).getHighScore() != -1) {
                                pseudo9tv.setText("9 - " + users.get(8).getPseudo());
                                score9tv.setText(Integer.toString(users.get(8).getHighScore()));
                            }
                            if (users.size() > 9 && users.get(9).getHighScore() != -1) {
                                pseudo10tv.setText("10 - " + users.get(9).getPseudo());
                                score10tv.setText(Integer.toString(users.get(9).getHighScore()));
                            }
                        } else {
                            refresh.setText(refreshText);
                        }
                    } else {
                        refresh.setText(nonAuth);
                    }
                }
                break;


                // code exécuté lorsque le doight glisse sur l'écran.
            case MotionEvent.ACTION_MOVE:



                break;

            // lorsque le doigt quitte l'écran
            case MotionEvent.ACTION_UP:


        }

        return true;  // On retourne "true" pour indiquer qu'on a géré l'évènement
    }

    @Override
    public void onResume(){
        super.onResume();

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

        if(users.size()!=0) {
            refresh.setText("");
            if (users.size() > 0) {
                pseudo1tv.setText("1 - " + users.get(0).getPseudo());
                score1tv.setText(Integer.toString(users.get(0).getHighScore()));
            }
            if (users.size() > 1) {
                pseudo2tv.setText("2 - " + users.get(1).getPseudo());
                score2tv.setText(Integer.toString(users.get(1).getHighScore()));
            }

            if (users.size() > 2) {
                pseudo3tv.setText("3 - " + users.get(2).getPseudo());
                score3tv.setText(Integer.toString(users.get(2).getHighScore()));
            }
            if (users.size() > 3) {
                pseudo4tv.setText("4 - " + users.get(3).getPseudo());
                score4tv.setText(Integer.toString(users.get(3).getHighScore()));
            }
            if (users.size() > 4) {
                pseudo5tv.setText("5 - " + users.get(4).getPseudo());
                score5tv.setText(Integer.toString(users.get(4).getHighScore()));
            }
            if (users.size() > 5) {
                pseudo6tv.setText("6 - " + users.get(5).getPseudo());
                score6tv.setText(Integer.toString(users.get(5).getHighScore()));
            }
            if (users.size() > 6) {
                pseudo7tv.setText("7 - " + users.get(6).getPseudo());
                score7tv.setText(Integer.toString(users.get(6).getHighScore()));
            }
            if (users.size() > 7) {
                pseudo8tv.setText("8 - " + users.get(7).getPseudo());
                score8tv.setText(Integer.toString(users.get(7).getHighScore()));
            }
            if (users.size() > 8) {
                pseudo9tv.setText("9 - " + users.get(8).getPseudo());
                score9tv.setText(Integer.toString(users.get(8).getHighScore()));
            }
            if (users.size() > 9) {
                pseudo10tv.setText("10 - " + users.get(9).getPseudo());
                score10tv.setText(Integer.toString(users.get(9).getHighScore()));
            }
        } else {
            refresh.setText(refreshText);
        }

    }

    @Override
    public void onBackPressed() {
        Intent retourMenuIntent = new Intent(Classement_Activity.this, Game.class);

        startActivity(retourMenuIntent);
    }
}