package edu.hanoi.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class HomeController {
    private final static Logger logger = Logger.getLogger(String.valueOf(HomeController.class));

    @RequestMapping(value = "/home")
    @ResponseBody
    String home() {
        return "Hello World";
    }


    @RequestMapping(value = "/")
    ModelAndView home1() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Hello Java Clazz");
        return mv;
    }

    @RequestMapping(value = "/login")
    ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");
        if (error != null) mv.addObject("error", "error username or password");

        return mv;
    }

    @RequestMapping(value = "/nguoi-dung")
    ModelAndView forUser() {
        ModelAndView mv = new ModelAndView("index");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("message", "Hello User " + auth.getName());
        return mv;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}
