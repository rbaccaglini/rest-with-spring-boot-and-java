package br.com.roger.services;

import br.com.roger.models.Person;
import org.springframework.stereotype.Service;

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

}
