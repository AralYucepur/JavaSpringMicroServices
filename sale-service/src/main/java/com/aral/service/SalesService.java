package com.aral.service;

import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.request.SaleRequestDto;
import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.manager.IProductManager;
import com.aral.mapper.ISalesMapper;
import com.aral.repository.ISales;
import com.aral.repository.entity.Sales;
import com.aral.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SalesService extends ServiceManager<Sales, Long> {

    private final ISales repository;
    private final IProductManager productManager;

    public SalesService(ISales repository, IProductManager productManager) {
        super(repository);
        this.repository = repository;
        this.productManager = productManager;
    }


    public FindProductByIdResponseDto createSales(FindProductByIdRequestDto dto, SaleRequestDto sDto){
        ResponseEntity<FindProductByIdResponseDto> response = productManager.findProduct(dto);
        int stockControl = sDto.getSaleQuantity();
//      Reminder!!! >>>  Check null point
        if(stockControl > response.getBody().getStockQuantity()){
            throw new IllegalStateException("Stok miktarı yetersiz!");
        }else {


        Sales sale = ISalesMapper.INSTANCE.fromProductReturnDto(response.getBody());
        sale.setSaleQuantity(sDto.getSaleQuantity());
        sale.setPrice(response.getBody().getProductPrice()*sDto.getSaleQuantity());
        save(sale);

        return response.getBody();}

    }
}
