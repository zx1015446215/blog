package com.zx.shark.log;

import com.zx.shark.model.Log;
import com.zx.shark.model.MyException;
import com.zx.shark.repository.LogRepository;
import com.zx.shark.repository.MyExceptionRepository;
import com.zx.shark.utils.JSONResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//@Aspect
//@Component
public class ControllerLog {
    private final static Logger logger = LoggerFactory.getLogger(ControllerLog.class);
    private Log log = new Log();
    @Autowired
    LogRepository logRepository;
    @Autowired
    MyExceptionRepository myExceptionRepository;


    @Pointcut("execution(public * com.zx.shark.controller..*.*(..))")
    public void cut(){
    }

    @Before("cut()")
    public void doBefore(JoinPoint joinPoint){
        log.setDate(new Date(System.currentTimeMillis()));
       ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       HttpServletRequest request = requestAttributes.getRequest();
        //用户的凭证
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.setPrincipal(principal.toString());
        //url
        log.setUrl(request.getRequestURI());
        //method
        log.setMethod(request.getMethod());
        //ip
        log.setIp(request.getRemoteAddr());
        //类方法
        log.setClass_method(joinPoint.getSignature().getDeclaringTypeName()+'.'+ joinPoint.getSignature().getName());
        //参数
        log.setArgs(joinPoint.getArgs());
    }
    @After("cut()")//无论Controller中调用方法以何种方式结束，都会执行
        public void doAfter(){

    }

    @AfterReturning(returning = "obj",pointcut = "cut()")//在调用上面 @Pointcut标注的方法后执行。用于获取返回值
    public void doAfterReturning(Object obj){
        log.setResponse(obj.toString());
        logRepository.insert(log);
    }
    @AfterThrowing(pointcut = "cut()", throwing = "e")//cut()
        public JSONResult handleThrowing(JoinPoint joinPoint, Exception e) {//controller类抛出的异常在这边捕获
        logger.info("异常抛出:");
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        List<String > lists = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            lists.add(String.valueOf(args[i]));
        }
        MyException myException = new MyException(new Date(System.currentTimeMillis()),e.getMessage(),className,methodName,lists);
        //存入mongodb
        myExceptionRepository.insert(myException);
        return JSONResult.errorMsg("错误");
    }

}
