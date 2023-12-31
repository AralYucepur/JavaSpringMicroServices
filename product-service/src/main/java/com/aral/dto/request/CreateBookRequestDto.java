package com.aral.dto.request;

import com.aral.repository.entity.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateBookRequestDto {

    @NotBlank(message = "Kitap ismi boş geçilemez.")
    @Size(min = 1,max = 32)
    String productName;
    @NotBlank(message = "Yazar ismi boş geçilemez.")
    @Size(min = 1,max = 32)
    String author;
    @NotBlank(message = "Yayıncı ismi boş geçilemez.")
    @Size(min = 1,max = 32)
    String publisher;
    Integer pageCount;
    BookType type;
    LocalDate publishDate;

}
