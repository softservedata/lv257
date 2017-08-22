package com.softserve.edu.resources.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
    
    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
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
