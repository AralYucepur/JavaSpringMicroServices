package com.aral.controller;

import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SalesService salesService;

    @CrossOrigin("*")
    @PutMapping("/create")
    public ResponseEntity<FindProductByIdResponseDto> createSales(FindProductByIdRequestDto dto){

        return ResponseEntity.ok(salesService.createSales(dto));

    }


}
