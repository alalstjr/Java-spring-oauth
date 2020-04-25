package com.jjunpro.shop.dto;

import lombok.Data;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;

@Data
public class UserFormDTO {

    private Long id;
    private String email;
    private String username;

    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private String signInProvider;
    private String providerUserId;

    public UserFormDTO(Connection<?> connection) {
        UserProfile socialUserProfile = connection.fetchUserProfile();
        this.id = null;
        this.email = socialUserProfile.getEmail();
        this.username = socialUserProfile.getUsername();
        this.firstName = socialUserProfile.getFirstName();
        this.lastName = socialUserProfile.getLastName();

        ConnectionKey key = connection.getKey();
        this.signInProvider = key.getProviderId();
        this.providerUserId = key.getProviderUserId();
    }
}
