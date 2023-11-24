package com.aral.service;

import com.aral.dto.request.*;
import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.dto.response.FindUserProfileResponseDto;
import com.aral.dto.response.SaleResponseDto;
import com.aral.manager.IProductManager;
import com.aral.manager.IUserProfileManager;
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

    private final IUserProfileManager userProfileManager;

    public SalesService(ISales repository, IProductManager productManager, IUserProfileManager userProfileManager) {
        super(repository);
        this.repository = repository;
        this.productManager = productManager;
        this.userProfileManager = userProfileManager;
    }

    //Fix Dtos !!!
    @Transactional
    public SaleResponseDto createSales(FindUserProfileRequestDto uDto, FindProductByIdRequestDto pDto, SaleRequestDto sDto) {
        ResponseEntity<FindProductByIdResponseDto> responseProduct = productManager.findProduct(pDto);
        if (responseProduct.getBody() == null) {
            throw new IllegalStateException("Ürün bilgisi alınamadı!");
        }
        ResponseEntity<FindUserProfileResponseDto> responseUserProfile = userProfileManager.findUserProfile(uDto);
        if (responseUserProfile.getBody() == null) {
            throw new IllegalStateException("Kullanıcı profil bilgisi alınamadı!");
        }
        int stockControl = sDto.getSaleQuantity();
        double balanceControl = responseProduct.getBody().getProductPrice()*stockControl;
        if (responseProduct.getBody().getStockQuantity()==null || stockControl > responseProduct.getBody().getStockQuantity() ) {
            throw new IllegalStateException("Stok miktarı yetersiz!");
        }
        if(responseUserProfile.getBody().getBalance()==null || balanceControl > responseUserProfile.getBody().getBalance()){
            throw new IllegalStateException("Bakiye miktarınız yetersiz!");
        }

        PurchaseProductRequestDto purchaseDto = PurchaseProductRequestDto.builder()
                .productId(pDto.getProductId())
                .saleQuantity(stockControl)
                .build();
        productManager.purchaseProduct(purchaseDto);

        PurchaseUserBalanceRequestDto purchaseUserBalanceRequestDto = PurchaseUserBalanceRequestDto.builder()
                .id(uDto.getAuthid())
                .balance(balanceControl)
                .build();
        userProfileManager.purchaseUserProfile(purchaseUserBalanceRequestDto);

        Sales sale = ISalesMapper.INSTANCE.fromProductReturnDto(responseProduct.getBody());
        sale.setSaleQuantity(sDto.getSaleQuantity());
        sale.setPrice(responseProduct.getBody().getProductPrice() * sDto.getSaleQuantity());
        save(sale);

        SaleResponseDto saleResponseDto = ISalesMapper.INSTANCE.fromSales(sale);
        saleResponseDto.setProductPrice(responseProduct.getBody().getProductPrice());
        saleResponseDto.setProductName(responseProduct.getBody().getProductName());

        return saleResponseDto;
    }

}

