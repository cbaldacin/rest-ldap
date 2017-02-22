package com.restldap.api.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.restldap.api.EmployeesController;
import com.restldap.api.entities.Employee;

@Component
public class EmployeeResourceAssembler implements ResourceAssembler<Employee, Resource<Employee>> {

	@Override
	public Resource<Employee> toResource(Employee employee) {
		Resource<Employee> resource = new Resource<Employee>(employee);
		resource.add(linkTo(methodOn(EmployeesController.class).findById(employee.getId())).withSelfRel());
	    return resource;
	}

	public Resources<Resource<Employee>> toResources(List<Employee> employees) {
		List<Resource<Employee>> result = new ArrayList<Resource<Employee>>();
		
		for(Employee employee : employees) {
			result.add(toResource(employee));
		}
		
		return new Resources<Resource<Employee>>(result, linkTo(methodOn(EmployeesController.class).findAll()).withSelfRel());

	}

	

}
