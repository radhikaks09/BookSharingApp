package com.example.booksharingapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignup = findViewById(R.id.buttonSignup);
        TextView signupText = findViewById(R.id.textView);
        signupText.setText(R.string.signup_text);

        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(main);
                    }
                    else{
                        Log.w(TAG, "singInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed: Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        buttonSignup.setOnClickListener((v -> {
            Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(signup);
        }));
    }
}
