package com.aral.service;

import com.aral.dto.request.RegisterRequestDto;
import com.aral.dto.response.RegisterResponseDto;
import com.aral.mapper.IAuthMapper;
import com.aral.repository.IAuthRepository;
import com.aral.repository.entity.Auth;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;


    public AuthService(IAuthRepository repository){
        super(repository);
        this.repository=repository;
    }

    public RegisterResponseDto save(RegisterRequestDto dto){

        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new RuntimeException();

        if(repository.findOptionalByUsername(dto.getUsername()).isPresent())
            throw new RuntimeException();

        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterRequestDto(dto));

        RegisterResponseDto result = IAuthMapper.INSTANCE.fromAuth(auth);
        result.setRegisterstate(100);
        result.setContent(auth.getEmail()+" ile başarılı bir şekilde kayıt oldunuz.");
        return  result;

    }

}
