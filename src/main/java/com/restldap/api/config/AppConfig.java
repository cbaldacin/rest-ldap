package com.restldap.api.config;

import java.util.HashMap;
import java.util.Map;

import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.ldap.control.SortControlDirContextProcessor;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextProcessor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.ldap.filter.PresentFilter;

@Configuration
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class AppConfig {

	@Value("${app.ldapBase}")
	private String ldapBase;

	@Value("${app.ldapUrl}")
	private String ldapUrl;

	@Value("${app.ldapUserDn}")
	private String ldapUserDn;

	@Value("${app.ldapPassword}")
	private String ldapPassword;

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setBase(ldapBase);
		contextSource.setUrl(ldapUrl);
		contextSource.setPassword(ldapPassword);
		contextSource.setUserDn(ldapUserDn);

		Map<String, Object> baseEnvProps = new HashMap<String, Object>();
		baseEnvProps.put("java.naming.ldap.attributes.binary", "objectSid thumbnailPhoto");

		contextSource.setBaseEnvironmentProperties(baseEnvProps);

		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate(ContextSource contextSource) {
		LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
		ldapTemplate.setIgnorePartialResultException(true);
		return ldapTemplate;
	}

	@Bean
	public SearchControls searchControls() {
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		return searchControls;

	}

	@Bean
	public DirContextProcessor sortByName() {
		return new SortControlDirContextProcessor("cn");
	}
	
	
	/**
	 * Specific initialFilter to retrieve users in the tested AD
	 */
	@Bean
	public AndFilter initialFilter() {
		AndFilter initialFilter = new AndFilter();
		initialFilter.and(new EqualsFilter("objectclass", "person"));
		initialFilter.and(new EqualsFilter("objectclass", "user"));
		initialFilter.and(new EqualsFilter("objectclass", "organizationalPerson"));
		initialFilter.and(new EqualsFilter("objectclass", "top"));

		initialFilter.and(new PresentFilter("title"));

		OrFilter orFilter = new OrFilter();
		orFilter.or(new EqualsFilter("userAccountControl", "512"));
		orFilter.or(new EqualsFilter("userAccountControl", "544"));
		orFilter.or(new EqualsFilter("userAccountControl", "66048"));

		initialFilter.and(orFilter);
		
		return initialFilter;
	}
	
	
	

}
