package com.jjunpro.shop.service;

import com.jjunpro.shop.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    Optional<Account> findById(Long id);

    List<Account> findAll();

    Account findByUsername(String username);

    Optional<Account> findByEmail(String email);

    Account insertAccount(Account account);

    Long updateAccount(Account account);

    UserDetails loadUserByUsername(String email);
}
