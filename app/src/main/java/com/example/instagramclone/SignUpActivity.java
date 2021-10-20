package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    public static final String EMAILPATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignUp;
    private Button btnLogin;
    
    @Override
    protected void onCreate(Bundle InstanceState) {
        super.onCreate(InstanceState);
        setContentView(R.layout.activity_sign_up);

        // Navigate to home screen if logged in
        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick sign up button");
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.trim().matches(EMAILPATTERN)) {
                    Toast.makeText(SignUpActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                signUpUser(email, username, password);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLoginActivity();
            }
        });

    }

    private void signUpUser(String email, String username, String password) {
        Log.i(TAG, "Attempting to sign up user " + username);
        ParseUser user = new ParseUser();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground();
        Log.i(TAG, "Sign up user " + username + " success!");
        Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
        goLoginActivity();
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
