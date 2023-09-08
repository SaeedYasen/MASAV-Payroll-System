package com.example.finalprokhaled;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button btnPage1 = findViewById(R.id.btnPage1);
        Button btnPage2 = findViewById(R.id.btnPage2);
        Button btnPage3 = findViewById(R.id.btnPage3);
        ImageView Logout = findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });

        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // טפל בלחיצה על כפתור הדף הראשון
                Intent mainIntent = new Intent(Home.this, BillingDetailsActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();

            }
        });

        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // טפל בלחיצה על כפתור הדף השני

                Intent mainIntent = new Intent(Home.this, Menu.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });

        btnPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // טפל בלחיצה על כפתור הדף השלישי



                Intent mainIntent = new Intent(Home.this, ChatActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                String st ;
                Intent intent = getIntent();
                st = intent.getStringExtra("Value");
                mainIntent.putExtra("Value", st);
                long lastSignInTime1 = intent.getLongExtra("Lime", 0);
                long currentTime = System.currentTimeMillis();
                mainIntent.putExtra("Lime", currentTime);



                startActivity(mainIntent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}