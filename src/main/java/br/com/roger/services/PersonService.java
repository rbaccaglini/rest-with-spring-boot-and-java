package br.com.roger.services;

import br.com.roger.data.vo.v1.PersonVO;
import br.com.roger.exceptios.ResourceNotFoundException;
import br.com.roger.models.Person;
import br.com.roger.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private static final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO getById(Long id){
        logger.info("Finding person by ID!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));
    }

    public List<PersonVO> getAll(){
        logger.info("Finding all persons!");
        return repository.findAll();
    }

    public PersonVO createPerson(PersonVO person){
        logger.info("Creating new person!");
        return repository.save(person);
    }

    public PersonVO updatePerson(PersonVO person){
        logger.info("Updating person!");

        PersonVO entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));

        entity.setGender(entity.getGender());
        entity.setAddress(entity.getAddress());
        entity.setFirstName(entity.getFirstName());
        entity.setLastName(entity.getLastName());

        return repository.save(person);
    }

    public void deletePerson(Long id){
        logger.info("Deleting person by id!");

        PersonVO person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));

        repository.delete(person);
    }
}
