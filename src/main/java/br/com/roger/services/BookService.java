package br.com.roger.services;

import br.com.roger.controllers.BookController;
import br.com.roger.data.vo.v1.BookVO;
import br.com.roger.exceptios.RequireObjectIsNullException;
import br.com.roger.exceptios.ResourceNotFoundException;
import br.com.roger.mapper.MyMapper;
import br.com.roger.models.Book;
import br.com.roger.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private final Logger logger = Logger.getLogger(BookService.class.getName());
    @Autowired
    BookRepository repository;

    public BookVO getById(Long id) throws Exception {
        logger.info("Finding book by ID!");
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));

        BookVO vo = MyMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
        return vo;
    }

    public List<BookVO> getAll() {
        logger.info("Finding all books!");
        List<BookVO> books = MyMapper.parseListObjects(repository.findAll(), BookVO.class);

        books
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BookController.class).getBookById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return books;
    }

    public BookVO createBook(BookVO book) {
        logger.info("Creating new book!");
        if (book == null) throw new RequireObjectIsNullException();

        Book entity = MyMapper.parseObject(book, Book.class);
        BookVO vo = MyMapper.parseObject(repository.save(entity), BookVO.class);
        try {
            vo.add(linkTo(methodOn(BookController.class).getBookById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public BookVO updateBook(BookVO newBook) {
        logger.info("Updating book!");

        Book entity = repository.findById(newBook.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));

        entity.setAuthor(newBook.getAuthor());
        entity.setLaunchDate(newBook.getLaunchDate());
        entity.setPrice(newBook.getPrice());
        entity.setTitle(newBook.getTitle());

        BookVO vo = MyMapper.parseObject(repository.save(entity), BookVO.class);
        try {
            vo.add(linkTo(methodOn(BookController.class).getBookById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public void deleteBook(Long id) {
        logger.info("Deleting book!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this id!"));

        repository.delete(entity);
    }

    public List<BookVO> getByTitle(String title) {
        logger.info("Finding books by title!");
        List<BookVO> books = MyMapper.parseListObjects(repository.findByTitleContaining(title), BookVO.class);

        books
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BookController.class).getBookById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return books;
    }
}
