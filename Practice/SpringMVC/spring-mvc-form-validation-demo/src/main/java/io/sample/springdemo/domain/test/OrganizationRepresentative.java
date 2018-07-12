package io.sample.springdemo.domain.test;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationRepresentative {
    
    @NotBlank(message = "* First Name: cannot be blank")
    private String firstName;
    
    @NotEmpty(message = "* Surname: cannot be empty")
    @Size(min = 3, max = 10, message = "* Surname: min 3 characters required, max 10 characters allowed")
    private String lastName;
    
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
}
