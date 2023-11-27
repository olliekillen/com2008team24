package com.sheffield;

public class User {
    Integer userId;
    String email;
    String pass;
    String forename;
    String surname;
    String postcode;
    Integer houseNum;

    public User (Integer userId, String email, String pass, String forename, String surname, String postcode, Integer houseNum) {
        this.userId = userId;
        this.email = email;
        this.pass = pass;
        this.forename = forename;
        this.surname = surname;
        this.postcode = postcode;
        this.houseNum = houseNum;
    }

    public Integer getId() {return userId; }
    public String getEmail() {return email; }
    public String getpass() {
        String asterPass = "*".repeat(pass.length());

        return asterPass; }
    public String getForename() {return forename; }
    public String getSurname() {return surname; }
    public String getFullName(){return forename+" "+surname;}
    public String getPostcode() {return postcode; }
    public Integer getHouseNum() {return houseNum; }

}
