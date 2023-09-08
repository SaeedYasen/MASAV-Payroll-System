package com.example.finalprokhaled;

public class BillingDetails {
    private String billingDescription;
    private String address;
    private String billingPeriodFrom;
    private String billingPeriodTo;
    private String firstCharge;
    private String billingday;
    private String chargeAmount;
    private String numerOfCharges;
    // Add more fields as needed

    public BillingDetails(String billingDescription, String address, String billingPeriodFrom, String billingPeriodTo, String firstCharge, String billingday, String chargeAmount, String numerOfCharges) {
        this.billingDescription = billingDescription;
        this.address = address;
        this.billingPeriodFrom = billingPeriodFrom;
        this.billingPeriodTo = billingPeriodTo;
        this.firstCharge = firstCharge;
        this.billingday = billingday;
        this.chargeAmount = chargeAmount;
        this.numerOfCharges = numerOfCharges;
    }

    // Getters
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

    public String getBillingday() {
        return billingday;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public String getNumerOfCharges() {
        return numerOfCharges;
    }

    // Setters
    public void setBillingDescription(String billingDescription) {
        this.billingDescription = billingDescription;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBillingPeriodFrom(String billingPeriodFrom) {
        this.billingPeriodFrom = billingPeriodFrom;
    }

    public void setBillingPeriodTo(String billingPeriodTo) {
        this.billingPeriodTo = billingPeriodTo;
    }

    public void setFirstCharge(String firstCharge) {
        this.firstCharge = firstCharge;
    }

    public void setBillingday(String billingday) {
        this.billingday = billingday;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public void setNumerOfCharges(String numerOfCharges) {
        this.numerOfCharges = numerOfCharges;
    }


    // Add more getters and setters as needed for additional fields
}
