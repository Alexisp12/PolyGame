package com.polyjoule.ylebourlout.apriou.polygame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Alexis on 20/07/2017.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {


    //defining views
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;


    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    private Boolean authDone=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            authDone=true;
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), Profil.class));
        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup  = (TextView) findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    //method for user login
    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            authDone=true;
                            startActivity(new Intent(getApplicationContext(), Profil.class));
                        }
                    }
                });

//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReferenceFromUrl(databaseReference.toString());
//
//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
//                    if(authDone) {
//                        if ((eventSnapshot.getValue(UserInformation.class).getEmail()).equals(email)) {
//                            userInfo = eventSnapshot.getValue(UserInformation.class);
//                            SharedPreferences settings = getSharedPreferences(SETS, 0);
//                            int highScore = settings.getInt("highscore", 0);
//                            if (userInfo.getHighScore() < highScore) {
//                                userInfo.setHighScore(highScore);
//                            }
//                            startActivity(new Intent(getApplicationContext(), Profil.class));
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
////                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
////                    if(authDone) {
////                        if ((eventSnapshot.getValue(UserInformation.class).getEmail()).equals(email)) {
////                            userInfo = eventSnapshot.getValue(UserInformation.class);
////                            SharedPreferences settings = getSharedPreferences(SETS, 0);
////                            int highScore = settings.getInt("highscore", 0);
////                            if (userInfo.getHighScore() < highScore) {
////                                userInfo.setHighScore(highScore);
////                            }
////                            startActivity(new Intent(getApplicationContext(), Profil.class));
////                        }
////                    }
////                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();
            startActivity(new Intent(this, Registration.class));
        }
    }
    @Override
    public void onBackPressed() {
        Intent retourMenuIntent = new Intent(Login.this, Menu.class);

        startActivity(retourMenuIntent);
    }
}