package io.sample.springdemo.validation.test;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailVerificationValidator implements ConstraintValidator<EmailVerification, String> {
    
    private String message;
    
    @Override
    public void initialize(EmailVerification constraintAnnotation) {
        message = constraintAnnotation.message();
    }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
    
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (email == null) {
            return false;
        }
        return emailValidator.isValid(email);
    }
}
