package br.com.roger.services;

import br.com.roger.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private static final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person getById(String id){

        logger.info("Finding person by ID!");

        Person mockPerson = new Person();
        mockPerson.setId(counter.incrementAndGet());
        mockPerson.setFirstName("Roger");
        mockPerson.setLastName("Baccaglini");
        mockPerson.setAddress("Rua x, 125");
        mockPerson.setGender("male");

        return mockPerson;
    }

    public List<Person> getAll(){
        return mockPersonList();
    }

    private List<Person> mockPersonList() {

        logger.info("Finding all persons!");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person mockPerson = new Person();
            mockPerson.setId(counter.incrementAndGet());
            mockPerson.setFirstName("Person " + i);
            mockPerson.setLastName("LastName " + i);
            mockPerson.setAddress("Address " + i);
            mockPerson.setGender("male");
            persons.add(mockPerson);
        }
        return persons;
    }
}
