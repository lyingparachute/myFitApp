package com.infoshare.myfitwebapp.security.oauth;

import com.infoshare.myfitwebapp.controller.DishController;
import com.infoshare.myfitwebapp.entity.UserLogin;
import com.infoshare.myfitwebapp.enums.AuthenticationProvider;
import com.infoshare.myfitwebapp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger LOGGER = LogManager.getLogger(DishController.class);
    private final UserService userService;

    public OAuth2LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        // TODO - email on registration user!
//        String username = oAuth2User.getUsername();

        String username = "test";
        UserLogin user = userService.findByUsername(username);

        if (user == null) {
            userService.createNewUserAfterOAuthLoginSuccess(email, AuthenticationProvider.GOOGLE);
        } else {

            return;
        }
        LOGGER.info("Customer's email: {}", email);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
