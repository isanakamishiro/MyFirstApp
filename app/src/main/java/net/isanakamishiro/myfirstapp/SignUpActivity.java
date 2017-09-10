package net.isanakamishiro.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import net.isanakamishiro.myfirstapp.util.Alert;

public class SignUpActivity extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();

        passwordEditText = findViewById(R.id.passwordField);
        emailEditText = findViewById(R.id.emailField);
        signUpButton = findViewById(R.id.signupButton);

        signUpButton.setOnClickListener(view -> {
            String password = passwordEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();

            if (password.isEmpty() || email.isEmpty()) {
                Alert.showDialog(SignUpActivity.this,
                        R.string.signup_error_message,
                        R.string.signup_error_title);
            } else {
                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, task -> {
                            if (task.isSuccessful()) {
                                placeToMainFromSignUp();
                            } else {
                                Alert.showDialog(SignUpActivity.this,
                                        task.getException().getMessage(),
                                        R.string.signup_error_title);

                            }
                        });

            }

        });
    }

    private void placeToMainFromSignUp() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
