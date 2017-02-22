
package com.restldap.api.ldap;

import org.springframework.ldap.core.support.AbstractContextMapper;

import com.restldap.api.entities.Employee;


public abstract class EmployeeMapper extends AbstractContextMapper<Employee> {
	/**
	* Specific parse necessary in the tested AD to get mananager's name
	*/
	protected static String parseManagerName(final String manager) {
		final String[] strs = manager.split(",");
		for (final String str : strs) {
			if (str.startsWith("CN=")) {
				return str.substring(3);
			}
		}
		return manager;
	}

}
