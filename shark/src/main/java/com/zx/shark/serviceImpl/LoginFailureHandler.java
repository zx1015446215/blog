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
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("Message:  "+exception.getMessage());
        if(!exception.getMessage().equals("username is wrong")){
            redirectStrategy.sendRedirect(request,response,"/sendFailurePass");
        }else{

            redirectStrategy.sendRedirect(request,response,"/sendFailureUsen");
        }
    }

}
