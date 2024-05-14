package se.lexicon.model;

import java.util.Random;

public class User {

    // fields
    private String username;
    private String password;
    private boolean expired;

    // constructors
    //Registration
    public User(String username) {
        this.username = username;

    }
    //Authentication
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //Expiration
    public User(String username, String password, boolean expired) {
        this.username = username;
        this.password = password;
        this.expired = expired;
    }

    //getters and setters

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isExpired() {
        return expired;
    }

    public String userInfo() {
        return "username: " + username + " ,password: " + password ;

    }

    //method for generate random passwords
    private String generateRandomPassword() {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 10;
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        //iterate through the password length
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            char randomCharacter = allowedCharacters.charAt(randomIndex);
            stringBuilder.append(randomCharacter);
        }
        return stringBuilder.toString();
    }

    //method for new password
    public void newPassword() {
       this.password = generateRandomPassword();
    }

}
