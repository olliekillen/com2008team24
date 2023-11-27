package com.sheffield;

public class Staff extends User {

    public Staff(Integer userId, String email, String pass, String forename, String surname, String postcode,
                 Integer houseNum) {
        super(userId, email, pass, forename, surname, postcode, houseNum);
    }

}