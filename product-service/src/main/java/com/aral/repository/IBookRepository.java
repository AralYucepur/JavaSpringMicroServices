package com.aral.repository;

import com.aral.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findOptionalById(Long id);

}
