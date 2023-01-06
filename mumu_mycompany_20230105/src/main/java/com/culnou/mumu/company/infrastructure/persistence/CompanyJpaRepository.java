package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.Company;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CompanyRepository;
import com.culnou.mumu.company.domain.model.DomainName;
import com.culnou.mumu.company.domain.model.Industry;
import com.culnou.mumu.company.domain.model.IndustryGroup;
import com.culnou.mumu.company.domain.model.IndustrySubGroup;

@Service("companyJpaRepository")
@Transactional
public class CompanyJpaRepository implements CompanyRepository{
	
	@Autowired
	private CompanyJpaDataRepository repository;

	@Override
	public void save(Company company) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(company == null) {
			throw new IllegalArgumentException("The company may not be set to null.");
		}
		repository.save(this.convertCompanyToCompanyEntity(company));
		
		
	}

	@Override
	public void saveAll(List<Company> companies) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Company company) throws Exception {
		// TODO Auto-generated method stub
		if(company == null) {
			throw new IllegalArgumentException("The company may not be set to null.");
		}
		repository.delete(this.convertCompanyToCompanyEntity(company));
		
	}

	@Override
	public void removeAll(List<Company> companies) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Company companyOfId(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(companyId == null) {
			throw new IllegalArgumentException("Must provide a companyId.");
		}
		CompanyEntity entity = repository.findByCompanyId(companyId);
		//entityの存在チェックを行う。
		//これを実施しないと、以下のconvertCompanyEntityToCompanyでNullポインタエラーが発生する。
		if(entity == null) {
			throw new Exception("The company may not exist.");
		}
		return this.convertCompanyEntityToCompany(repository.findByCompanyId(companyId));
	}

	@Override
	public List<Company> companiesOfDomainName(DomainName domainName) throws Exception {
		// TODO Auto-generated method stub
		//検索条件チェック
		if(domainName == null) {
			throw new IllegalArgumentException("The domain name may not be set to null.");
		}
		List<CompanyEntity> entities = repository.findCompaniesOfDomainName(domainName);
		return this.convertCompanyEntitiesToCompanies(entities);
	}
	
	public List<Company> companiesOfCompanyName(String companyName) throws Exception{
		if(companyName == null) {
			throw new IllegalArgumentException("The company name may not be set to null.");
		}
		List<CompanyEntity> entities = repository.findCompaniesOfCompanyName(companyName);
		return this.convertCompanyEntitiesToCompanies(entities);
	}

	@Override
	public List<Company> companies() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> companiesOfIndustryGroup(IndustryGroup industryGroup) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> companiesOfIndustry(Industry industry) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> companiesOfIndustrySubGroup(IndustrySubGroup industrySubGroup) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Company convertCompanyEntityToCompany(CompanyEntity entity) {
		Company company = new Company(entity.getCompanyId(), entity.getDomainName());
		company.setCompanyPassword(entity.getCompanyPassword());
		company.setEaName(entity.getEaName());
		company.setEaPassword(entity.getEaPassword());
		if(entity.getCompanyName() != null) {
			company.setCompanyName(entity.getCompanyName());
		}
		if(entity.getEmail() != null) {
			company.setEmail(entity.getEmail());
		}
		if(entity.getIndustryGroup() != null) {
			company.setIndustryGroup(entity.getIndustryGroup());
		}
		if(entity.getAddress() != null) {
			company.setAddress(entity.getAddress());
		}
		if(entity.getPurpose() != null) {
			company.setPurpose(entity.getPurpose());
		}
		return company;
	}
	
	private List<Company> convertCompanyEntitiesToCompanies(List<CompanyEntity> entities){
		List<Company> companies = new ArrayList<>();
		for(CompanyEntity entity : entities) {
			companies.add(this.convertCompanyEntityToCompany(entity));
		}
		return companies;
	}
	
	private CompanyEntity convertCompanyToCompanyEntity(Company company) {
		CompanyEntity entity = new CompanyEntity();
		entity.setCompanyId(company.companyId());
		entity.setDomainName(company.domainName());
		entity.setCompanyPassword(company.companyPassword());
		entity.setEaName(company.eaName());
		entity.setEaPassword(company.eaPassword());
		if(company.address() != null) {
			entity.setAddress(company.address());
		}
		if(company.email() != null) {
			entity.setEmail(company.email());
		}
		if(company.industryGroup() != null) {
			entity.setIndustryGroup(company.industryGroup());
		}
		if(company.companyName() != null) {
			entity.setCompanyName(company.companyName());
		}
		if(company.purpose() != null) {
			entity.setPurpose(company.purpose());
		}
		
		
		return entity;
		
	}

}
