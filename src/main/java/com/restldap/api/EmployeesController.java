package com.restldap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restldap.api.entities.Employee;
import com.restldap.api.exceptions.EmployeeNotFoundException;
import com.restldap.api.hateoas.EmployeeResourceAssembler;
import com.restldap.api.service.EmployeeService;

@RestController
@RequestMapping("/v1/employees")
public class EmployeesController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Resource<Employee> findById(@PathVariable final String id) throws EmployeeNotFoundException {

		return assembler.toResource(employeeService.getEmployeeById(id));

	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Resources<Resource<Employee>> findAll() {

		Resources<Resource<Employee>> resources = assembler.toResources(employeeService.findAll());
		
		return resources;

	}

}
