package com.zx.shark.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private static final Logger logger= LoggerFactory.getLogger(LoginSuccessHandler.class);
    RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        if(exception.getMessage().equals("password is wrong")){

            redirectStrategy.sendRedirect(request,response,"/sendFailurePass");
        }else{

            redirectStrategy.sendRedirect(request,response,"/sendFailureUsen");
        }
    }

}
