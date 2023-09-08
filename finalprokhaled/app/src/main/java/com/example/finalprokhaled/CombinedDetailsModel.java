package com.example.finalprokhaled;
public class CombinedDetailsModel {
    private String billingDescription;
    private String address;
    private String billingPeriodFrom;
    private String billingPeriodTo;
    private String firstCharge;
    private String billingDay;
    private String chargeAmount;
    private String numberOfCharges;
    private String customerNumber;
    private String customerName;
    private String customerID;
    private String bankName;
    private String bankCode;
    private String branchCode;
    private String accountNumber;

    public CombinedDetailsModel(String billingDescription, String address, String billingPeriodFrom,
                                String billingPeriodTo, String firstCharge, String billingDay,
                                String chargeAmount, String numberOfCharges,
                                String customerNumber, String customerName, String customerID,
                                String bankName, String bankCode, String branchCode, String accountNumber) {
        this.billingDescription = billingDescription;
        this.address = address;
        this.billingPeriodFrom = billingPeriodFrom;
        this.billingPeriodTo = billingPeriodTo;
        this.firstCharge = firstCharge;
        this.billingDay = billingDay;
        this.chargeAmount = chargeAmount;
        this.numberOfCharges = numberOfCharges;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerID = customerID;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
    }

    public String getBillingDescription() {
        return billingDescription;
    }

    public String getAddress() {
        return address;
    }

    public String getBillingPeriodFrom() {
        return billingPeriodFrom;
    }

    public String getBillingPeriodTo() {
        return billingPeriodTo;
    }

    public String getFirstCharge() {
        return firstCharge;
    }

    public String getBillingDay() {
        return billingDay;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public String getNumberOfCharges() {
        return numberOfCharges;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
