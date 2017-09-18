package com.softserve.edu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("firstPageMessage", "This is redirect");
        return "redirect:/index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)//, params = {"firstPageMessage"})
    public ModelAndView homeHandler(
            @RequestParam(value = "firstPageMessage", required=false, defaultValue = "1001")
            String firstMessage,
            ModelAndView model) {
        // model.addAttribute("firstPageMessage", "This is home");
        if (model.getModel().get("firstPageMessage") != null) { // ERROR
                   model.addObject("firstPageMessage",
                model.getModel().get("firstPageMessage") + " *** This is HOME ***");
        } else {
            model.addObject("firstPageMessage", firstMessage + " This is HOME_123");
        }
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    // public String indexHandler(Model model) {
    // model.addAttribute("firstPageMessage", "index");
    public String indexHandler(Model model) {
        if (model.asMap().get("firstPageMessage") != null) { // ERROR?
            model.addAttribute("firstPageMessage",
                    model.asMap().get("firstPageMessage").toString() + " *** This is INDEX ***");
        } else {
            model.addAttribute("firstPageMessage", "This is INDEX");
        }
        return "index";

        // model.addObject("firstPageMessage",
        // model.getModel().get("firstPageMessage").toString()+" This is
        // INDEX");
        // model.setViewName("index");
        // return model;

    }

}
