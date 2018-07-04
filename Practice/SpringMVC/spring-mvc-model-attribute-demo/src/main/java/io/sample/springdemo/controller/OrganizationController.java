package io.sample.springdemo.controller;

import io.sample.springdemo.domain.Organization;
import io.sample.springdemo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping("/location")
	public String addLocation() {
		return "location";
	}
	
	@RequestMapping("/listOrgs")
	public String listOrganizations(Model model) {
		List<Organization> orgs = organizationService.getOrgList();
		model.addAttribute("orgList", orgs);
		return "listOrganizations";
	}
}
