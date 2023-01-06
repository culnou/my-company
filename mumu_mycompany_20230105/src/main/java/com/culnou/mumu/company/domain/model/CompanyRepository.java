package com.culnou.mumu.company.domain.model;

import java.util.List;




public interface CompanyRepository {
	
	//コマンド。
	public void save(Company company) throws Exception;
			
	public void saveAll(List<Company> companies) throws Exception;
	
	public void remove(Company company) throws Exception;
			
	public void removeAll(List<Company> companies) throws Exception;
	
	//クエリ。
    public Company companyOfId(CompanyId companyId) throws Exception;
    
    public List<Company> companiesOfDomainName(DomainName domainName) throws Exception;
    
    public List<Company> companiesOfCompanyName(String companyName) throws Exception;
    
    public List<Company> companies() throws Exception;
    
    public List<Company> companiesOfIndustryGroup(IndustryGroup industryGroup) throws Exception;
    
    public List<Company> companiesOfIndustry(Industry industry) throws Exception;
    
    public List<Company> companiesOfIndustrySubGroup(IndustrySubGroup industrySubGroup) throws Exception;
    
    
    


}
