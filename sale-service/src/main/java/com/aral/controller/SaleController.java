package com.aral.controller;

import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.request.FindUserProfileRequestDto;
import com.aral.dto.request.SaleRequestDto;
import com.aral.dto.response.SaleResponseDto;
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
    public ResponseEntity<SaleResponseDto> createSales(FindUserProfileRequestDto uDto, FindProductByIdRequestDto dto, SaleRequestDto sDto){

        return ResponseEntity.ok(salesService.createSales(uDto,dto,sDto));

    }


}
