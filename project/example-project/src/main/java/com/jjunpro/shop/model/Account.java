package com.jjunpro.shop.model;

import com.jjunpro.shop.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class Account {

    private Long     id;
    private String   username;
    private String   email;
    private String   firstName;
    private String   lastName;
    private String   password;
    private boolean  enabled;
    private UserRole userRole;
    private String   ageRange;
    private String   birthday;
    private String   gender;

    public Account() {
    }

    @Builder
    public Account(Long id, String username, String email, String firstName, String lastName,
            String password, boolean enabled, UserRole userRole, String ageRange,
            String birthday, String gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
        this.ageRange = ageRange;
        this.birthday = birthday;
        this.gender = gender;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}