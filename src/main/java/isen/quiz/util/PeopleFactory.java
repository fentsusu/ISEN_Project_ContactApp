package isen.quiz.util;

import isen.quiz.model.Person;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PeopleFactory {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");

    public static List<Person> getPeople(String filePath) {
        List<Person> people = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(getPath(filePath));
            for (String line : lines) {
                String[] record = line.split(",");
                Person person = new Person();
                person.setLastName(record[2]);
                person.setFirstName(record[3]);
                person.setNickName(record[3]);
                person.setPhoneNumber(record[4]);
                person.setAddress(record[5]);
                //person.getEmailAddress(record[6]);


                //person.setBirthDate(Date(record[9]));
                people.add(person);
            }
            return people;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Path getPath(String filePath) throws URISyntaxException {
        return Paths.get(PeopleFactory.class.getClassLoader().getResource(filePath).toURI());
    }
}
