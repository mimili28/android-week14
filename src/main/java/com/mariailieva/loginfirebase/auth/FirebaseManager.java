package com.mariailieva.loginfirebase.auth;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.mariailieva.loginfirebase.MainActivity;

public class FirebaseManager {

    FirebaseAuth auth;
    private MainActivity mainActivity;

    public FirebaseManager(MainActivity activity){
        auth = FirebaseAuth.getInstance();
        mainActivity = activity;
        setupAuthStateListener();
    }

    private void setupAuthStateListener(){
        auth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    System.out.println("Signed out");
                    mainActivity.hideMessage();
                }
                else{
                    System.out.println("Signed in");
                }
            }
        });
    }

    //sign in
    public void signIn(String email, String password, final MainActivity activity){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            System.out.println("LOGIN successful");
                            activity.showLogInMessage();
                        }
                        else{
                            System.out.println("LOGIN failed" + task.getException());
                        }
                    }
                });
    }

    public void signUp(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            System.out.println("SIGNUP successful" +
                                    task.getResult().getUser().getEmail());
                        }
                        else{
                            System.out.println("SIGNUP failed");
                        }
                    }
                });
    }

    public void signOut(){
        auth.signOut();
    }



}
