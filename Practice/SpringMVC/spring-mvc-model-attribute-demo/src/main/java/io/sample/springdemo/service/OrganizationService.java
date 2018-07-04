package io.sample.springdemo.service;

import io.sample.springdemo.dao.OrganizationDao;
import io.sample.springdemo.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
	
	@Autowired
	private OrganizationDao organizationDao;
	
	public List<Organization> getOrgList() {
		return organizationDao.getAllOrganizations();
	}

}
