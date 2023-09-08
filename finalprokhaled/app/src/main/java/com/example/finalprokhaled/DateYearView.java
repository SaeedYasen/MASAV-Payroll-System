package com.example.finalprokhaled;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.checkerframework.common.subtyping.qual.Bottom;
import java.util.ArrayList;
import java.util.List;

public class DateYearView extends AppCompatActivity {


    private ArrayList<String> dateselect=new ArrayList<>();
    private RecyclerView recyclerView;
    private BillingDetailsAdapteryear adapter;
    Button msav;
    DatabaseHelper Dbdata;
    List<CombinedDetailsModel> lst;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateofmonthandyear);

        Dbdata = new DatabaseHelper(this);
        lst = new ArrayList<>();
        // Initialize ViewModel


        Intent intent = getIntent();
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(DateYearView.this, SecondYearActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
                finish();
            }
        });
        dateselect=intent.getStringArrayListExtra("Date");
        System.out.println(dateselect);

        for(String ds:dateselect) {
            lst.addAll(Dbdata.getBillingDetailsByDate(ds));


        }
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BillingDetailsAdapteryear(lst);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}


