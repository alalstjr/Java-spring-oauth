package com.jjunpro.shop.controller;

import com.jjunpro.shop.enums.UserRole;
import com.jjunpro.shop.model.Account;
import com.jjunpro.shop.service.AccountServiceImpl;
import com.jjunpro.shop.service.FacebookServiceImpl;
import com.jjunpro.shop.service.SecurityServiceImpl;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/facebook")
public class FacebookController {

    private final FacebookServiceImpl facebookService;
    private final AccountServiceImpl  accountService;
    private final SecurityServiceImpl securityService;

    @GetMapping("/login")
    public RedirectView login() {
        RedirectView redirectView = new RedirectView();
        String       url          = facebookService.login();

        redirectView.setUrl(url);

        return redirectView;
    }

    @GetMapping("")
    public String facebook(@RequestParam("code") String code) {
        String accessToken = facebookService.getAccessToken(code);

        return "redirect:/facebook/profileData/" + accessToken;
    }

    @GetMapping("/profileData/{accessToken:.+}")
    public String profileData(
            @PathVariable String accessToken,
            Model model,
            HttpServletRequest request
    ) {
        /* 페이스북에서 받아온 사용자의 정보 */
        User   userProfile = facebookService.getUserProfile(accessToken);
        String birthday    = null;
        String ageRange    = null;
        String gender      = null;

        /* 사용자의 생일정보가 존재하는 경우 */
        if (userProfile.getBirthday() != null) {
            birthday = userProfile.getBirthday();
        }
        /* 사용자의 나이정보가 존재하는 경우 */
        if (userProfile.getAgeRange() != null) {
            ageRange = userProfile.getAgeRange().toString();
        }
        /* 사용자의 성별정보가 존재하는 경우 */
        if (userProfile.getGender() != null) {
            gender = userProfile.getGender();
        }

        /* DB 내부에 사용자가 이미 가입되어 있는지 체크합니다. */
        Optional<Account> accountDB = accountService.findByEmail(userProfile.getEmail());

        UserRole userrole;

        if (accountDB.isPresent()) {
            accountDB.get().setEmail(userProfile.getEmail());
            accountDB.get().setFirstName(userProfile.getFirstName());
            accountDB.get().setLastName(userProfile.getLastName());
            accountDB.get().setBirthday(birthday);
            accountDB.get().setAgeRange(ageRange);
            accountDB.get().setGender(gender);
            userrole = accountDB.get().getUserRole();

            accountService.updateAccount(accountDB.get());

            model.addAttribute("user", accountDB.get());
        } else {
            Account account = Account.builder()
                    .email(userProfile.getEmail())
                    .firstName(userProfile.getFirstName())
                    .lastName(userProfile.getLastName())
                    .enabled(true)
                    .userRole(UserRole.USER)
                    .birthday(birthday)
                    .ageRange(ageRange)
                    .gender(gender)
                    .build();
            userrole = account.getUserRole();

            accountService.insertAccount(account);

            model.addAttribute("user", account);
        }

        securityService.autologin(
                userProfile.getEmail(),
                null,
                userrole,
                request
        );

        return "account/userProfile";
    }
}
