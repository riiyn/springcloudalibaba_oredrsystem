package com.riiyn.filter;

import org.springframework.stereotype.Component;

/**
 * @Author: riiyn
 * @Date: 2021/3/23 13:00
 */
@Component
public interface AuthConstans {
    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";
    
    /**
     * JWT令牌前缀
     */
    String AUTHORIZATION_PREFIX = "riiyn ";
    
    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";
    
    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";
    
    /**
     * JWT失效时间
     */
    String JWT_EXP = "exp";
    
    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";
    
    /**
     * 密码加密方式
     */
    String BCRYPT = "{bcrypt}";
    
    String USER_ID_KEY = "user_id";
    
    String USER_NAME_KEY = "username";
    
    String CLIENT_ID_KEY = "client_id";
    
    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";
    
    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";
    
    String GRANT_TYPE_KEY = "grant_type";
    
    String REFRESH_TOKEN = "refresh_token";
}
