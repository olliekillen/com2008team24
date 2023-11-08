package orders;

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
}
