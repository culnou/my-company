package com.culnou.mumu.company.domain.model;

import java.util.UUID;

public class CompanyFactory {
	
	public static Company createCompany(String domaiName) throws Exception{
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        CompanyId companyId = new CompanyId(str);
        DomainName name = new DomainName(domaiName);
		return new Company(companyId, name);
	}

}
