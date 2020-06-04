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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;

public class LogInActivity extends AppCompatActivity {

    @BindView(R.id.field_email)
    EditText fieldEmail;

    @BindView(R.id.field_password)
    EditText fieldPassword;

    @BindView(R.id.go_to_authentication_button)
    Button goToAuthenticationButton;

    @BindView(R.id.email_sign_in_button)
    Button signInButton;

    String varEmail;
    String varPassword;

    private void signInButtonClick() {
        if (fieldEmail.getText().toString().equals("")) {
            //код если поле пусто
        } else {
            //код если текст есть
            if (fieldPassword.getText().toString().equals("")) {
                //код если поле пусто
            } else {
                //код если текст есть
                varEmail = fieldEmail.getText().toString();
                varPassword = fieldPassword.getText().toString();
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(varEmail, varPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // User signed in successfully
                                    goTo(0);
                                }
                            }
                        });
            }
        }
    }

    private void goToAuthenticationButtonClick() {
        goTo(1);
    }

    private void goTo(int i) {
        Intent intent;
        switch (i) {
            case 0:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, AuthenticationActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_to_authentication_button:
                goToAuthenticationButtonClick();
                break;
            case R.id.email_sign_in_button:
                signInButtonClick();
                break;
        }
    }
}
