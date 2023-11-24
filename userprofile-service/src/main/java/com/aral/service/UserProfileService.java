package com.aral.service;

import com.aral.dto.request.CreateProfileRequestDto;
import com.aral.dto.request.FindUserProfileRequestDto;
import com.aral.dto.request.PurchaseUserBalanceRequestDto;
import com.aral.dto.response.FindUserProfileResponseDto;
import com.aral.dto.response.PurchaseResponseDto;
import com.aral.mapper.IUserProfileMapper;
import com.aral.repository.IUserProfileRepository;
import com.aral.repository.entity.UserProfile;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        UserProfile userProfile = repository.findOptionalByAuthid(dto.getId()).orElseThrow();
        double newBalance = userProfile.getBalance() - dto.getBalance();
        userProfile.setBalance(newBalance);
        save(userProfile);
        return PurchaseResponseDto.builder().result("Satın alma başarılı").build();
    }

    public FindUserProfileResponseDto findUserProfile(FindUserProfileRequestDto dto) {
        System.out.println(dto);
        Optional<UserProfile> optionalUserProfile = repository.findOptionalByAuthid(dto.getAuthid());

        if (optionalUserProfile.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            return IUserProfileMapper.INSTANCE.findUserFromId(userProfile);
        } else {
            throw new IllegalStateException("Kullanıcı profili bulunamadı");
        }
    }

    public String addBalance(Long id, Double balance){
        UserProfile userProfile = repository.findOptionalByAuthid(id).orElseThrow();
        System.out.println(userProfile);
        userProfile.setBalance(userProfile.getBalance()+balance);
        save(userProfile);

        return "Bakiye yükleme başarılı";
    }


}
