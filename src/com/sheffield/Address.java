package com.sheffield;

public class Address {
    private String postCode;
    private Integer houseNumber;
    private String roadName;
    private String city;

    public Address (String postcode, int housenumber, String roadname , String City){
        this.postCode = postcode;
        this.houseNumber = housenumber;
        this.roadName = roadname;
        this.city = City;
    }
    public String getAddress(){
        return (houseNumber + " " + roadName + ", " + city + " " + postCode);
    }
}
