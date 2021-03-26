//package com.riiyn.security;
//
//import com.riiyn.filter.AuthConstans;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.ReactiveAuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.server.authorization.AuthorizationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.StringUtils;
//import reactor.core.publisher.Mono;
//
//import java.util.*;
//
///**
// * @Author: riiyn
// * @Date: 2021/3/23 9:21
// * 鉴权管理器
// */
//@Component
//@AllArgsConstructor
//@Slf4j
//public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//
//    @Override
//    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
//        final ServerHttpRequest request = authorizationContext.getExchange().getRequest();
//        final String path = request.getURI().getPath();
//        log.debug("请求，path={}", path);
//        final AntPathMatcher pathMatcher = new AntPathMatcher();
//
//        // 1. 对应跨域的预检请求直接放行
//        if (request.getMethod() == HttpMethod.OPTIONS){
//            return Mono.just(new AuthorizationDecision(true));
//        }
//
//        // 2. token为空拒绝访问
//        final String token = request.getHeaders().getFirst(AuthConstans.AUTHORIZATION_KEY);
//        if (StringUtils.isEmpty(token)){
//            log.debug("请求token为空拒绝访问，path={}", path);
//            return Mono.just(new AuthorizationDecision(false));
//        }
//
//        // 3.缓存取资源权限角色关系列表 todo 待实现
////        Map<Object, Object> resourceRolesMap = new HashMap<>();
////        resourceRolesMap.put("/account/login/**", "admin");
//////                redisTemplate.opsForHash().entries("");
////        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
////
////        // 4.请求路径匹配到的资源需要的角色权限集合authorities
////        List<String> authorities = new ArrayList<>();
////        authorities.add("admin");
////        while (iterator.hasNext()) {
////            String pattern = (String) iterator.next();
////            if (pathMatcher.match(pattern, path)) {
////                authorities.add((String) resourceRolesMap.get(pattern));
////            }
////        }
////        log.info("require authorities:{}", authorities);
//
//        Mono<AuthorizationDecision> authorizationDecisionMono = mono
//                .filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
////                .any(roleId -> {
////                    // TODO 待确定
////                    // 5. roleId是请求用户的角色(格式:ROLE_{roleId})，authorities是请求资源所需要角色的集合
////                    log.info("访问路径：{}", path);
////                    log.info("用户角色roleId：{}", roleId);
////                    log.info("资源需要权限authorities：{}", authorities);
//////                    return authorities.contains(roleId);
////                    return true;
////                })
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
//
//        return authorizationDecisionMono;
//    }
//}
