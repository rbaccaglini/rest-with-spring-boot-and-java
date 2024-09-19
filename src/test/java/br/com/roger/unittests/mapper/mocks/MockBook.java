package br.com.roger.unittests.mapper.mocks;

import br.com.roger.data.vo.v1.BookVO;
import br.com.roger.mapper.MyMapper;
import br.com.roger.models.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockBookVo() {
        return mockBookVo(0);
    }
    
    public List<Book> mockEntityList(int qtd) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList(int qtd) {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            books.add(mockBookVo(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Date date = new Date(1716449400);

        Book entity = new Book();
        entity.setId(number.longValue());
        entity.setTitle("Title " + number);
        entity.setPrice(10.1 + number.doubleValue());
        entity.setAuthor("Author " + number);
        entity.setLaunchDate(date);

        return entity;
    }

    public BookVO mockBookVo(Integer number) {
        Date date = new Date(1716449400);

        BookVO vo = new BookVO();
        vo.setKey(number.longValue());
        vo.setTitle("Title " + number);
        vo.setPrice(10.1 + number.doubleValue());
        vo.setAuthor("Author " + number);
        vo.setLaunchDate(date);

        return vo;
    }

}
