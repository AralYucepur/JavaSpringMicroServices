package com.aral.manager;

import com.aral.dto.request.FindUserProfileRequestDto;
import com.aral.dto.request.PurchaseUserBalanceRequestDto;
import com.aral.dto.response.FindUserProfileResponseDto;
import com.aral.dto.response.PurchaseResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "User-Profile-Manager", url = "http://localhost:9091/v1/dev/userprofile", decode404 = true)
public interface IUserProfileManager {
    @GetMapping("/find")
    @CrossOrigin("*")
    ResponseEntity<FindUserProfileResponseDto> findUserProfile(FindUserProfileRequestDto dto);
    @PutMapping("/purchase")
    @CrossOrigin("*")
    ResponseEntity<PurchaseResponseDto> purchaseUserProfile(PurchaseUserBalanceRequestDto dto);


}
