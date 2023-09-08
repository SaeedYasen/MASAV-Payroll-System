package com.example.finalprokhaled;


import static android.text.TextUtils.isEmpty;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BillingDetailsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText editTextCardValidity; // הוספתי כדי להשתמש בה בפונקציה התאריך

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_details);

        databaseHelper = new DatabaseHelper(this);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(BillingDetailsActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        Button buttonUpdate = findViewById(R.id.updet);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDatabase();
            }


        });
        editTextCardValidity = findViewById(R.id.editTextCardValidity);
        // השארת שאר הקוד שלך כמו בדוגמה המקורית
    }

    private void saveDataToDatabase() {
        // קוד השמירה במסד הנתונים יהיה כאן

        // השארת שאר הקוד שלך כמו בדוגמה המקורית
        EditText editTextCustomerNumber = findViewById(R.id.editTextCustomerNumber);
        EditText editTextCustomerName = findViewById(R.id.editTextCustomerName);
        EditText editTextCustomerID = findViewById(R.id.editTextCustomerID);

        String customerNumber = editTextCustomerNumber.getText().toString();
        String customerName = editTextCustomerName.getText().toString();
        String customerID = editTextCustomerID.getText().toString();

        Customer customer = new Customer(customerNumber, customerName, customerID);

        // קוד נתוני הבנק (bankDetails) הנשמרים ב-Firebase
        Spinner spinnerBank = findViewById(R.id.spinnerBank);
        EditText bankCodeEditText = findViewById(R.id.editTextCode);

        String selectedBank = spinnerBank.getSelectedItem().toString();

        EditText editTextBranchCode = findViewById(R.id.editTextBranchCode);
        EditText editTextAccountNumber = findViewById(R.id.editTextAccountNumber);

        String bankName = spinnerBank.getSelectedItem().toString();
        String bankCode = bankCodeEditText.getText().toString();
        String branchCode = editTextBranchCode.getText().toString();
        String accountNumber = editTextAccountNumber.getText().toString();

        BankDetails bankDetails = new BankDetails(bankName, bankCode, branchCode, accountNumber);

        // קוד נתוני החיוב (billingDetails) הנשמרים ב-Firebase
        EditText editTextBillingDescription = findViewById(R.id.editTextBillingDescription);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextperiodfrom = findViewById(R.id.editTextBillingPeriodFrom);
        EditText editTextperiodto = findViewById(R.id.editTextBillingPeriodTo);
        EditText editTextfirstcharge = findViewById(R.id.editTextFirstCharge);
        EditText editTextBillingDay = findViewById(R.id.editTextBillingDay);
        EditText editTextChargeAmount = findViewById(R.id.editTextChargeAmount);
        EditText editTextNumberOfCharges = findViewById(R.id.editTextNumberOfCharges);


        String billingDescription = editTextBillingDescription.getText().toString();
        String address = editTextAddress.getText().toString();

        String billingPeriodFrom = editTextperiodfrom.getText().toString();
        String billingPeriodTo = editTextperiodto.getText().toString();
        String firstCharge = editTextCardValidity.getText().toString();
        String Billingday = editTextBillingDay.getText().toString();
        String ChargeAmount = editTextChargeAmount.getText().toString();
        String NumerOfCharges = editTextNumberOfCharges.getText().toString();

        BillingDetails billingDetails = new BillingDetails(billingDescription, address, billingPeriodFrom, billingPeriodTo, firstCharge, Billingday, ChargeAmount, NumerOfCharges);

        if (isEmpty(editTextCustomerNumber.getText()) ||
                isEmpty(editTextCustomerName.getText()) ||
                isEmpty(editTextCustomerID.getText()) ||

                isEmpty(editTextBranchCode.getText()) ||
                isEmpty(editTextAccountNumber.getText()) ||
                isEmpty(editTextCardValidity.getText()) ||
                isEmpty(editTextBillingDescription.getText()) ||
                isEmpty(editTextAddress.getText()) ||
                isEmpty(editTextperiodfrom.getText()) ||
                isEmpty(editTextperiodto.getText()) ||
                isEmpty(editTextfirstcharge.getText()) ||
                isEmpty(editTextBillingDay.getText()) ||
                isEmpty(editTextChargeAmount.getText()) ||
                isEmpty(editTextNumberOfCharges.getText())) {
            Toast.makeText(BillingDetailsActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();


        } else {


            if (validateAccountNumber(accountNumber, bankName, branchCode)) {



                // השמירה במסד הנתונים באמצעות המחלקה DatabaseHelper
                databaseHelper.addCustomer(customer);
                databaseHelper.addBankDetails(bankDetails);
                databaseHelper.addBillingDetails(billingDetails);

                Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(BillingDetailsActivity.this, Home.class);
                startActivity(mainIntent);
                finish();

            }
        }
    }

    // כאן נשארת הקוד שלך לפי הדוגמה המקורית

        private boolean validateAccountNumber(String accountNumber, String bankName ,String branchCode) {

            if (bankName.equals("בנק הפועלים")) {

                if (accountNumber.length() != 6 || branchCode.length() != 3) {
                    return false;
                }

                int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                int sum = 0;

                for (int i = 0; i < accountNumber.length(); i++) {
                    sum += Character.getNumericValue(accountNumber.charAt(i)) * weights[i];
                }

                int remainder = sum % 11;

                if (remainder == 0 || remainder == 2 || remainder == 4 || remainder == 6) {
                    return true;
                } else {
                    return false;
                }


            } else if (bankName.equals("בנק יהב")) {
                if (accountNumber.length() != 6 || branchCode.length() != 3) {
                    return false;
                }

                int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                int sum = 0;

                for (int i = 0; i < accountNumber.length(); i++) {
                    sum += Character.getNumericValue(accountNumber.charAt(i)) * weights[i];
                }

                int remainder = sum % 11;

                if (remainder == 0 || remainder == 2) {
                    return true;
                } else {
                    return false;
                }

            } else if (bankName.equals("דיסקונט")||bankName.equals("מרכנתיל דיסקונט")) {
                if (accountNumber.length() != 9) {
                    return false;
                }

                int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                int sum = 0;

                for (int i = 0; i < accountNumber.length(); i++) {
                    sum += Character.getNumericValue(accountNumber.charAt(i)) * weights[i];
                }

                int remainder = sum % 11;

                if (remainder == 0 || remainder == 2 || remainder == 4) {
                    return true;
                } else {
                    return false;
                }

            } else if (bankName.equals("בנק מזרחי")) {
                if (accountNumber.length() != 9 || branchCode.length() != 3) {
                    return false;
                }

                int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                int sum = 0;

                for (int i = 0; i < accountNumber.length(); i++) {
                    sum += Character.getNumericValue(accountNumber.charAt(i)) * weights[i];
                }

                int remainder = sum % 11;

                if (remainder == 0 || remainder == 2 || remainder == 4) {
                    // החלף שם הסניף למספר מתחת ל-400
                    int branch = Integer.parseInt(branchCode);
                    if (branch > 400) {
                        branch -= 400;
                    }

                    String adjustedBranch = String.format("%03d", branch);

                    // בדיקה נוספת לפי המספר המתוקן של הסניף
                    int[] branchWeights = {9, 8, 7, 6, 5, 4, 3, 2, 1};
                    int branchSum = 0;

                    for (int i = 0; i < adjustedBranch.length(); i++) {
                        branchSum += Character.getNumericValue(adjustedBranch.charAt(i)) * branchWeights[i];
                    }

                    int branchRemainder = branchSum % 11;

                    if (branchRemainder == 0 || branchRemainder == 2 || branchRemainder == 4) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            } else if (bankName.equals("בנק הדואר")) {
                if (accountNumber.length() != 10) {
                    return false;
                }

                int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                int sum = 0;

                for (int i = 0; i < accountNumber.length(); i++) {
                    sum += Character.getNumericValue(accountNumber.charAt(i)) * weights[i];
                }

                return sum % 10 == 0;

            }

            return false;
        }



        private String getBankCode(String bankName) {


            // כאן ניתן לממש את הלוגיקה לקבלת קוד הבנק בהתאם לשם הבנק
            return "";
        }

        public void showDatePickerDialogCardValidity(View view) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            calendar.set(year, monthOfYear, dayOfMonth);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            String cardValidity = sdf.format(calendar.getTime());
                            editTextCardValidity.setText(cardValidity);
                        }
                    },
                    year, month, day
            );

            datePickerDialog.show();
        }


        public void showDatePickerDialogFrom(View view) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            final EditText editTextBillingPeriodFrom = findViewById(R.id.editTextBillingPeriodFrom);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            calendar.set(year, monthOfYear, dayOfMonth);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            String selectedDate = sdf.format(calendar.getTime());

                            editTextBillingPeriodFrom.setText(selectedDate);
                        }
                    },
                    year, month, day
            );

            datePickerDialog.show();
        }

        public void showDatePickerDialogTo(View view) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            final EditText editTextBillingPeriodTo = findViewById(R.id.editTextBillingPeriodTo);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            calendar.set(year, monthOfYear, dayOfMonth);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            String selectedDate = sdf.format(calendar.getTime());

                            editTextBillingPeriodTo.setText(selectedDate);
                        }
                    },
                    year, month, day
            );

            datePickerDialog.show();
        }

        public void showDatePickerFirstCharge(View view) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            final EditText editTextFirstCharge = findViewById(R.id.editTextFirstCharge);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            calendar.set(year, monthOfYear, dayOfMonth);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            String selectedDate = sdf.format(calendar.getTime());

                            editTextFirstCharge.setText(selectedDate);
                        }
                    },
                    year, month, day
            );

            datePickerDialog.show();
        }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}


