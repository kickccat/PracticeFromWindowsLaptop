package io.sample.controller;

import io.sample.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {
    
    DemoService service;
    
    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }
    
    // http://localhost:8080/todo-list/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    // http://localhost:8080/todo-list/welcome
    @GetMapping("welcome")
    public String welcome(Model model) {
        model.addAttribute("user", "Tim");
        log.info("model = {}", model);
        return "welcome";
    }
    
    @ModelAttribute("welcomeMessage")
    public String welcomeMessage() {
        log.info("welcomeMessage() called");
        return "Welcome to this Demo application";
    }
}
