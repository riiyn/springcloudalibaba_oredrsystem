package com.riiyn.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * rsa公钥获取接口
 * @Author: riiyn
 * @Date: 2021/3/22 23:19
 */
@RestController
@RequestMapping("/publickey")
public class PublicKeyController {
    @Autowired
    private KeyPair keyPair;
    
    @GetMapping("/getPublicKey")
    public Map<String, Object> getPublicKey(){
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(rsaPublicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
