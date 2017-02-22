package com.restldap.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restldap.api.entities.Employee;
import com.restldap.api.exceptions.EmployeeNotFoundException;
import com.restldap.api.ldap.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	public Employee getEmployeeById(String id) {
		
		Employee employee = repository.findById(id);
		
		if (employee==null) {
			throw new EmployeeNotFoundException();
		}
		
		return employee;
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}
	
	
	

}
