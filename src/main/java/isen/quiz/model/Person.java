package isen.quiz.model;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class Person {

    private int id;
    private String lastName;
    private String firstName;
    private String nickName;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private String birthDate;


    public Person(){}
    public Person(int id, String lastName, String firstName, String nickName,
                  String phoneNumber, String address, String emailAddress, String birthDate) {

        this.id = id;
        validateAndSetFirstName(firstName);
        validateAndSetLastName(lastName);
        this.nickName = nickName;
        validateAndSetPhoneNumber(phoneNumber);
        this.address = address;
        validateAndSetEmailAddress(emailAddress);
        this.birthDate = birthDate;
    }

    public Person(String lastName, String firstName, String nickName,
                  String phoneNumber, String address, String emailAddress, String birthDate) {
        validateAndSetFirstName(firstName);
        validateAndSetLastName(lastName);
        this.nickName = nickName;
        validateAndSetPhoneNumber(phoneNumber);
        this.address = address;
        validateAndSetEmailAddress(emailAddress);
        this.birthDate = birthDate;
    }

    /*public List<Person> getAllPersons() throws SQLException {
        List<Person> listOfPersons = new ArrayList<>();

//        String url = "jdbc:sqlite:sqlite.db";
//        Connection connection = DriverManager.getConnection(url);
//        Statement statement = connection.createStatement();
//        String sqlQuery = "SELECT * FROM person";
//        ResultSet result = statement.executeQuery(sqlQuery);
//        return (List<Person>) result;

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sqlQuery = "SELECT * FROM person";
                try (ResultSet results = statement.executeQuery(sqlQuery)) {
                    while (results.next()) {
                        // Create person object and add to listOfPersons array list
                        Person person = new Person(
                                results.getInt("idperson"),
                                results.getString("firstname"),
                                results.getString("lastname"),
                                results.getString("nickname")
                        );


                        person.setPhoneNumber( results.getString("phone_number"));
                        person.setEmailAddress(results.getString("email_address"));
                        person.setAddress(results.getString("address"));


                        // Check if the other fields are not null and set their values correspondingly
                        if (results.getObject("address") != null)
                            person.setAddress(results.getString("address"));
                        if (results.getString("email_address") != null)
                            person.setEmailAddress(results.getString("email_address"));
                        if (results.getString("phone_number") != null)
                            person.setPhoneNumber(results.getString("phone_number"));
                        if (results.getDate("birth_date") != null)
                            person.setDateOfBirth(results.getDate("birth_date").toLocalDate());
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            // Print stack trace to help us debug the issue.
            e.printStackTrace();
        }
        return listOfPersons;

    }*/


    //Getters and Setters
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

    public String getAddress() {return address;}

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    //Method for Input Validation
    private void validateAndSetFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty() || firstName.length() > 50) {
            throw new IllegalArgumentException("First name must be non-empty and less than 50 characters.");
        }
        this.firstName = firstName;
    }

    //Method for the Lastname must be non-empty and less than 50 characters
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
