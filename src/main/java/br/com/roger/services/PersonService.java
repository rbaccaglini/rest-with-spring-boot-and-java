package br.com.roger.services;

import br.com.roger.controllers.PersonController;
import br.com.roger.data.vo.v1.PersonVO;
import br.com.roger.exceptios.RequireObjectIsNullException;
import br.com.roger.exceptios.ResourceNotFoundException;
import br.com.roger.mapper.MyMapper;
import br.com.roger.models.Person;
import br.com.roger.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO getById(Long id) throws Exception {
        logger.info("Finding person by ID!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));

        PersonVO vo = MyMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).getPersonById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> getAll(){
        logger.info("Finding all persons!");
        List<PersonVO> persons = MyMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
            .forEach(p -> {
                try {
                    p.add(linkTo(methodOn(PersonController.class).getPersonById(p.getKey())).withSelfRel());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        return  persons;
    }

    public PersonVO createPerson(PersonVO person){

        if (person == null) throw new RequireObjectIsNullException();

        logger.info("Creating new person!");
        Person entity = MyMapper.parseObject(person, Person.class);
        PersonVO vo = MyMapper.parseObject(repository.save(entity), PersonVO.class);
        try {
            vo.add(linkTo(methodOn(PersonController.class).getPersonById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public PersonVO updatePerson(PersonVO person){
        logger.info("Updating person!");

        if (person == null) throw new RequireObjectIsNullException();

        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));

        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());

        PersonVO vo = MyMapper.parseObject(repository.save(entity), PersonVO.class);
        try {
            vo.add(linkTo(methodOn(PersonController.class).getPersonById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public void deletePerson(Long id){
        logger.info("Deleting person by id!");

        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));
        repository.delete(person);
    }
}
