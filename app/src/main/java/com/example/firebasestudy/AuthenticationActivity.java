package com.example.firebasestudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthenticationActivity extends AppCompatActivity {

    @BindView(R.id.field_email_authentication)
    EditText fieldEmailAuthentication;

    @BindView(R.id.field_password_authentication)
    EditText fieldPasswordAuthentication;

    @BindView(R.id.create_new_account_button)
    Button createNewAccountButton;

    String varEmail;
    String varPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authentication);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void mAuthenticationButton() {
        if (fieldEmailAuthentication.getText().toString().equals("")) {
            //код если поле пусто
        } else {
            //код если текст есть
            if (fieldPasswordAuthentication.getText().toString().equals("")) {
                //код если поле пусто
            } else {
                //код если текст есть
                varEmail = fieldEmailAuthentication.getText().toString();
                varPassword = fieldPasswordAuthentication.getText().toString();
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(varEmail, varPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    // User registered successfully
                                    goTo();
                                }
                            }
                        });
            }
        }
    }

    private void goTo() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            System.out.println("User ID: " + user.getUid());
        } else {
            System.out.println("Error: sign in failed.");
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_new_account_button:
                mAuthenticationButton();
                break;
        }
    }
}
