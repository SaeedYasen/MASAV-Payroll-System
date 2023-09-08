
package com.example.finalprokhaled;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Button;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.lifecycle.ViewModelProvider;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import org.checkerframework.common.subtyping.qual.Bottom;
        import java.util.ArrayList;
        import java.util.List;

public class Dateofname extends AppCompatActivity {


    private String dateselect;
    private String name;
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

        dateselect = intent.getStringExtra("Date");
       name = intent.getStringExtra("Name");
        System.out.println(dateselect+name);
        lst=Dbdata.getBillingDetailsByDateAndCustomer(dateselect,name);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BillingDetailsAdapteryear(lst);
        recyclerView.setAdapter(adapter);
    }
}


