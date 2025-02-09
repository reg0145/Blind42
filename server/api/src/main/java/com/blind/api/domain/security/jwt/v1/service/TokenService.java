package com.blind.api.domain.security.jwt.v1.service;

import com.blind.api.domain.security.jwt.v1.domain.Token;
import com.blind.api.domain.user.v2.domain.User;

public interface TokenService {
    User findUserByAccessToken(String accessToken);
    Token findByAccessToken(String accessToken);
}
