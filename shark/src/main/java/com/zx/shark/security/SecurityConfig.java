package com.zx.shark.security;


import com.zx.shark.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

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
    UserService userService;
    @Autowired
    LogoutSuccess logoutSuccess;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        super.configure(auth);
//        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                System.out.println("密钥:   "+s);
//                return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
//            }
//        });
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/**","/css/**","/images/**","/img/**","/blog/**","/fonts/**").permitAll()
                .mvcMatchers("/index/**").permitAll()
                .mvcMatchers("/yummy/**","/book/**","/user/**").hasAnyAuthority("ROLE_ADMIN")
                .mvcMatchers("/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index/login")
                .loginProcessingUrl("/index/login")
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
