package com.aral.controller;

import com.aral.dto.request.CreateBookRequestDto;
import com.aral.dto.request.UpdateBookRequestDto;
import com.aral.dto.response.CreateBookResponseDto;
import com.aral.dto.response.UpdateBookResponseDto;
import com.aral.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @CrossOrigin("*")
    @PostMapping("/create")
    public ResponseEntity<CreateBookResponseDto> createBook(@RequestBody @Valid CreateBookRequestDto dto){

        return ResponseEntity.ok(bookService.create(dto));

    }
    @CrossOrigin("*")
    @PutMapping("/update")
    public ResponseEntity<UpdateBookResponseDto> updateBook(@RequestBody @Valid UpdateBookRequestDto dto){

        return ResponseEntity.ok(bookService.update(dto));

    }
}
