package com.zx.shark.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
    RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSessionRequestCache httpSessionRequestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = httpSessionRequestCache.getRequest(request, response);
        logger.info("登陆成功");
        redirectStrategy.sendRedirect(request,response,"/sendSuccessMessage");
    }
}