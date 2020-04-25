package com.jjunpro.shop.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.Person;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application-social.properties")
public class GoogleServiceImpl implements GoogleService {

    @Value("${default.google.uri}")
    private String uri;

    @Value("${spring.social.google.app-id}")
    private String appId;

    @Value("${spring.social.google.app-secret}")
    private String appSecret;

    /**
     * scope 설정
     * <p>
     * https://developers.google.com/people/api/rest/v1/people/get
     */
    @Override
    public String login() {
        List<String> SCOPES = Arrays.asList(
                PeopleServiceScopes.USERINFO_EMAIL,
                PeopleServiceScopes.USERINFO_PROFILE,
                PeopleServiceScopes.USER_BIRTHDAY_READ
        );

        return new GoogleBrowserClientRequestUrl(this.appId, this.uri, SCOPES)
                .setResponseTypes(Collections.singleton("code"))
                .build();

    }

    @Override
    public Person getUserProfile(String code) throws IOException {
        HttpTransport  httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory   = new JacksonFactory();

        GoogleTokenResponse tokenResponse =
                new GoogleAuthorizationCodeTokenRequest(
                        httpTransport,
                        jsonFactory,
                        this.appId,
                        this.appSecret,
                        code,
                        this.uri
                ).execute();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setClientSecrets(this.appId, this.appSecret)
                .build()
                .setFromTokenResponse(tokenResponse);

        PeopleService peopleService =
                new PeopleService.Builder(httpTransport, jsonFactory, credential).build();

        /**
         * Get the person 값 가져오기
         * https://developers.google.com/people/v1/read-people
         * */
        return peopleService
                .people()
                .get("people/me")
                .setPersonFields("names,emailAddresses,ageRange,birthdays,genders")
                .execute();
    }
}
