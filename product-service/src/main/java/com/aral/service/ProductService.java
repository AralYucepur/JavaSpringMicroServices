package com.aral.service;

import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.request.PurchaseRequestDto;
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
            return IProductMapper.INSTANCE.fromProduct(existingOptionalProduct.get());
        }
    }
    public String purchase(PurchaseRequestDto dto){
        Product purchaseProduct = repository.findOptionalByProductId(dto.getProductId()).orElseThrow();
        purchaseProduct.setStockQuantity(purchaseProduct.getStockQuantity()-dto.getSaleQuantity());
        save(purchaseProduct);
        return "Başarılı";

    }

}
