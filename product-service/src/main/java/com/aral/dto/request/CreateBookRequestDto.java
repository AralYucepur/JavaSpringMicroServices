package com.aral.dto.request;

import com.aral.repository.entity.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateBookRequestDto {

    String productName;
    String author;
    String publisher;
    Integer pageCount;
    BookType type;
    LocalDate publishDate;

}
