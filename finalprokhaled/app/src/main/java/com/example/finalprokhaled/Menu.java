package com.example.finalprokhaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar; // Import the ActionBar class

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);



        Button btnShowByMonthYear = findViewById(R.id.btnShowByMonthYear);
        Button btnFilterBetweenDates = findViewById(R.id.btnFilterBetweenDates);
        Button btnSetDates = findViewById(R.id.btnSetDates);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(Menu.this, Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
        btnShowByMonthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // פעולות להצגת נתונים לפי חודש ושנה
                Intent mainIntent = new Intent(Menu.this, SecondYearActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });

        btnFilterBetweenDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // פעולות לסינון בין שני תאריכים
                Intent mainIntent = new Intent(Menu.this, SecondActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });

        btnSetDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // פעולות לקביעת תאריכים
                Intent mainIntent = new Intent(Menu.this, DateRangeActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); // Handle the back button press
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
