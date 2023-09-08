package com.example.finalprokhaled;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DisplayInvoiceActivity extends AppCompatActivity {

    private List<CombinedDetailsModel> selectedItems;
    private SelectedItemsViewModel viewModel;
    private EditText editTextBankName, editTextBranchCode, editTextBankCode;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_invoice);

        viewModel = new ViewModelProvider(this).get(SelectedItemsViewModel.class);

        // Initialize views
        editTextBankName = findViewById(R.id.editTextBankName);
        editTextBranchCode = findViewById(R.id.editTextBranchCode);
        editTextBankCode = findViewById(R.id.editTextBankCode);

        selectedItems = viewModel.getSelectedItems();

        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        shareFileInternal();
                    } else {
                        // Handle permission denied
                    }
                }
        );
    }

    public void saveFile(View view) {
        String bankName = editTextBankName.getText().toString();
        String branchCode = editTextBranchCode.getText().toString();
        String bankCode = editTextBankCode.getText().toString();

        File file = new File(Environment.getExternalStorageDirectory(), "invoice.txt");

        try {
            FileWriter writer = new FileWriter(file);
            for (CombinedDetailsModel selected : selectedItems) {
                writer.write("Bank Name: " + bankName + "\n");
                writer.write("Branch Code: " + branchCode + "\n");
                writer.write("Bank Code: " + bankCode + "\n");
                writer.write("Billing Description: " + selected.getBillingDescription() + "\n");
                writer.write("Address: " + selected.getAddress() + "\n");
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shareFile(View view) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED) {
            shareFileInternal();
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void shareFileInternal() {
        File file = new File(Environment.getExternalStorageDirectory(), "invoice.txt");
        Uri uri = FileProvider.getUriForFile(this, "com.example.finalprokhaled.fileprovider", file);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent chooser = Intent.createChooser(intent, "Share File");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
            overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
