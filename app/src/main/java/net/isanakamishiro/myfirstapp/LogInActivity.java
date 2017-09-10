package net.isanakamishiro.myfirstapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import net.isanakamishiro.myfirstapp.util.Alert;

public class LogInActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button logInButton;
    TextView signUpTextView;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mFirebaseAuth = FirebaseAuth.getInstance();

        signUpTextView = findViewById(R.id.signUpText);
        emailEditText = findViewById(R.id.emailField);
        passwordEditText = findViewById(R.id.passwordField);
        logInButton = findViewById(R.id.loginButton);

        signUpTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                email = email.trim();
                password = password.trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Alert.showDialog(LogInActivity.this,
                            R.string.login_error_message,
                            R.string.login_error_title);

                } else {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LogInActivity.this, task -> {
                                if (task.isSuccessful()) {
                                    placeToMain();
                                } else {
                                    Alert.showDialog(LogInActivity.this,
                                            task.getException().getMessage(),
                                            R.string.login_error_title);
                                }
                            });
                }
            }

            private void placeToMain() {
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
