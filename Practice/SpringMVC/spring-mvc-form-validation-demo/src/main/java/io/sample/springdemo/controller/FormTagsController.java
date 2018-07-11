package io.sample.springdemo.controller;

import io.sample.springdemo.domain.test.OrganizationRegistration;
import io.sample.springdemo.service.test.OrganizationRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/formTagsDemo")
public class FormTagsController {
    
    @Autowired
    private OrganizationRegistrationService orgRegService;
    
    @RequestMapping("/home")
    public ModelAndView home(Model model) {
        return new ModelAndView("test/formTagsTestViews/formTagsHome", "orgRegistration", new OrganizationRegistration());
    }
    
    @RequestMapping(value = "/registerOrg", method = RequestMethod.POST)
    public String organizationRegistration(@ModelAttribute OrganizationRegistration orgRegistration, Model model) {
        model.addAttribute("orgReg", orgRegistration);
        return "test/formTagsTestViews/formTagsResult";
    }
    
    @ModelAttribute
    public void populateFormObjectWithData(Model model) {
        Map<String, Object> map = model.asMap();
        map.put("turnoverList", orgRegService.populateTurnover());
        map.put("typeList", orgRegService.populateTypes());
        map.put("serviceLengthList", orgRegService.populateServiceLengths());
        map.put("registeredPreviouslyList", orgRegService.populateRegisteredPreviously());
        map.put("subscriptionList", orgRegService.populateOptionalServices());
        map.put("premiumServiceList", orgRegService.populatePremiumServices());
        map.put("employeeStrengthList", orgRegService.populateEmployeeStrength());
    }
}
