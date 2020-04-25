package com.jjunpro.shop.mapper;

import com.jjunpro.shop.model.Account;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    Optional<Account> findById(Long id);

    List<Account> findAll();

    Account findByUsername(String username);

    Optional<Account> findByEmail(String email);

    Long insertAccount(Account account);

    Long updateAccount(Account account);

    Optional<Account> findByEmailAndEnabled(String email, boolean enabled);
}