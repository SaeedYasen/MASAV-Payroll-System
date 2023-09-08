package com.example.finalprokhaled;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String TABLE_BANK_DETAILS = "bank_details";
    private static final String TABLE_BILLING_DETAILS = "billing_details";

    // Common column names
    private static final String COLUMN_ID = "id";

    // Customers table columns
    private static final String COLUMN_CUSTOMER_NUMBER = "customer_number";

    private static final String COLUMN_CUSTOMER_NAME = "customer_name";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";

    // Bank Details table columns
    private static final String COLUMN_BANK_NAME = "bank_name";
    private static final String COLUMN_BANK_CODE = "bank_code";
    private static final String COLUMN_BRANCH_CODE = "branch_code";
    private static final String COLUMN_ACCOUNT_NUMBER = "account_number";

    // Billing Details table columns
    private static final String COLUMN_BILLING_DESCRIPTION = "billing_description";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_BILLING_PERIOD_FROM = "billing_period_from";
    private static final String COLUMN_BILLING_PERIOD_TO = "billing_period_to";
    private static final String COLUMN_FIRST_CHARGE = "first_charge";
    private static final String COLUMN_BILLING_DAY = "billing_day";
    private static final String COLUMN_CHARGE_AMOUNT = "charge_amount";
    private static final String COLUMN_NUMBER_OF_CHARGES = "number_of_charges";

    // Create table statements
    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE " + TABLE_CUSTOMERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CUSTOMER_NUMBER + " TEXT,"
            + COLUMN_CUSTOMER_NAME + " TEXT,"
            + COLUMN_CUSTOMER_ID + " TEXT"
            + ")";

    private static final String CREATE_TABLE_BANK_DETAILS = "CREATE TABLE " + TABLE_BANK_DETAILS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BANK_NAME + " TEXT,"
            + COLUMN_BANK_CODE + " TEXT,"
            + COLUMN_BRANCH_CODE + " TEXT,"
            + COLUMN_ACCOUNT_NUMBER + " TEXT"
            + ")";

    private static final String CREATE_TABLE_BILLING_DETAILS = "CREATE TABLE " + TABLE_BILLING_DETAILS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_BILLING_DESCRIPTION + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_BILLING_PERIOD_FROM + " TEXT,"
            + COLUMN_BILLING_PERIOD_TO + " TEXT,"
            + COLUMN_FIRST_CHARGE + " TEXT,"
            + COLUMN_BILLING_DAY + " TEXT,"
            + COLUMN_CHARGE_AMOUNT + " TEXT,"
            + COLUMN_NUMBER_OF_CHARGES + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_BANK_DETAILS);
        db.execSQL(CREATE_TABLE_BILLING_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILLING_DETAILS);
        onCreate(db);
    }

    // Add customer record
    public void addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMER_NUMBER, customer.getCustomerNumber());
        values.put(COLUMN_CUSTOMER_NAME, customer.getCustomerName());
        values.put(COLUMN_CUSTOMER_ID, customer.getCustomerID());
        db.insert(TABLE_CUSTOMERS, null, values);
        db.close();
    }

    // Add bank details record
    public void addBankDetails(BankDetails bankDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BANK_NAME, bankDetails.getBankName());
        values.put(COLUMN_BANK_CODE, bankDetails.getBankCode());
        values.put(COLUMN_BRANCH_CODE, bankDetails.getBranchCode());
        values.put(COLUMN_ACCOUNT_NUMBER, bankDetails.getAccountNumber());
        db.insert(TABLE_BANK_DETAILS, null, values);
        db.close();
    }

    // Add billing details record
    public void addBillingDetails(BillingDetails billingDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BILLING_DESCRIPTION, billingDetails.getBillingDescription());
        values.put(COLUMN_ADDRESS, billingDetails.getAddress());
        values.put(COLUMN_BILLING_PERIOD_FROM, billingDetails.getBillingPeriodFrom());
        values.put(COLUMN_BILLING_PERIOD_TO, billingDetails.getBillingPeriodTo());
        values.put(COLUMN_FIRST_CHARGE, billingDetails.getFirstCharge());
        values.put(COLUMN_BILLING_DAY, billingDetails.getBillingday());
        values.put(COLUMN_CHARGE_AMOUNT, billingDetails.getChargeAmount());
        values.put(COLUMN_NUMBER_OF_CHARGES, billingDetails.getNumerOfCharges());
        db.insert(TABLE_BILLING_DETAILS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<BillingDetails> getAllBillingDetails() {
        List<BillingDetails> billingDetailsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BILLING_DETAILS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {

                String billingDescription = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_DESCRIPTION));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                String billingPeriodFrom = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_PERIOD_FROM));
                String billingPeriodTo = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_PERIOD_TO));
                String firstCharge = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_CHARGE));
                String billingDay = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_DAY));
                String chargeAmount = cursor.getString(cursor.getColumnIndex(COLUMN_CHARGE_AMOUNT));
                String numberOfCharges = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_OF_CHARGES));

                BillingDetails billingDetails = new BillingDetails(billingDescription, address, billingPeriodFrom,
                        billingPeriodTo, firstCharge, billingDay, chargeAmount, numberOfCharges);
                billingDetailsList.add(billingDetails);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return billingDetailsList;
    }

    @SuppressLint("Range")
    // פונקציה לקבלת רשימת כל רשומות נתוני הבנק
    public List<BankDetails> getAllBankDetails() {
        List<BankDetails> bankDetailsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BANK_DETAILS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String bankName = cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME));
                String bankCode = cursor.getString(cursor.getColumnIndex(COLUMN_BANK_CODE));
                String branchCode = cursor.getString(cursor.getColumnIndex(COLUMN_BRANCH_CODE));
                String accountNumber = cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_NUMBER));

                BankDetails bankDetails = new BankDetails(bankName, bankCode, branchCode, accountNumber);
                bankDetailsList.add(bankDetails);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return bankDetailsList;
    }

    @SuppressLint("Range")
    // פונקציה לקבלת רשימת כל רשומות הלקוחות
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUSTOMERS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String customerNumber = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NUMBER));
                String customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME));
                String customerID = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_ID));

                Customer customer = new Customer(customerNumber, customerName, customerID);
                customerList.add(customer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return customerList;
    }

    @SuppressLint("Range")

    public List<String> getAllFirstCharges() {
        List<String> firstChargesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BILLING_DETAILS,
                new String[]{COLUMN_FIRST_CHARGE}, // Select only COLUMN_FIRST_CHARGE
                null, null,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String firstCharge = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_CHARGE));
                firstChargesList.add(firstCharge);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return firstChargesList;
    }


    @SuppressLint("Range")
    public ArrayList<String> getDatesBetween(String startDate, String endDate) {
        ArrayList<String> dates = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT DISTINCT " + COLUMN_FIRST_CHARGE + " FROM " + TABLE_BILLING_DETAILS +
                " WHERE " + COLUMN_FIRST_CHARGE + " BETWEEN '" + startDate + "' AND '" + endDate + "'";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String firstCharge = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_CHARGE));
                dates.add(firstCharge);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return dates;
    }


    @SuppressLint("Range")
    public ArrayList<String> Givemedates(String year, String month) {
        ArrayList<String> dates = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat databaseDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(year));
            calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1); // Set to the first day of the month

            String startOfMonth = databaseDateFormat.format(calendar.getTime());

            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1); // Set to the last day of the month

            String endOfMonth = databaseDateFormat.format(calendar.getTime());

            String query = "SELECT DISTINCT " + COLUMN_FIRST_CHARGE + " FROM " + TABLE_BILLING_DETAILS +
                    " WHERE " + COLUMN_FIRST_CHARGE + " BETWEEN '" + startOfMonth + "' AND '" + endOfMonth + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    String firstCharge = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_CHARGE));
                    dates.add(firstCharge);
                } while (cursor.moveToNext());
            }

            cursor.close();
        } finally {
            db.close();
        }

        return dates;
    }


    @SuppressLint("Range")
    public List<CombinedDetailsModel> getBillingDetailsByDate(String selectedDate) {
        List<CombinedDetailsModel> billingDetailsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_BILLING_DETAILS + " WHERE " + COLUMN_FIRST_CHARGE + " = '" + selectedDate + "'", null);
        if (cursor.moveToFirst()) {
            do {
                billingDetailsList.add(getCombinedDetailsById(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))));
            } while (cursor.moveToNext());
        } else {
            billingDetailsList=null;
        }

        cursor.close();
        db.close();
        return billingDetailsList;
    }

    @SuppressLint("Range")
    public List<CombinedDetailsModel> getBillingDetailsByDateAndCustomer(String selectedDate, String customerName) {
        List<CombinedDetailsModel> billingDetailsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT bd." + COLUMN_ID + " FROM " + TABLE_BILLING_DETAILS + " bd" +
                " JOIN " + TABLE_CUSTOMERS + " c ON bd." + COLUMN_ID  + " = c." + COLUMN_ID +
                " WHERE bd." + COLUMN_FIRST_CHARGE + " = ?" +
                " AND c." + COLUMN_CUSTOMER_NAME + " = ?", new String[]{selectedDate, customerName});

        if (cursor.moveToFirst()) {
            do {
                billingDetailsList.add(getCombinedDetailsById(cursor.getInt(cursor.getColumnIndex(COLUMN_ID))));
            } while (cursor.moveToNext());
        }

        cursor.close(); // Close the first cursor
        db.close();
        return billingDetailsList;
    }




    @SuppressLint("Range")
    public CombinedDetailsModel getCombinedDetailsById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " +
                "bd." + COLUMN_BILLING_DESCRIPTION + ", " +
                "bd." + COLUMN_ADDRESS + ", " +
                "bd." + COLUMN_BILLING_PERIOD_FROM + ", " +
                "bd." + COLUMN_BILLING_PERIOD_TO + ", " +
                "bd." + COLUMN_FIRST_CHARGE + ", " +
                "bd." + COLUMN_BILLING_DAY + ", " +
                "bd." + COLUMN_CHARGE_AMOUNT + ", " +
                "bd." + COLUMN_NUMBER_OF_CHARGES + ", " +
                "c." + COLUMN_CUSTOMER_NUMBER + ", " +
                "c." + COLUMN_CUSTOMER_NAME + ", " +
                "c." + COLUMN_CUSTOMER_ID + ", " +
                "b." + COLUMN_BANK_NAME + ", " +
                "b." + COLUMN_BANK_CODE + ", " +
                "b." + COLUMN_BRANCH_CODE + ", " +
                "b." + COLUMN_ACCOUNT_NUMBER  +
                " FROM " + TABLE_BILLING_DETAILS + " bd" +
                " LEFT JOIN " + TABLE_CUSTOMERS + " c ON bd." + COLUMN_ID + " = c." + COLUMN_ID +
                " LEFT JOIN " + TABLE_BANK_DETAILS + " b ON bd." + COLUMN_ID + " = b." + COLUMN_ID +
                " WHERE bd." + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        CombinedDetailsModel combinedDetails = null;
        if (cursor != null && cursor.moveToFirst()) {
            String billingDescription = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_DESCRIPTION));
            String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
            String billingPeriodFrom = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_PERIOD_FROM));
            String billingPeriodTo = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_PERIOD_TO));
            String firstCharge = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_CHARGE));
            String billingDay = cursor.getString(cursor.getColumnIndex(COLUMN_BILLING_DAY));
            String chargeAmount = cursor.getString(cursor.getColumnIndex(COLUMN_CHARGE_AMOUNT));
            String numberOfCharges = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_OF_CHARGES));
            String customerNumber = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NUMBER));
            String customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME));
            String customerID = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_ID));
            String bankName = cursor.getString(cursor.getColumnIndex(COLUMN_BANK_NAME));
            String bankCode = cursor.getString(cursor.getColumnIndex(COLUMN_BANK_CODE));
            String branchCode = cursor.getString(cursor.getColumnIndex(COLUMN_BRANCH_CODE));
            String accountNumber = cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_NUMBER));

            combinedDetails = new CombinedDetailsModel(
                    billingDescription, address, billingPeriodFrom, billingPeriodTo,
                    firstCharge, billingDay, chargeAmount, numberOfCharges,
                    customerNumber, customerName, customerID,
                    bankName, bankCode, branchCode, accountNumber
            );
            cursor.close();
        }

        db.close();

        return combinedDetails;
    }





    public List<String> getDistinctCustomerName() {
        List<String> customerNames = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, TABLE_CUSTOMERS, new String[]{COLUMN_CUSTOMER_NAME}, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String customerNumber = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME));
                customerNames.add(customerNumber);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return customerNames;
    }







}


