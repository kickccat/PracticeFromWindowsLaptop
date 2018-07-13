package io.sample.springdemo.domain.test;


import io.sample.springdemo.validation.test.AgeConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrganizationRepresentative {
    
    @NotBlank(message = "* First Name: cannot be blank")
    private String firstName;
    
    @NotEmpty(message = "* Surname: cannot be empty")
    @Size(min = 3, max = 10, message = "* Surname: min 3 characters required, max 10 characters allowed")
    private String lastName;
    
    @AgeConstraint(lower = 20, upper = 70, message = "* Age: range 20 to 70 only")
    private Integer age;
    
    @NotBlank(message = "* Zipcode: cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{6}", message = "* Zipcode: 6 characters and/or digits only")
    private String zipCode;
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
