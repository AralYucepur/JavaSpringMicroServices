package com.aral.service;

import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.mapper.IProductMapper;
import com.aral.repository.IProductRepository;
import com.aral.repository.entity.Product;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService extends ServiceManager<Product, Long> {

    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public FindProductByIdResponseDto findProduct(FindProductByIdRequestDto dto){
        Optional<Product> existingOptionalProduct = repository.findOptionalByProductId(dto.getProductId());
        if(existingOptionalProduct.isEmpty()){
            throw new NullPointerException();}
        else{
//            CHECK!!!
            FindProductByIdResponseDto result = IProductMapper.INSTANCE.fromProduct(existingOptionalProduct.get());


            return result;

        }
    }
}
