package com.jjunpro.shop.service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.util.Collections;
import oauth.kakao.KakaoAuthorizationCodeTokenRequest;
import oauth.kakao.KakaoBrowserClientRequestUrl;
import oauth.kakao.KakaoTemplate;
import oauth.kakao.KakaoUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoServiceImpl implements KakaoService {

    @Value("${default.kakao.uri}")
    private String uri;

    @Value("${spring.social.kakao.app-id}")
    private String appId;

    @Value("${spring.social.kakao.app-secret}")
    private String appSecret;

    @Override
    public String login() {
        return new KakaoBrowserClientRequestUrl(this.appId, this.uri, null)
                .setResponseTypes(Collections.singleton("code"))
                .build();
    }

    @Override
    public KakaoUser getUserProfile(String code) throws IOException {
        HttpTransport  httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory   = new JacksonFactory();

        TokenResponse tokenResponse =
                new KakaoAuthorizationCodeTokenRequest(
                        httpTransport,
                        jsonFactory,
                        this.appId,
                        this.appSecret,
                        code,
                        this.uri
                ).execute();

        KakaoTemplate kakaoTemplate = new KakaoTemplate(tokenResponse.getAccessToken());

        return kakaoTemplate.fetchObject(KakaoUser.class);
    }
}
