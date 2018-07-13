package io.sample.springdemo.validation.test;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AgeConstraintValidator.class)
public @interface AgeConstraint {
    
    // Message attribute
    String message() default "* Age must be between 18 and 60 years";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    // Lower attribute
    int lower() default 18;
    
    // Upper attribute
    int upper() default 60;
    
}
