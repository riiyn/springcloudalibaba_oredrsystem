//package com.riiyn.config;
//
//import com.riiyn.filter.AuthConstans;
//import com.riiyn.security.AuthorizationManager;
//import com.riiyn.util.WebUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
//import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
//import reactor.core.publisher.Mono;
//
///**
// * @Author: riiyn
// * @Date: 2021/3/23 9:44
// * 资源服务器配置
// */
//@Configuration
//@EnableWebFluxSecurity
//public class ResourceServerConfig {
//
//    @Autowired
//    private AuthorizationManager authorizationManager;
//    @Autowired
//    private WhiteListConfig whiteListConfig;
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
//        // 自定义处理JWT请求头过期或签名错误的结果
//        http.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());
//        http.authorizeExchange()
//                // todo
//                .pathMatchers("/account/login").permitAll()
//                .anyExchange().access(authorizationManager)
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler()) // 处理未授权
//                .authenticationEntryPoint(authenticationEntryPoint()) // 处理未认证
//                .and().csrf().disable();
//        return http.build();
//    }
//
//    /**
//     * 未授权
//     *
//     * @return mono
//     */
//    @Bean
//    ServerAccessDeniedHandler accessDeniedHandler() {
//        return (exchange, denied) -> {
//            Mono mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
//                    .flatMap(response -> WebUtils.writeFailedToResponse(response, "C403"));
//
//            return mono;
//        };
//    }
//
//    /**
//     * token无效或者已过期自定义响应
//     */
//    @Bean
//    ServerAuthenticationEntryPoint authenticationEntryPoint() {
//        return (exchange, e) -> {
//            Mono mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
//                    .flatMap(response -> WebUtils.writeFailedToResponse(response, "C400"));
//            return mono;
//        };
//    }
//
//    /**
//     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
//     * 需要把jwt的Claim中的authorities加入
//     * 方案：重新定义ReactiveAuthenticationManager权限管理器，默认转换器JwtGrantedAuthoritiesConverter
//     *
//     * @return c
//     */
//    @Bean
//    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        // todo
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstans.AUTHORIZATION_PREFIX);
//        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstans.AUTHORIZATION_KEY);
//
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//    }
//}
