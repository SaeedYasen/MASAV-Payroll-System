package com.example.finalprokhaled;
public class BankDetails {
    private String bankName;
    private String bankCode;
    private String branchCode;
    private String accountNumber;

    public BankDetails() {
        // Default constructor required for Firebase
    }

    public BankDetails(String bankName, String bankCode, String branchCode, String accountNumber) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
