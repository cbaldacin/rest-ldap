
package com.restldap.api.ldap;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.stereotype.Component;

import com.restldap.api.entities.Employee;

@Component
public class PicturelessEmployeeMapper extends EmployeeMapper {

	@Override
	protected Employee doMapFromContext(DirContextOperations context) {
		Employee employee = new Employee();

		employee.setEmail(context.getStringAttribute("mail") != null ? context.getStringAttribute("mail") : null);
		employee.setPosition(context.getStringAttribute("title") != null ? context.getStringAttribute("title") : null);
		employee.setId(context.getStringAttribute("sAMAccountName") != null
				? context.getStringAttribute("sAMAccountName") : null);
		employee.setName(context.getStringAttribute("cn") != null ? context.getStringAttribute("cn") : null);
		employee.setManager(context.getStringAttribute("manager") != null
				? parseManagerName(context.getStringAttribute("manager")) : null);

		employee.setPhoto(null);

		return employee;
	}

}
