package br.com.roger.unittests.mockito.services;

import br.com.roger.data.vo.v1.BookVO;
import br.com.roger.exceptios.RequireObjectIsNullException;
import br.com.roger.models.Book;
import br.com.roger.repositories.BookRepository;
import br.com.roger.services.BookService;
import br.com.roger.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() throws Exception {
        Date date = new Date(1716449400);

        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        BookVO result = service.getById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals(result.getAuthor(), "Author 1");
        assertEquals(result.getPrice(), 11.1);
        assertEquals(result.getTitle(), "Title 1");
        assertEquals(result.getLaunchDate(), date);
    }

    @Test
    void getAll() {

        Date date = new Date(1716449400);

        int qtd = 5;
        List<Book> list = input.mockEntityList(qtd);
        when(repository.findAll()).thenReturn(list);

        List<BookVO> books = service.getAll();

        assertNotNull(books);
        assertEquals(books.size(), qtd);

        BookVO result = books.get(1);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals(result.getAuthor(), "Author 1");
        assertEquals(result.getPrice(), 11.1);
        assertEquals(result.getTitle(), "Title 1");
        assertEquals(result.getLaunchDate(), date);
    }

    @Test
    void createBook() {
        Date date = new Date(1716449400);
        Book persisted = input.mockEntity(1);
        persisted.setId(1L);

        BookVO vo = input.mockBookVo(1);
        vo.setKey(1L);

        when(repository.save(any(Book.class))).thenReturn(persisted);

        BookVO result = service.createBook(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals(result.getAuthor(), "Author 1");
        assertEquals(result.getPrice(), 11.1);
        assertEquals(result.getTitle(), "Title 1");
        assertEquals(result.getLaunchDate(), date);
    }

    @Test
    void createBookWithNullPerson() {
        Exception ex = assertThrows(RequireObjectIsNullException.class,
                () -> service.createBook(null)
        );

        String expected = "It is not allowed to persist a null objects!";

        assertEquals(ex.getMessage(), expected);

    }

    @Test
    void updateBook() {
        Date date = new Date(1716449400);

        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted;
        persisted = entity;

        BookVO vo = input.mockBookVo(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        BookVO result = service.updateBook(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals(result.getAuthor(), "Author 1");
        assertEquals(result.getPrice(), 11.1);
        assertEquals(result.getTitle(), "Title 1");
        assertEquals(result.getLaunchDate(), date);
    }

    @Test
    void updatedBookWithNullPerson() {
        Exception ex = assertThrows(RequireObjectIsNullException.class,
                () -> service.updateBook(null)
        );

        String expected = "It is not allowed to persist a null objects!";

        assertEquals(ex.getMessage(), expected);
    }

    @Test
    void deleteBook() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.deleteBook(1L);
    }
}