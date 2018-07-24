package io.sample.console;

import io.sample.config.GameConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
    
//    private static final Logger log = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        log.info("Guess the number game");
        
        // Create context
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
        
        // Close context
        context.close();
    }
}