package com.mariailieva.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mariailieva.loginfirebase.auth.FirebaseManager;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private ImageView imageView;
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email= findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        imageView = findViewById(R.id.hiImage);
        firebaseManager = new FirebaseManager(this);
    }

    public void showLogInMessage(){
        imageView.setVisibility(View.VISIBLE);
    }

    public void hideMessage(){
        imageView.setVisibility(View.INVISIBLE);
    }

    public void signIn(View view){
        String emailTxt = email.getText().toString();
        String passwordTxt = password.getText().toString();
        if(email.length() > 0 && password.length() > 0) {
            firebaseManager.signIn(emailTxt, passwordTxt, this);
        }
    }

    public void signUp(View view){
        String emailTxt = email.getText().toString();
        String passwordTxt = password.getText().toString();
        if(email.length() > 0 && password.length() > 0) {
            firebaseManager.signUp(emailTxt, passwordTxt);
        }
    }

    public void signOut(View view){
        firebaseManager.signOut();
    }

}
