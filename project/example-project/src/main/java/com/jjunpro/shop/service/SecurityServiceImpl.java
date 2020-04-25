package com.jjunpro.shop.service;

import com.jjunpro.shop.enums.UserRole;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.Spring;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;
    private final AccountServiceImpl    accountService;

    @Override
    public void autologin(String email, String password, UserRole userRole,
            HttpServletRequest request) {
        /* StringUtils Null 검사 */
        if (StringUtils.hasText(password)) {
            /* 서버에 접근하려는 유저의 정보를 찾아서 인증 객체를 생성합니다. */
            UserDetails userDetails = accountService.loadUserByUsername(email);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password,
                            userDetails.getAuthorities()
                    )
            );

            /* SecurityContextHolder 내부에 유저 정보 객체를 등록합니다. */
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
        } else {
            HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRole));

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    grantedAuthorities
            );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
        }

        request
                .getSession()
                .setAttribute(
                        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        SecurityContextHolder.getContext()
                );
    }
}
