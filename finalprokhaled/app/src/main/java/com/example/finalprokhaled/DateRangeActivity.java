package com.example.finalprokhaled;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateRangeActivity extends AppCompatActivity {

    private DatabaseHelper Dbdata;
    private Spinner customerSpinner;
    private EditText fromDateEditText;
    private Calendar calendar;
    List<CombinedDetailsModel> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondnameanddate);

        Dbdata = new DatabaseHelper(this);
        calendar = Calendar.getInstance();
        customerSpinner = findViewById(R.id.customerSpinner);
        fromDateEditText = findViewById(R.id.editTextFromDate);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(DateRangeActivity.this, Menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
        lst=new ArrayList<>();

        fromDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });

        List<String> customerNames = Dbdata.getDistinctCustomerName();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, customerNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customerSpinner.setAdapter(spinnerAdapter);

        Button showDatesButton = findViewById(R.id.showDatesButton);
        showDatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date =fromDateEditText.getText().toString();
                String cusmername=customerSpinner.getSelectedItem().toString();
/*
                lst=Dbdata.getBillingDetailsByDateAndCustomer(date,cusmername);
*/

                Intent itIntent = new Intent(DateRangeActivity.this, Dateofname.class);

                itIntent.putExtra("Date", date);
                itIntent.putExtra("Name", cusmername);


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
        EditText editText = isFromDate ? fromDateEditText : fromDateEditText;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        editText.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
