package io.sample.springdemo.validation.test;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldsVerificationValidator.class)
public @interface FieldsVerification {
    
    String message() default "* Field values do not match";
    String field();
    String fieldMatch();
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface List {
        FieldsVerification[] value();
    }
}
