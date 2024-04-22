package com.example.booksharingapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booksharingapp.ui.profile.ProfileViewModel;
import com.example.booksharingapp.ui.profile.SetProfileData;
import com.example.booksharingapp.ui.profile.UserProfileData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity{
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonSignup = findViewById(R.id.buttonSignup);

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        buttonSignup.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            UserProfileData userData = new UserProfileData(username, email);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        db.collection("users").document(mAuth.getUid()).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createProfile:success");
                                    SetProfileData profile = new SetProfileData();
                                    profile.updateProfile();
                                    Intent main = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(main);
                                }
                                else{
                                    Log.w(TAG, "createProfile:failure", task.getException());
                                }
                            }
                        });
                    }
                    else{
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignupActivity.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}
