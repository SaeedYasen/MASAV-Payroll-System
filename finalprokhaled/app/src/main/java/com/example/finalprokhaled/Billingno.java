package com.example.finalprokhaled;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Billingno extends AppCompatActivity {

    private List<CombinedDetailsModel> selectedItems;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdate);
        selectedItems=new ArrayList<>();


        Intent intent = getIntent();
        selectedItems= (ArrayList<CombinedDetailsModel>) intent.getSerializableExtra("selectedItems");

    }

}
