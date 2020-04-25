package com.jjunpro.shop.service;

import java.io.IOException;
import oauth.kakao.KakaoUser;

public interface KakaoService {

    String login();

    KakaoUser getUserProfile(String code) throws IOException;
}
