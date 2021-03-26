package com.riiyn.filter;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.riiyn.util.WebUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: riiyn
 * @Date: 2021/3/23 12:51
 * 全局过滤器
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();
    
        // 没有token则放行
        String token = request.getHeaders().getFirst(AuthConstans.AUTHORIZATION_KEY);
        if (StringUtils.isEmpty(token) || !token.startsWith(AuthConstans.AUTHORIZATION_PREFIX)){
            return chain.filter(exchange);
        }
        
        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在拦截响应token失效
        token = token.replace(AuthConstans.AUTHORIZATION_PREFIX, Strings.EMPTY);
        final JWSObject jwsObject = JWSObject.parse(token);
        final String payload = jwsObject.getPayload().toString();
        final JSONObject jsonObject = JSONObject.parseObject(payload);
        final String jti = jsonObject.getString(AuthConstans.JWT_JTI);
        final Boolean isBlack = redisTemplate.hasKey(AuthConstans.TOKEN_BLACKLIST_PREFIX + jti);
        if (isBlack){
            return WebUtils.writeFailedToResponse(response, "C403");
        }
        // 存在token且不是黑名单，request写入JWT的载体信息
        request = exchange.getRequest().mutate()
                .header(AuthConstans.JWT_PAYLOAD_KEY, payload).build();
        exchange = exchange.mutate().request(request).build();
        
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
}
