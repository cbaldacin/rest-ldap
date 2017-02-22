
package com.restldap.api.ldap;

import javax.naming.NamingException;
import javax.xml.bind.DatatypeConverter;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.stereotype.Component;

import com.restldap.api.entities.Employee;

@Component
public class FullEmployeeMapper extends EmployeeMapper {

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

		try {
			String decodedPhoto = context.getAttributes().get("thumbnailPhoto") != null
					? DatatypeConverter.printBase64Binary((byte[]) context.getAttributes().get("thumbnailPhoto").get())
					: null;
			employee.setPhoto(decodedPhoto);

		} catch (NamingException e) {

			// Not a big deal, It's just pictureless
			employee.setPhoto(null);
		}

		return employee;
	}


	

}
