package com.aral.service;

import com.aral.config.security.JwtUserDetails;
import com.aral.dto.request.DoLoginRequestDto;
import com.aral.dto.request.RegisterRequestDto;
import com.aral.dto.response.DoLoginResponseDto;
import com.aral.dto.response.RegisterResponseDto;
import com.aral.exception.AuthServiceException;
import com.aral.exception.ErrorType;
import com.aral.mapper.IAuthMapper;
import com.aral.rabbitmq.model.CreateUser;
import com.aral.rabbitmq.model.MailModel;
import com.aral.rabbitmq.producer.CreateUserProducer;
import com.aral.rabbitmq.producer.MailProducer;
import com.aral.repository.IAuthRepository;
import com.aral.repository.IRoleRepository;
import com.aral.repository.entity.Auth;
import com.aral.repository.entity.Role;
import com.aral.utility.ActivationCode;
import com.aral.utility.JwtTokenManager;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final IRoleRepository roleRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateUserProducer createUserProducer;
    private final MailProducer mailProducer;
    private final JwtUserDetails jwtUserDetails;


    public AuthService(IAuthRepository repository, IRoleRepository roleRepository, JwtTokenManager jwtTokenManager, CreateUserProducer createUserProducer, MailProducer mailProducer, JwtUserDetails jwtUserDetails) {
        super(repository);
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.createUserProducer = createUserProducer;
        this.mailProducer = mailProducer;
        this.jwtUserDetails = jwtUserDetails;
    }

    public RegisterResponseDto save(RegisterRequestDto dto) {

        if (!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(ErrorType.REGISTER_UNMATCHED_REPASSWORD_ERROR);

        if (repository.findOptionalByUsername(dto.getUsername()).isPresent())
            throw new AuthServiceException(ErrorType.REGISTER_USED_USERNAME_ERROR);

        Auth auth = IAuthMapper.INSTANCE.fromRegisterRequestDto(dto);
        auth.setActivationCode(ActivationCode.activationCodeGenerator());
        save(auth);

        createUserProducer.convertAndSendMessageCreateUser(CreateUser.builder()
                .authid(auth.getId())
                .username(auth.getUsername())
                .email(auth.getEmail()).build());

        MailModel mailModel = IAuthMapper.INSTANCE.toMAilModelFromAuth(auth);
        mailProducer.convertAndSendMessageMailModel(mailModel);


        RegisterResponseDto result = IAuthMapper.INSTANCE.fromAuth(auth);
        result.setRegisterstate(100);
        result.setContent(auth.getEmail() + " ile başarılı bir şekilde kayıt oldunuz.");
        return result;

    }

    public DoLoginResponseDto doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        List<Role> role = roleRepository.findByAuthid(auth.get().getId());
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId(), role.stream().map(Role::getRole).collect(Collectors.toList()));
        if (token.isEmpty())
            throw new AuthServiceException(ErrorType.JWT_TOKEN_CREATE_ERROR);

        return DoLoginResponseDto.builder()
                .token(token.get())
                .build();
    }

//    public DoLoginResponseDto authan(DoLoginRequestDto dto) {
//
//
//        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
//        if (auth.isEmpty())
//            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
//        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
//        if (token.isEmpty())
//            throw new AuthServiceException(ErrorType.JWT_TOKEN_CREATE_ERROR);
//        Optional<Long> authid = jwtTokenManager.getByIdFromToken(token.orElse("").toString());
//        if (authid.isPresent()) {
//            UserDetails userDetails = jwtUserDetails.getUserByAuthId(authid.get());
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                    userDetails, null, userDetails.getAuthorities()
//            );
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }else {
//            throw new AuthServiceException(ErrorType.JWT_INVALID_TOKEN);
//        }
//        return DoLoginResponseDto.builder()
//                .token(token.get())
//                .build();
//    }
}
