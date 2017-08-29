package com.softserve.edu.resources.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.resources.service.UserService;

@Controller
public class WebController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        userService.save();
        return "index";
    }

  @RequestMapping(value = "/{page}.html", method = RequestMethod.GET)
  public String redirect(@PathVariable("page") String page) {
      return "redirect:/static/html/" + page + ".html";
  }

//    @RequestMapping(value = "/final", method = RequestMethod.GET)
//    public String redirect() {
//        return "redirect:/static/html/final.htm";
//    }

}
