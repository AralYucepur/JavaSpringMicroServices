package com.aral.service;

import com.aral.dto.request.DoLoginRequestDto;
import com.aral.dto.request.RegisterRequestDto;
import com.aral.dto.response.DoLoginResponseDto;
import com.aral.dto.response.RegisterResponseDto;
import com.aral.exception.AuthServiceException;
import com.aral.exception.ErrorType;
import com.aral.mapper.IAuthMapper;
import com.aral.rabbitmq.model.CreateUser;
import com.aral.rabbitmq.producer.CreateUserProducer;
import com.aral.repository.IAuthRepository;
import com.aral.repository.entity.Auth;
import com.aral.utility.JwtTokenManager;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateUserProducer createUserProducer;



    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager, CreateUserProducer createUserProducer){
        super(repository);
        this.repository=repository;
        this.jwtTokenManager = jwtTokenManager;
        this.createUserProducer = createUserProducer;
    }

    public RegisterResponseDto save(RegisterRequestDto dto){

        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(ErrorType.REGISTER_UNMATCHED_REPASSWORD_ERROR);

        if(repository.findOptionalByUsername(dto.getUsername()).isPresent())
            throw new AuthServiceException(ErrorType.REGISTER_USED_USERNAME_ERROR);

        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterRequestDto(dto));
        createUserProducer.convertAndSendMessageCreateUser(CreateUser.builder()
                .authid(auth.getId())
                .username(auth.getUsername())
                .email(auth.getEmail()).build());

        RegisterResponseDto result = IAuthMapper.INSTANCE.fromAuth(auth);
        result.setRegisterstate(100);
        result.setContent(auth.getEmail()+" ile başarılı bir şekilde kayıt oldunuz.");
        return  result;

    }

    public DoLoginResponseDto doLogin(DoLoginRequestDto dto){
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if(auth.isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty())
            throw new AuthServiceException(ErrorType.JWT_TOKEN_CREATE_ERROR);

        return DoLoginResponseDto.builder()
                .token(token.get())
                .build();
    }

}
