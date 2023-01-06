package com.culnou.mumu.company.domain.model.business.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.BusinessDomainService;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.BusinessDomainDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class BusinessDomainDomainService {
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private BusinessDomainService businessDomainService;
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	@Autowired
	private AddBusinesssDomainChecker addBusinessDomainChecker;
	/*
	 * 事業のパーパスを定義する
	 */
	public MessageDto defineBusinessDomain(BusinessDomainDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_BusinessDomain_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainName() == null) {
				throw new Exception("The_businessDomainName_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainName().isEmpty()) {
				throw new Exception("Must_provide_a_businessDomainName");
			}
			if(dto.getPurpose() == null) {
				throw new Exception("The_businessDomainPurpse_may_not_be_set_to_null");
			}
			if(dto.getPurpose().isEmpty()) {
				throw new Exception("Must_provide_a_businessDomainPurpse");
			}
			if(dto.getIndustryId() == null) {
				throw new Exception("The_industryId_may_not_be_set_to_null");
			}
			if(dto.getIndustryId().isEmpty()) {
				throw new Exception("Must_provide_a_industryId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			
			//以下のロジックはドメイン知識なのでドメインサービスにまかせる。2022/9/8
			/*
			List<BusinessDomain> enterprises = businessDomainRepository.findEnterprises(true, new CompanyId(dto.getCompanyId()));
			if(enterprises.size() > 0) {
				
				//企業ドメインが産業に割り当てられていない場合、例外を発生させる。2022/08/16
				BusinessDomain enterpriseDomain = enterprises.get(0);
				if(enterpriseDomain.industry() == null) {
					throw new Exception("You_must_assign_enterprise_domain_to_industries_first");
				}
				//会社に企業ドメインがあった場合、新規事業ドメインは、事業ドメインに設定し企業ドメインは唯一になるようにする。
				dto.setEnterprise(false);
			}
			*/
			//事業ドメインが追加できるか検証する。
			if(!addBusinessDomainChecker.addable(dto.getCompanyId())) {
				throw new Exception("Yout_can_not_add_business_domain");
			}
			
			
			companyService.addBusinessDomain(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateBusinessDomain(BusinessDomainDto dto) {
		MessageDto message = new MessageDto();
		try {
			
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_BusinessDomain_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainId() == null) {
				throw new Exception("The_businessDomainId_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainName() == null) {
				throw new Exception("The_businessDomainName_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainName().isEmpty()) {
				throw new Exception("Must_provide_a_businessDomainName");
			}
			if(dto.getPurpose() == null) {
				throw new Exception("The_businessDomainPurpse_may_not_be_set_to_null");
			}
			if(dto.getPurpose().isEmpty()) {
				throw new Exception("Must_provide_a_businessDomainPurpse");
			}
			if(dto.getIndustryId() == null) {
				throw new Exception("The_industryId_may_not_be_set_to_null");
			}
			if(dto.getIndustryId().isEmpty()) {
				throw new Exception("Must_provide_a_industryId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			BusinessDomainDto businessDomain = companyService.findBusinessDomainById(dto.getBusinessDomainId());
			if(businessDomain == null) {
				throw new Exception("The_businessDomain_could_not_be_found");
			}
			//ビジネスロジック
			//ジョブや事業単位など、事業ドメインに関連する要素が割り当てられてている場合の処理
			if(businessDomainService.isUsed(new BusinessDomainId(dto.getBusinessDomainId()))) {
				//名前を変更することはできません。
				if(!businessDomain.getBusinessDomainName().equals(dto.getBusinessDomainName())) {
					throw new Exception("businessDomainName_can_not_be_change");
				}
				//産業を変更することはできません。
				if(!businessDomain.getIndustryId().equals(dto.getIndustryId())) {
					throw new Exception("Industry_can_not_be_changed");
				}
			}
			//会社に企業ドメインがあった場合、新規事業ドメインは、事業ドメインに設定し企業ドメインは唯一になるようにする。
			List<BusinessDomain> enterprises = businessDomainRepository.findEnterprises(true, new CompanyId(dto.getCompanyId()));
			if(enterprises.size() > 0) {
				dto.setEnterprise(false);
			}
			companyService.updateBusinessDomain(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeBusinessDomain(String businessDomainId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(businessDomainId == null) {
				throw new Exception("The_businessDomainId_may_not_be_set_to_null");
			}
			if(businessDomainId.isEmpty()) {
				throw new Exception("Must_provide_a_businessDomainId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			BusinessDomainDto businessDomain = companyService.findBusinessDomainById(businessDomainId);
			if(businessDomain == null) {
				throw new Exception("The_businessDomain_could_not_be_found");
			}
			//ビジネスロジック
			//ジョブや事業単位など、事業ドメインに関連する要素が割り当てられてている場合、事業ドメインを削除することはできません。
			if(businessDomainService.isUsed(new BusinessDomainId(businessDomainId))) {
				throw new Exception("The_BusinessDomain_can_not_be_removed");
			}
			companyService.deleteBusinessDomain(businessDomainId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

}
