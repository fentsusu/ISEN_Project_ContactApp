package isen.quiz.model;
import java.util.Date;
import java.util.regex.Pattern;
public class Person {

    private int id;
    private String lastName;
    private String firstName;
    private String nickName;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private Date birthDate;


    public Person(int id, String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, Date birthDate) {
        this.id = id;
        validateAndSetFirstName(firstName);
        validateAndSetLastName(lastName);
        this.nickName = nickName;
        validateAndSetPhoneNumber(phoneNumber);
        this.address = address;
        validateAndSetEmailAddress(emailAddress);
        this.birthDate = birthDate;
    }

    public Person() {

    }

    public Person(String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, java.sql.Date birthDate) {

        validateAndSetFirstName(firstName);
        validateAndSetLastName(lastName);
        this.nickName = nickName;
        validateAndSetPhoneNumber(phoneNumber);
        this.address = address;
        validateAndSetEmailAddress(emailAddress);
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    private void validateAndSetFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty() || firstName.length() > 50) {
            throw new IllegalArgumentException("First name must be non-empty and less than 50 characters.");
        }
        this.firstName = firstName;
    }

    private void validateAndSetLastName(String lastName) {
        if (lastName == null || lastName.isEmpty() || lastName.length() > 50) {
            throw new IllegalArgumentException("Last name must be non-empty and less than 50 characters.");
        }
        this.lastName = lastName;
    }

    private void validateAndSetPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number.");
        }
        this.phoneNumber = phoneNumber;
    }

    private void validateAndSetEmailAddress(String emailAddress) {
        if (emailAddress == null || !isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.emailAddress = emailAddress;
    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Use a regular expression for phone number validation
        String regexPattern = "^(\\d{10})$"; // Ten-digit number
        return Pattern.matches(regexPattern, phoneNumber);
    }

    private boolean isValidEmail(String email) {
        // Use a regular expression for email validation
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.matches(regexPattern, email);
    }
}
