package com.jjunpro.shop.service;

import com.google.api.services.people.v1.model.Person;
import java.io.IOException;

public interface GoogleService {

    String login();

    Person getUserProfile(String code) throws IOException;

}
