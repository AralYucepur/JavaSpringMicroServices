package com.aral.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tblbook")
@Entity
public class Book extends Product{
    String name;
    String author;
    String publisher;
    Integer pageCount;
    BookType type;
    LocalDate publishDate;

}
