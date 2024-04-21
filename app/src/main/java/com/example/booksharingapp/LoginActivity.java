package com.example.booksharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignup = findViewById(R.id.buttonSignup);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            if (username.equals("admin@gmail.com") && password.equals("admin")){
                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(main);
            }
            else{
                Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSignup.setOnClickListener((v -> {
            Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(signup);
        }));
    }
}
