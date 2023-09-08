package com.example.finalprokhaled;

public class BillingDetailsModel {
    private String id;
    private String billingDate;
    private String chargeAmount;
    private String customerName;
    private String customerId;
    private String address;
    private String bankName;
    private String branchCode;
    private String accountNumber;

    public BillingDetailsModel(String id, String billingDate, String chargeAmount, String customerName,
                               String customerId, String address, String bankName, String branchCode,
                               String accountNumber) {
        this.id = id;
        this.billingDate = billingDate;
        this.chargeAmount = chargeAmount;
        this.customerName = customerName;
        this.customerId = customerId;
        this.address = address;
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
    }

    public String getId() {
        return id;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
