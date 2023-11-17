package com.aral.service;

import com.aral.dto.request.CreateBookRequestDto;
import com.aral.dto.request.UpdateBookRequestDto;
import com.aral.dto.response.CreateBookResponseDto;
import com.aral.dto.response.UpdateBookResponseDto;
import com.aral.mapper.IBookMapper;
import com.aral.repository.IBookRepository;
import com.aral.repository.entity.Book;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService extends ServiceManager<Book, Long> {

    private final IBookRepository repository;

    public BookService(IBookRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public CreateBookResponseDto create(CreateBookRequestDto dto) {

        Book book = save(IBookMapper.INSTANCE.fromCreateBookRequestDto(dto));
        return CreateBookResponseDto.builder()
                .message("Kitap kaydı başarılı").build();
    }

    public UpdateBookResponseDto update(UpdateBookRequestDto dto){
        Optional<Book> existingOptionalBook = repository.findOptionalByProductId(dto.getProductId());
        if(existingOptionalBook.isPresent()){
            Book existingBook = existingOptionalBook.get();
            Book updateBook = update(IBookMapper.INSTANCE.fromUpdateBookRequestDto(dto, existingBook));
            return UpdateBookResponseDto.builder()
                    .message(updateBook.getProductName()+" adlı kitabınız başarıyla güncellendi.").build();
        }else{
        return UpdateBookResponseDto.builder()
                .message("Güncellemeye çalıştığınız kitap bulunamadı").build();}

    }

}
