package com.blind.api.domain.security.jwt.v1.service;

import com.blind.api.domain.security.jwt.v1.domain.Token;
import com.blind.api.domain.security.jwt.v1.repository.TokenRepository;
import com.blind.api.domain.user.v2.domain.User;
import com.blind.api.global.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository repository;

    @Override
    public User findUserByAccessToken(String accessToken){
        return repository.findByAccessToken(accessToken)
                .orElseThrow(() -> new BusinessException("{token.notfound}"))
                .getUser();
    }
    public Token findByAccessToken(String accessToken){
        return repository.findByAccessToken(accessToken)
                .orElseThrow(() -> new BusinessException("{token.notfound}"));
    }
}
