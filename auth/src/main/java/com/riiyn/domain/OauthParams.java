package com.riiyn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: riiyn
 * @Date: 2021/3/23 11:48
 * 授权参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OauthParams {
    private String grantType = "password"; // 授权模式
    private String clientId = "client"; // Oauth2客户端ID
    private String clientSecret = "123456"; // 客户端秘钥
    private String refreshToken; // 刷新token
    private String username; // 登录用户名
    private String password; // 登录密码
}
