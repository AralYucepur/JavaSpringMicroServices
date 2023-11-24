package com.aral.service;

import com.aral.dto.request.CreateProfileRequestDto;
import com.aral.dto.request.FindUserProfileRequestDto;
import com.aral.dto.request.PurchaseUserBalanceRequestDto;
import com.aral.dto.response.FindUserProfileResponseDto;
import com.aral.dto.response.PurchaseResponseDto;
import com.aral.exception.AuthServiceException;
import com.aral.exception.ErrorType;
import com.aral.mapper.IUserProfileMapper;
import com.aral.repository.IUserProfileRepository;
import com.aral.repository.entity.UserProfile;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {

    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Boolean createProfile(CreateProfileRequestDto dto) {

        repository.save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
        return true;

    }

    public PurchaseResponseDto purchase(PurchaseUserBalanceRequestDto dto) {
        //Will fix error type!!
        UserProfile userProfile = repository.findOptionalById(dto.getId()).orElseThrow(() -> new AuthServiceException(ErrorType.LOGIN_ERROR));
        double newBalance = userProfile.getBalance() - dto.getBalance();
        userProfile.setBalance(newBalance);
        save(userProfile);
        return PurchaseResponseDto.builder().result("Satın alma başarılı").build();
    }

    public FindUserProfileResponseDto findUserProfile(FindUserProfileRequestDto dto) {
        //Will fix error type!!
        UserProfile userProfile = repository.findOptionalById(dto.getAuthid()).orElseThrow(() -> new AuthServiceException(ErrorType.LOGIN_ERROR));
        return IUserProfileMapper.INSTANCE.findUserFromId(userProfile);
    }

    public String addBalance(Long id, Double balance){
        UserProfile userProfile = repository.findOptionalById(id).orElseThrow();
        userProfile.setBalance(userProfile.getBalance()+balance);

        return "Bakiye yükleme başarılı";
    }


}
