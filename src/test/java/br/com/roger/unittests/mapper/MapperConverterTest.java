package br.com.roger.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import br.com.roger.data.vo.v1.BookVO;
import br.com.roger.data.vo.v1.PersonVO;
import br.com.roger.mapper.MyMapper;
import br.com.roger.models.Book;
import br.com.roger.models.Person;
import br.com.roger.unittests.mapper.mocks.MockBook;
import br.com.roger.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapperConverterTest {

    MockPerson inputMockPerson;
    MockBook inputMockBook;

    @BeforeEach
    public void setUp() {
        inputMockPerson = new MockPerson();
        inputMockBook = new MockBook();
    }

    // **** Person tests
    @Test
    public void parseEntityToVOTest() {
        PersonVO output = MyMapper.parseObject(inputMockPerson.mockEntity(), PersonVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = MyMapper.parseListObjects(inputMockPerson.mockEntityList(14), PersonVO.class);
        PersonVO outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Addres Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        PersonVO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Addres Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        PersonVO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = MyMapper.parseObject(inputMockPerson.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = MyMapper.parseListObjects(inputMockPerson.mockVOList(), Person.class);
        Person outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Addres Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        Person outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Addres Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        Person outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    // **** Book tests
    @Test
    public void parseBookEntityToVOTest() {
        Date date = new Date(1716449400);
        BookVO output = MyMapper.parseObject(inputMockBook.mockEntity(), BookVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Author 0", output.getAuthor());
        assertEquals("Title 0", output.getTitle());
        assertEquals(10.1, output.getPrice());
        assertEquals(date, output.getLaunchDate());
    }

    @Test
    public void parseBookEntityListToVOListTest() {
        Date date = new Date(1716449400);

        List<BookVO> outputList = MyMapper.parseListObjects(inputMockBook.mockEntityList(5), BookVO.class);

        BookVO outputZero = outputList.getFirst();
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Author 0", outputZero.getAuthor());
        assertEquals("Title 0", outputZero.getTitle());
        assertEquals(10.1, outputZero.getPrice());
        assertEquals(date, outputZero.getLaunchDate());

        BookVO outputFour = outputList.get(4);
        assertEquals(Long.valueOf(4L), outputFour.getKey());
        assertEquals("Author 4", outputFour.getAuthor());
        assertEquals("Title 4", outputFour.getTitle());
        assertEquals(14.1, outputFour.getPrice());
        assertEquals(date, outputFour.getLaunchDate());
    }

    @Test
    public void parseVOToBookEntityTest() {
        Date date = new Date(1716449400);

        Book output = MyMapper.parseObject(inputMockBook.mockBookVo(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Author 0", output.getAuthor());
        assertEquals("Title 0", output.getTitle());
        assertEquals(10.1, output.getPrice());
        assertEquals(date, output.getLaunchDate());
    }

    @Test
    public void parseVOListToBookEntityListTest() {
        Date date = new Date(1716449400);

        List<Book> outputList = MyMapper.parseListObjects(inputMockBook.mockVOList(5), Book.class);

        Book outputZero = outputList.getFirst();
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Author 0", outputZero.getAuthor());
        assertEquals("Title 0", outputZero.getTitle());
        assertEquals(10.1, outputZero.getPrice());
        assertEquals(date, outputZero.getLaunchDate());

        Book outputFour = outputList.get(4);
        assertEquals(Long.valueOf(4L), outputFour.getId());
        assertEquals("Author 4", outputFour.getAuthor());
        assertEquals("Title 4", outputFour.getTitle());
        assertEquals(14.1, outputFour.getPrice());
        assertEquals(date, outputFour.getLaunchDate());
    }
}
