package com.aral.dto.request;

import com.aral.repository.entity.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateBookRequestDto {
    Long productId;
    String productName;
    String productDescription;
    Double productPrice;
    String author;
    Integer stockQuantity;
    String publisher;
    Integer pageCount;
    @Enumerated(EnumType.STRING)
    BookType type;
    LocalDate publishDate;
}
