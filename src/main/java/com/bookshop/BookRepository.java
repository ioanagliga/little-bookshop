package com.bookshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByAuthor(String author);

    Book findByAuthorAndTitle(String author,String title);

}

