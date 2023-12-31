package com.aral.repository;

import com.aral.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findOptionalByProductId(Long id);

}
