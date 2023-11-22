package com.sheffield;

public class BankDetails {
    private Integer bankCardNumber;
    private String expiryDate;
    private Integer securityCode;
    private String cardHolderName;
    private Integer userId;

    public BankDetails(int BankCardNumber, String ExpiryDate, int SecurityCode, String CardHolderName, Integer id){
    this.bankCardNumber = BankCardNumber;
    this.expiryDate = ExpiryDate;
    this.securityCode = SecurityCode;
    this.cardHolderName = CardHolderName;
    this.userId = id;
    }

    public Integer getBankCardNumber(){return bankCardNumber;}
    public String getExpiryDate(){return expiryDate;}
    public Integer getSecurityCode(){return securityCode;}
    public String getCardHolderName(){return cardHolderName;}
    public Integer getUserId(){return userId;}
}
