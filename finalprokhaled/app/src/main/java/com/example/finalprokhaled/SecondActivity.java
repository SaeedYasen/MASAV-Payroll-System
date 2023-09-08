package com.example.finalprokhaled;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextFromDate;
    private EditText editTextUntilDate;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextFromDate = findViewById(R.id.editTextFromDate);
        editTextUntilDate = findViewById(R.id.editTextUntilDate);
        Button buttonPickDate = findViewById(R.id.buttonPickDate);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(SecondActivity.this, Menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
        calendar = Calendar.getInstance();

        editTextFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });

        editTextUntilDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(false);
            }
        });

        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startdate =editTextFromDate.getText().toString();
                   String enddate  =editTextUntilDate.getText().toString();


                Intent itIntent = new Intent(SecondActivity.this, ListDate.class);
                itIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                itIntent.putExtra("Start", startdate);

                itIntent.putExtra("End", enddate);

                startActivity(itIntent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
    }

    private void showDatePickerDialog(final boolean isFromDate) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEditText(isFromDate);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void updateEditText(boolean isFromDate) {
        EditText editText = isFromDate ? editTextFromDate : editTextUntilDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        editText.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
