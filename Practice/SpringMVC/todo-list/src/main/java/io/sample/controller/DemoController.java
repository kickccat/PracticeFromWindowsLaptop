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
    
    // Fields
    private final DemoService service;
    
    // Constructors
    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }
    
    // Request methods
    // http://localhost:8080/todo-list/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    // http://localhost:8080/todo-list/welcome
    @GetMapping("welcome")
    public String welcome(Model model) {
        model.addAttribute("helloMessage", service.getHelloMessage("Zip"));
        log.info("model = {}", model);
        return "welcome";
    }
    
    // Model attribute
    @ModelAttribute("welcomeMessage")
    public String welcomeMessage() {
        log.info("welcomeMessage() called");
        return service.getWelcomeMessage();
    }
}
