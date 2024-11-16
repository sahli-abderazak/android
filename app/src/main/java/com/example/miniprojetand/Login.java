package com.example.miniprojetand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button signInButton;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        registerTextView = findViewById(R.id.registerTextView);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser(email, password);
                } else {
                    Toast.makeText(Login.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Intent intent = new Intent(Login.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(Login.this, "Échec de connexion : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
