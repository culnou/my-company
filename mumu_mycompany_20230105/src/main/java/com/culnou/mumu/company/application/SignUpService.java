package com.culnou.mumu.company.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.model.Company;
import com.culnou.mumu.company.domain.model.CompanyFactory;

import com.culnou.mumu.company.domain.model.CompanyRepository;
import com.culnou.mumu.company.domain.model.DomainName;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.SignInDto;
import com.culnou.mumu.compnay.controller.SignUpDto;

@Service
@Transactional
public class SignUpService {
	
	
	
	
	@Qualifier("companyJpaRepository")
	@Autowired
	private CompanyRepository companyRepository;
	
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	
	public MessageDto signUp(SignUpDto signUpDto) {
		MessageDto message = new MessageDto();
		try {
			
			//DTOの事前条件の検証
			if(signUpDto.getCompanyDomainName() == null) {
				throw new IllegalArgumentException("The_domain_name_may_not_be_set_to_null");
			}
			if(signUpDto.getCompanyDomainName().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_domain_names");
			}
			if(signUpDto.getCompanyName() == null) {
				throw new IllegalArgumentException("The_companyName_may_not_be_set_to_null");
			}
			if(signUpDto.getCompanyName().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_companyName");
			}
			if(signUpDto.getCompanyPassword() == null) {
				throw new IllegalArgumentException("The_companyPassword_may_not_be_set_to_null");
			}
			if(signUpDto.getCompanyPassword().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_company_password");
			}
			if(signUpDto.getEaName() == null) {
				throw new IllegalArgumentException("The_eaName_may_not_be_set_to_null");
			}
			if(signUpDto.getEaName().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_eaName");
			}
			if(signUpDto.getEaPassword() == null) {
				throw new IllegalArgumentException("The_eaPassword_may_not_be_set_to_null");
			}
			if(signUpDto.getEaPassword().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_eaPassword");
			}
			
			//Companyを生成するときエンティティの不変条件の保証としてNull、Empty、正規表現などの検証が行われる。
			Company company = CompanyFactory.createCompany(signUpDto.getCompanyDomainName());
			company.setCompanyName(signUpDto.getCompanyName());
			company.setCompanyPassword(signUpDto.getCompanyPassword());
			company.setEaName(signUpDto.getEaName());
			company.setEaPassword(signUpDto.getEaPassword());
			
			//事後条件
			//同じドメイン名の会社は登録されていないかチェックする。
			List<Company> companies = companyRepository.companiesOfDomainName(new DomainName(signUpDto.getCompanyDomainName()));
			if(companies.size() > 0) {
				throw new Exception("The_company_is_already_exist");
			}
			//同じ会社名の会社は登録されていないかチェックする。
			List<Company> sameNameCompanies = companyRepository.companiesOfCompanyName(signUpDto.getCompanyName());
			if(sameNameCompanies.size() > 0) {
				throw new Exception("The_company_is_already_exist");
			}
			companyRepository.save(company);
			message.setReturnValue(company.companyId().id());
			
			//事業ドメインの生成
			BusinessDomain businessDomain = new BusinessDomain(company.companyId(), businessDomainRepository.nextIdentity());
			businessDomain.setBusinessDomainName(company.companyName());
			//企業ドメインとして定義する。
			businessDomain.setEnterprise(true);
			businessDomainRepository.save(businessDomain);
			
			//事業単位の生成
			BusinessUnit businessUnit = new BusinessUnit(company.companyId(), businessDomain.businessDomainId(), businessUnitRepository.nextIdentity());
			businessUnit.setBusinessUnitName(company.companyName());
			businessUnitRepository.save(businessUnit);
			
			message.setResult("OK");
			
			
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto signIn(SignInDto signInDto) {
		MessageDto message = new MessageDto();
		try {
			//DTOの事前条件の検証
			if(signInDto.getCompanyDomainName() == null) {
				throw new IllegalArgumentException("The_domain_name_may_not_be_set_to_null");
			}
			if(signInDto.getCompanyDomainName().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_domain_names");
			}
			if(signInDto.getCompanyPassword() == null) {
				throw new IllegalArgumentException("The_companyPassword_may_not_be_set_to_null");
			}
			if(signInDto.getCompanyPassword().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_company_password");
			}
			if(signInDto.getEaName() == null) {
				throw new IllegalArgumentException("The_eaName_may_not_be_set_to_null");
			}
			if(signInDto.getEaName().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_eaName");
			}
			if(signInDto.getEaPassword() == null) {
				throw new IllegalArgumentException("The_eaPassword_may_not_be_set_to_null");
			}
			if(signInDto.getEaPassword().isEmpty()) {
				throw new IllegalArgumentException("Must_provide_eaPassword");
			}
			List<Company> companies = companyRepository.companiesOfDomainName(new DomainName(signInDto.getCompanyDomainName()));
            if(companies.size() != 1) {
            	throw new Exception("The_company_is_not_exist");
            }
            Company company = companies.get(0);
            if(!company.companyPassword().equals(signInDto.getCompanyPassword())) {
            	throw new Exception("The_companyPassword_is_wrong");
            }
            if(!company.eaName().equals(signInDto.getEaName())) {
            	throw new Exception("The_eaName_is_wrong");
            }
            if(!company.eaPassword().equals(signInDto.getEaPassword())) {
            	throw new Exception("The_eaPassword_is_wrong");
            }
            message.setReturnValue(company.companyId().id());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	
	}

}
