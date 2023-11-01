package com.aral.service;

import com.aral.repository.IAuthRepository;
import com.aral.repository.entity.Auth;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;

    public AuthService(IAuthRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
