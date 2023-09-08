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

public class DateView extends AppCompatActivity {

    String dateselect;
    private RecyclerView recyclerView;
    private BillingDetailsAdapter adapter;
    Button msav;
    DatabaseHelper Dbdata;
    List<CombinedDetailsModel> lst;
    private SelectedItemsViewModel viewModel;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataview);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(DateView.this, SecondYearActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Dbdata = new DatabaseHelper(this);
        lst = new ArrayList<>();
        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(SelectedItemsViewModel.class);


        Intent intent = getIntent();
        dateselect = intent.getStringExtra("Date");
        System.out.println(dateselect);
        lst = Dbdata.getBillingDetailsByDate(dateselect);

        msav = findViewById(R.id.button_create_invoice);
        msav.setOnClickListener(v -> createInvoiceForSelectedItems());

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BillingDetailsAdapter(lst);
        recyclerView.setAdapter(adapter);
    }

    private void createInvoiceForSelectedItems() {
        List<CombinedDetailsModel> selectedItems = adapter.getSelectedItems();

        if (selectedItems != null && !selectedItems.isEmpty()) {
            // Save selectedItems in ViewModel
            viewModel.setSelectedItems(selectedItems);

            Intent intent = new Intent(DateView.this, DisplayInvoiceActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
            finish();
        } else {
            System.out.println("No items selected.");
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
