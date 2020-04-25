package com.jjunpro.shop.service;

import com.jjunpro.shop.enums.UserRole;
import javax.servlet.http.HttpServletRequest;

public interface SecurityService {

    void autologin(String email, String password, UserRole userRole, HttpServletRequest request);
}
