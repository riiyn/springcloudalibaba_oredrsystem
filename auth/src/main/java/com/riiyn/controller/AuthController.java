package com.riiyn.controller;

import com.riiyn.domain.OauthParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: riiyn
 * @Date: 2021/3/23 11:42
 * 认证中心
 */
@RestController
@RequestMapping("/oauth")
@Slf4j
public class AuthController {
    
    @Autowired
    private TokenEndpoint tokenEndpoint;
    
    @PostMapping("/token")
    public OAuth2AccessToken accessToken(Principal principal, @RequestBody OauthParams oauthParams) throws HttpRequestMethodNotSupportedException {
        Map<String, String> map = new HashMap<>();
        if (null != oauthParams){
            map.put("grant_type", oauthParams.getGrantType());
            map.put("client_id", oauthParams.getClientId());
            map.put("client_secret", oauthParams.getClientSecret());
            map.put("refresh_token",oauthParams.getRefreshToken());
            map.put("username", oauthParams.getUsername());
            map.put("password", oauthParams.getPassword());
        }
        return (OAuth2AccessToken) tokenEndpoint.postAccessToken(principal, map).getBody();
    }
}
