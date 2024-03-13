package com.uhpoo.ireon.global.security.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {
    private SecurityUtil(){}

    // SecurityContext에 유저 정보가 저장되는 시점 => jwtfilter의 doFilter에서 jwt 인증 후 저장
    public static String getCurrentMemberUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null) {
            throw new SecurityException("Security Context에 인증 정보가 없습니다.");
        }
        return authentication.getName();
    }
}
