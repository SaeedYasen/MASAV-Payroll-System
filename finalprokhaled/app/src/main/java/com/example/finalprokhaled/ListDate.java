package com.example.finalprokhaled;

import static android.text.TextUtils.isEmpty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListDate extends AppCompatActivity {
    private static final String TAG = "ListDate"; // For logging purposes
     DatabaseReference billingDetailsDatabase;
    private RecyclerView datesview;
    private String startdate;
    private String enddate;
    DatabaseHelper Dbdate;
    private ArrayList<String> datesf = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdate);

        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(ListDate.this, SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
        Dbdate = new DatabaseHelper(this);
        Intent intent = getIntent();
        startdate = intent.getStringExtra("Start");
        enddate = intent.getStringExtra("End");
        Log.d(TAG, "Start date: " + startdate);
        Log.d(TAG, "End date: " + enddate);



        datesview = findViewById(R.id.recyclerview);


        storeDataInArrays();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void storeDataInArrays() {

                dates = new ArrayList<>();

                datesf = (ArrayList<String>) Dbdate.getAllFirstCharges();


        dates  = Dbdate.getDatesBetween(startdate, enddate);

/*
                for (String firstCharge: datesf) {

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println(firstCharge);
                        Date startDate = sdf.parse(startdate);
                        Date endDate = sdf.parse(enddate);
                        Date nowDate = sdf.parse(firstCharge);
                        System.out.println("kakak"+nowDate);
                        if (nowDate.compareTo(startDate) >= 0 && nowDate.compareTo(endDate) <= 0) {
                            System.out.println("khaled"+nowDate);
                            dates.add(String.valueOf(nowDate));

                        }
/*
                        if (startDate.before(nowDate) && endDate.after(nowDate)) {
                            dates.add(String.valueOf(nowDate));
                            System.out.println(nowDate);
                        }


                    } catch (ParseException e) {
                        // Handle parse exception
                    }
                }
                */



                // Rest of your code...


                Adpterdate adapter = new Adpterdate(ListDate.this, ListDate.this, dates);
                datesview.setAdapter(adapter);
                datesview.setLayoutManager(new LinearLayoutManager(ListDate.this));
            }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
