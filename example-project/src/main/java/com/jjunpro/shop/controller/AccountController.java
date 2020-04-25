package com.jjunpro.shop.controller;

import com.jjunpro.shop.model.Account;
import com.jjunpro.shop.service.AccountServiceImpl;
import com.jjunpro.shop.service.SecurityServiceImpl;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Transactional
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl  accountService;
    private final SecurityServiceImpl securityService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("account", new Account());
        return "main/main";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute Account account, Model model,
            HttpServletRequest request) {
        Account accountDB = accountService.insertAccount(account);
        securityService.autologin(
                accountDB.getEmail(),
                accountDB.getPassword(),
                accountDB.getUserRole(),
                request
        );

        model.addAttribute("user", accountDB);

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();

        for (GrantedAuthority authority : authorities) {
            System.out.println(authority);
        }

        System.out.println(name);

        return "account/userprofile";
    }
}
