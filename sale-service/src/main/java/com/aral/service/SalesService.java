package com.aral.service;

import com.aral.dto.request.FindProductByIdRequestDto;
import com.aral.dto.request.FindUserProfileRequestDto;
import com.aral.dto.request.PurchaseRequestDto;
import com.aral.dto.request.SaleRequestDto;
import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.dto.response.SaleResponseDto;
import com.aral.manager.IProductManager;
import com.aral.mapper.ISalesMapper;
import com.aral.repository.ISales;
import com.aral.repository.entity.Sales;
import com.aral.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalesService extends ServiceManager<Sales, Long> {

    private final ISales repository;
    private final IProductManager productManager;

    public SalesService(ISales repository, IProductManager productManager) {
        super(repository);
        this.repository = repository;
        this.productManager = productManager;
    }


    @Transactional
    public SaleResponseDto createSales(FindUserProfileRequestDto uDto,FindProductByIdRequestDto pDto, SaleRequestDto sDto){
        ResponseEntity<FindProductByIdResponseDto> response = productManager.findProduct(pDto);
        int stockControl = sDto.getSaleQuantity();
        double balanceControl;
//      Reminder!!! >>>  Check null point
        if(stockControl > response.getBody().getStockQuantity()){
            throw new IllegalStateException("Stok miktarı yetersiz!");
        }
        else {
            PurchaseRequestDto purchaseDto = new PurchaseRequestDto();
            purchaseDto.setProductId(purchaseDto.getProductId());
            purchaseDto.setSaleQuantity(stockControl);
        productManager.purchase(purchaseDto);
        Sales sale = ISalesMapper.INSTANCE.fromProductReturnDto(response.getBody());
        sale.setSaleQuantity(sDto.getSaleQuantity());
        sale.setPrice(response.getBody().getProductPrice()*sDto.getSaleQuantity());
        save(sale);
        SaleResponseDto saleResponseDto = ISalesMapper.INSTANCE.fromSales(sale);
        saleResponseDto.setProductPrice(response.getBody().getProductPrice());
        saleResponseDto.setProductName(response.getBody().getProductName());
        //Unfinished(making with user match)

        return saleResponseDto;
        }

    }
}
