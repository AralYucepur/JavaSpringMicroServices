package com.aral.controller;

import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.request.PurchaseRequestDto;
import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @CrossOrigin("*")
    @PostMapping("/find")
    public ResponseEntity<FindProductByIdResponseDto> findProduct(@RequestBody @Valid FindProductByIdRequestDto dto){

        return ResponseEntity.ok(productService.findProduct(dto));

    }
    @PutMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody @Valid PurchaseRequestDto dto){
        return ResponseEntity.ok(productService.purchase(dto));
    }

}
