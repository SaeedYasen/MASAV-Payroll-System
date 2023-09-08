package com.example.finalprokhaled;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

    public class SecondYearActivity extends AppCompatActivity {

        private Spinner yearSpinner;
        private Spinner monthSpinner;
        private Button showDatesButton;
        DatabaseHelper dbdate;
        ArrayList<String>lst;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_secondyarandmonth);

            yearSpinner = findViewById(R.id.yearSpinner);
            monthSpinner = findViewById(R.id.monthSpinner);
            showDatesButton = findViewById(R.id.showDatesButton);
            ImageView backButton = findViewById(R.id.backButton);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to SecondYearActivity
                    Intent intent = new Intent(SecondYearActivity.this, Menu.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                    finish();
                }
            });
            lst=new ArrayList<>();
            dbdate=new DatabaseHelper(this);

            // Populate year spinner
            List<String> years = new ArrayList<>();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = currentYear - 5; i <= currentYear + 5; i++) {
                years.add(String.valueOf(i));
            }
            ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
            yearSpinner.setAdapter(yearAdapter);

            // Populate month spinner
            ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
            monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            monthSpinner.setAdapter(monthAdapter);

            showDatesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedYear = yearSpinner.getSelectedItem().toString();
                    String selectedMonth = String.valueOf(monthSpinner.getSelectedItemPosition() + 1);
                   lst=dbdate.Givemedates(selectedYear, selectedMonth);
                   System.out.println(lst);

                    Intent itIntent = new Intent(SecondYearActivity.this, DateYearView.class);
                    itIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    itIntent.putExtra("Date", lst);

                    startActivity(itIntent);
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
