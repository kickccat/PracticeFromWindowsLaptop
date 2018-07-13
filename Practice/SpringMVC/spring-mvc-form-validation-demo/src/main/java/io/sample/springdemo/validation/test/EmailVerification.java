package io.sample.springdemo.validation.test;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailVerificationValidator.class)
public @interface EmailVerification {
    
    String message() default "* Email is not valid";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
