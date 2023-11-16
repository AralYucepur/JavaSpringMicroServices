package com.aral.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@SuperBuilder
@Table(name = "tblbook")
@Entity
public class Book extends Product{
    String author;
    String publisher;
    Integer pageCount;
    @Enumerated(EnumType.STRING)
    BookType type;
    LocalDate publishDate;

}
