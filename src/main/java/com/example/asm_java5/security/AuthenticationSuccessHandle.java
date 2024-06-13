package com.example.asm_java5.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
public class AuthenticationSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("authen {}", authentication.isAuthenticated());
        log.info("context {}", SecurityContextHolder.getContext());

        boolean isAdmin =
                authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        log.info("response {}", isAdmin);
        if (isAdmin) {
            setDefaultTargetUrl("/ban-hang");
        } else {
            setDefaultTargetUrl("/mau-sac/hien-thi");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
