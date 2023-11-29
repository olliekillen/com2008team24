package com.sheffield;

public class BankDetails {
    private String bankCardNumber;
    private String expiryDate;
    private Integer securityCode;
    private String cardHolderName;
    private Integer userId;

    public BankDetails(String BankCardNumber, String ExpiryDate, int SecurityCode, String CardHolderName, Integer id){
    this.bankCardNumber = BankCardNumber;
    this.expiryDate = ExpiryDate;
    this.securityCode = SecurityCode;
    this.cardHolderName = CardHolderName;
    this.userId = id;
    }

    public String getBankCardNumber(){return bankCardNumber;}
    public String getCardNumberHidden(){
        String s = "";

        s = "*".repeat(bankCardNumber.length()-2) + bankCardNumber.substring(bankCardNumber.length()-2);
        return s;
    }
    public String getExpiryDate(){return expiryDate;}
    public Integer getSecurityCode(){return securityCode;}
    public String getCardHolderName(){return cardHolderName;}
    public Integer getUserId(){return userId;}
}
