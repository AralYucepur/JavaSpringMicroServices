package com.aral.manager;



import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.response.FindProductByIdResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "product-manager", url = "http://localhost:9092/product",decode404 = true)
public interface IProductManager {

    @CrossOrigin("*")
    @PostMapping("/find")
    ResponseEntity<FindProductByIdResponseDto> findProduct(@RequestBody @Valid FindProductByIdRequestDto dto);


}
