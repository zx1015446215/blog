package com.zx.shark.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/role")
@CrossOrigin(origins = "*")
@Controller
public class RoleManagerController {

    @RequestMapping("/manager")
    public ModelAndView manager(){
        ModelAndView modelAndView = new ModelAndView("test");
        return modelAndView;
    }
}
