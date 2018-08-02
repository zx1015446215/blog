package com.zx.shark.controller;

import com.zx.shark.model.User;
import com.zx.shark.model.User_Roles;
import com.zx.shark.service.impl.UserServiceImpl;
import com.zx.shark.utils.JSONResult;
import com.zx.shark.utils.SendEmail;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Digits;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@CrossOrigin(origins = "*")
public class ControllerOne {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RedisTemplate redisTemplate;
//    @Autowired   //mongodb
//    UserRepository userRepository;
    @RequestMapping("")
    public String main(){
        return  "index/index";
    }

    private static  final Logger logger= LoggerFactory.getLogger(ControllerOne.class);
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
            logger.info("注销---------------");
        }
        return "redirect:/yummy/login?logout";
    }

    @RequestMapping("/register")
    @ResponseBody
    public JSONResult register(HttpServletRequest request){
        //用户信息注册
        String username=request.getParameter("username");
        String password = DigestUtils.md5DigestAsHex(request.getParameter("password").getBytes());
        String email = request.getParameter("email");
        //获取随机用户id
        Long user_id=Long.valueOf(new SimpleDateFormat("ssSSS").format(new Date()).toString());
        User user=new User(user_id,username,password,email);
        System.out.println(email);
        //判断是用户注册还是管理员注册email
        String roleName=request.getParameter("role");
        Long roles_id=2L;  //2L代表用户注册，一般管理员是自己
        //用户信息和权限信息关联
        User_Roles user_roles=new User_Roles(user_id,roles_id);
        try {
            userServiceImpl.registerUser(user,user_roles);
        }catch (Exception e){
            logger.info("错误信息: "+e);
            return JSONResult.ok();
        }
        ValueOperations<String,User> operations=redisTemplate.opsForValue();
        operations.set(user.getUsername(),user,30, TimeUnit.SECONDS);
        logger.info("用户插入缓存 >> " +"id: "+ user.getId()+", username: "+user.getUsername()+",password: "+user.getPassword());
        logger.info("注册成功");
        return JSONResult.ok();
    }


    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    @ResponseBody
    public String sendCode(@RequestParam("email")String email) throws UnsupportedEncodingException, MessagingException {
        System.out.println("email:" +email);
        SendEmail sendEmail = new SendEmail("zx1015446215@163.com","zzzx19961026");
        sendEmail.setQqDefaultProperties();
        sendEmail.initMessage();
        Random random = new Random();
        String str = getUUID();
        String code = str.substring(0,4);
        sendEmail.setDefaultMessagePros("验证码",code,email,"zx博客");
        sendEmail.sendMessage();
        System.out.println("发送成功"+code);
        return code;
    }

    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
