package com.jjunpro.shop.service;

import org.springframework.social.facebook.api.User;

public interface FacebookService {

    String login();

    String getAccessToken(String code);

    User getUserProfile(String accessToken);
}
