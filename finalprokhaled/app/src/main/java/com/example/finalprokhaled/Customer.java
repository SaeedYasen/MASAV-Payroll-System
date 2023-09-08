package com.example.finalprokhaled;
public class Customer {
    private String customerNumber;
    private String customerName;
    private String customerID;

    public Customer() {
        // Default constructor required for Firebase
    }

    public Customer(String customerNumber, String customerName, String customerID) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerID = customerID;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}

