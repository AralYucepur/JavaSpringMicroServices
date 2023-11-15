package com.aral.service;

import com.aral.dto.request.CreateBookRequestDto;
import com.aral.dto.response.CreateBookResponseDto;
import com.aral.mapper.IBookMapper;
import com.aral.repository.IBookRepository;
import com.aral.repository.entity.Book;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceManager<Book, Long> {

    private final IBookRepository repository;

    public BookService(IBookRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public CreateBookResponseDto create(CreateBookRequestDto dto){
        Book book = save(IBookMapper.INSTANCE.fromCreateBookRequestDto(dto));
        return CreateBookResponseDto.builder()
                .message("Kitap kaydı başarılı").build();

    }
}
