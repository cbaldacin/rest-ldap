package com.restldap.api.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ldap.core.DirContextProcessor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.HardcodedFilter;
import org.springframework.stereotype.Service;

import com.restldap.api.entities.Employee;


@Service
public class EmployeeRepository {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private AndFilter initialFilter;

	@Autowired
	private EmployeeMapper fullEmployeeMapper;

	@Autowired
	private EmployeeMapper picturelessEmployeeMapper;

	@Autowired
	private SearchControls searchControls;

	@Autowired
	private DirContextProcessor sortByName;
	
	@Value("${app.ldapUserSearchBase}")
	private String ldapUserSearchBase;

	public Employee findById(String id) {
		AndFilter vntFilter = new AndFilter();
		vntFilter.and(initialFilter).and((new HardcodedFilter("(sAMAccountName=" + id + ")")));

		try {
			return ldapTemplate.searchForObject(query().filter(vntFilter), fullEmployeeMapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	public List<Employee> findAll() {
		List<Employee> result = ldapTemplate.search(ldapUserSearchBase, initialFilter.encode(), searchControls,
				picturelessEmployeeMapper, sortByName);
		return result;
	}

}
