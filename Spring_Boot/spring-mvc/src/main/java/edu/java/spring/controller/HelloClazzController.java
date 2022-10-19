package edu.java.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/hello")
public class HelloClazzController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView prinMessage(@RequestParam(value = "data",required = false)String message) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("message",message);
        return mv;
    }
    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("../clazz");
        mv.addObject("name","Khai");
        return mv;
    }
    @RequestMapping(value = "site", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:http://moom.vn";
    }
    @RequestMapping(value = "data", method = RequestMethod.GET , produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String raw() {
        return "xin chao moi nguoi";
    }

}