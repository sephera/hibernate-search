package com.example.hibernatesearch;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/")
    public ResponseEntity<List<Book>> search() {
        SearchSession searchSession = Search.session(entityManager);

        SearchResult<Book> result = searchSession.search(Book.class)
                .where(f -> f.match()
                        .fields("title")
                        .matching("Title"))
                .fetch(20);

        List<Book> hits = result.hits();

        return ResponseEntity.ok(hits);
    }

    @PostMapping("/")
    public ResponseEntity<Book> create() {
        final Book book = new Book();
        book.setTitle("Title " + ThreadLocalRandom.current().nextInt());
        book.setIsbn("isbn" + ThreadLocalRandom.current().nextInt());
        book.setPageCount(ThreadLocalRandom.current().nextInt());
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }
}
