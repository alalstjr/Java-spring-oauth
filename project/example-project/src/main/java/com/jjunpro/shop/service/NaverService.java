package com.jjunpro.shop.service;

import java.io.IOException;
import oauth.naver.NaverUser;

public interface NaverService {

    String login();

    NaverUser getUserProfile(String code) throws IOException;
}
