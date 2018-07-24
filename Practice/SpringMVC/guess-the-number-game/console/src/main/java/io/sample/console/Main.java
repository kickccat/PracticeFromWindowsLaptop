package io.sample.console;

import io.sample.config.AppConfig;
import io.sample.MessageGenerator;
import io.sample.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        log.info("Guess the number game");
        
        // Context
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // Get number generator bean from context
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);
        
        // Random number
        int number = numberGenerator.next();
        
        // Log generated number
        log.info("number = {}", number);
        
        // Get message generator bean from context
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);
        
        // Log generated message
        log.info("getMainMessage = {}", messageGenerator.getMainMessage());
        log.info("getResultMessage = {}", messageGenerator.getResultMessage());
        
        // Close context
        context.close();
    }
}
