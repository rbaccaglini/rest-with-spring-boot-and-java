package br.com.roger.unittests.mockito.services;

import br.com.roger.data.vo.v1.PersonVO;
import br.com.roger.exceptios.RequireObjectIsNullException;
import br.com.roger.models.Person;
import br.com.roger.repositories.PersonRepository;
import br.com.roger.services.PersonService;
import br.com.roger.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() throws Exception {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        PersonVO result = service.getById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals(result.getAddress(), "Addres Test1");
        assertEquals(result.getFirstName(), "First Name Test1");
        assertEquals(result.getLastName(), "Last Name Test1");
        assertEquals(result.getGender(), "Female");
    }

    @Test
    void getAll() {
        int qtd = 5;
        List<Person> list = input.mockEntityList(qtd);
        when(repository.findAll()).thenReturn(list);

        List<PersonVO> people = service.getAll();

        assertNotNull(people);
        assertEquals(people.size(), qtd);

        PersonVO person = people.get(1);
        assertNotNull(person.getKey());
        assertNotNull(person.getLinks());

        assertTrue(person.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals(person.getAddress(), "Addres Test1");
        assertEquals(person.getFirstName(), "First Name Test1");
        assertEquals(person.getLastName(), "Last Name Test1");
        assertEquals(person.getGender(), "Female");
    }

    @Test
    void createPerson() {
        Person persisted = input.mockEntity(1);
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(any(Person.class))).thenReturn(persisted);

        PersonVO result = service.createPerson(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals(result.getAddress(), "Addres Test1");
        assertEquals(result.getFirstName(), "First Name Test1");
        assertEquals(result.getLastName(), "Last Name Test1");
        assertEquals(result.getGender(), "Female");
    }

    @Test
    void createPersonWithNullPerson() {
        Exception ex = assertThrows(RequireObjectIsNullException.class,
            () -> service.createPerson(null)
        );

        String expected = "It is not allowed to persist a null objects!";

        assertEquals(ex.getMessage(), expected);

    }

    @Test
    void updatePerson() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted;
        persisted = entity;

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        PersonVO result = service.updatePerson(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        assertEquals(result.getAddress(), "Addres Test1");
        assertEquals(result.getFirstName(), "First Name Test1");
        assertEquals(result.getLastName(), "Last Name Test1");
        assertEquals(result.getGender(), "Female");
    }

    @Test
    void updatedPersonWithNullPerson() {
        Exception ex = assertThrows(RequireObjectIsNullException.class,
                () -> service.updatePerson(null)
        );

        String expected = "It is not allowed to persist a null objects!";

        assertEquals(ex.getMessage(), expected);
    }

    @Test
    void deletePerson() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.deletePerson(1L);
    }
}