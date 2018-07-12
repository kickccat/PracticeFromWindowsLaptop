package io.sample.springdemo.controller.test;

import io.sample.springdemo.domain.test.OrganizationRepresentative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/formValidationDemo")
public class FormValidationDemoController {
    
    private static Logger LOGGER = LoggerFactory.getLogger(FormValidationDemoController.class);
    
    @RequestMapping("/home")
    public ModelAndView home(Model model) {
        return new ModelAndView("test/formValidationTestViews/formValidationHome", "orgRep", new OrganizationRepresentative());
    }
    
    @RequestMapping(value = "/registerOrgRep", method = RequestMethod.POST)
    public String organizationRepresentativeRegistration(@Valid @ModelAttribute("orgRep") OrganizationRepresentative orgRepresentative,
                                                         BindingResult result, Model model) {
        // Debug code
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors) {
                LOGGER.info("field error: " + error.getDefaultMessage());
            }
        
            List<ObjectError> objectErrors = result.getGlobalErrors();
            for (ObjectError error : objectErrors) {
                LOGGER.info("global error: " + error.getDefaultMessage());
            }
            
            return "test/formValidationTestViews/formValidationHome";
            
        } else {
            return "test/formValidationTestViews/formValidationResult";
        }
    }
}
