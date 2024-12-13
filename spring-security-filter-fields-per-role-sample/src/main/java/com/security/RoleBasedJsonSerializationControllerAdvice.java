package com.security;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RoleBasedJsonSerializationControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {


    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

        Class<?> bodyClass = bodyContainer.getValue().getClass();
        AuthorityToJsonViewMappings mappings = bodyClass.getAnnotation(AuthorityToJsonViewMappings.class);
        if (mappings == null) {
            return;
        }

        Map<String, Class<?>> authorityToViewMap = new HashMap<>();
        for (AuthorityToJsonViewMapping mapping : mappings.value()) {
            authorityToViewMap.put(mapping.authority(), mapping.view());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : authentication.getAuthorities()) {

            if (authorityToViewMap.get(authority.getAuthority()) != null) {
                bodyContainer.setSerializationView(authorityToViewMap.get(authority.getAuthority()));
                return;
            }
        }
    }
}
