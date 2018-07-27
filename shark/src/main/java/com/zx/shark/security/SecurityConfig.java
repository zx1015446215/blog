package com.zx.shark.security;


import com.zx.shark.serviceImpl.AuthenticationProvider;
import com.zx.shark.serviceImpl.LoginFailureHandler;
import com.zx.shark.serviceImpl.LoginSuccessHandler;
import com.zx.shark.serviceImpl.LogoutSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    LogoutSuccess logoutSuccess;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**","/css/**","/images/**","/img/**","/blog/**","/fonts/**").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/yummy/**","/book/**","/user/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccess)
                .permitAll()
                .and()
                .csrf().disable();
    }

}
