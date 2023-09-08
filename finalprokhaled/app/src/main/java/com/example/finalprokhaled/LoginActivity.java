package com.example.finalprokhaled;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalprokhaled.ChatActivity;
import com.example.finalprokhaled.ITDepartment; // וודא שהנתיב תואם את המיקום המדויק של קובץ ה-ITDepartment
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText userName, password;
    Button login;
    TextView register, forgotPassword;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.pwd);
        login = findViewById(R.id.login_btn);
        register = findViewById(R.id.registerLink);
        forgotPassword = findViewById(R.id.ForgetPassword);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        currentUser = mAuth.getCurrentUser();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowUserToLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle navigation to registration screen
                // Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                // startActivity(registerIntent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPasswordUser();
            }
        });
    }

    private void resetPasswordUser() {
        String email = userName.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Please enter your email id", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Reset Email sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void AllowUserToLogin() {
        String email = userName.getText().toString().trim();
        String pwd = password.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Please enter email id", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Sign In");
            loadingBar.setMessage("Please wait, signing in...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (email.equals("khaledabdalqader77@gmail.com")) {
                                    sendToITDepartment();
                                } else {
                                    sendToMainActivity();
                                }
                                Toast.makeText(LoginActivity.this, "Welcome to the App", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error: " + msg, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void sendToITDepartment() {
        Intent itIntent = new Intent(LoginActivity.this, ITDepartment.class);
        itIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        String st = userName.getText().toString();
        itIntent.putExtra("Value", st);
        long currentTime = System.currentTimeMillis();
        itIntent.putExtra("Lime", currentTime);

        startActivity(itIntent);
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
        finish();
    }

    private void sendToMainActivity() {
        Intent mainIntent = new Intent(LoginActivity.this, Home.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        String st = userName.getText().toString();
        mainIntent.putExtra("Value", st);
        long currentTime = System.currentTimeMillis();
        mainIntent.putExtra("Lime", currentTime);

        startActivity(mainIntent);
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
