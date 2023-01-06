package com.culnou.mumu.company.domain.model.business.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitService;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.BusinessDomainDto;
import com.culnou.mumu.compnay.controller.BusinessUnitDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class BusinessUnitDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private BusinessUnitService businessUnitService;
	
	
	public MessageDto defineBusinessUnit(BusinessUnitDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_BusinessUnit_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainId() == null) {
				throw new Exception("The_businessDomainId_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainId().isEmpty()) {
				throw new Exception("Must_provide_a_businessDomainId");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_commentId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			if(dto.getBusinessUnitName() == null) {
				throw new Exception("The_BusinessUnitName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitName().isEmpty()) {
				throw new Exception("Must_provide_a_businessUnitName");
			}
			if(dto.getStartDate() == null) {
				throw new Exception("The_startDate_may_not_be_set_to_null");
			}
			if(dto.getEndDate() == null) {
				throw new Exception("The_endDate_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			BusinessDomainDto businessDomain = companyService.findBusinessDomainById(dto.getBusinessDomainId());
			if(businessDomain == null) {
				throw new Exception("The_BusinessDomain_could_not_be_found");
			}
			//ビジネスロジック
			//産業の設定
			dto.setIndustryId(businessDomain.getIndustryId());
			dto.setIndustryName(businessDomain.getIndustryName());
			//ステータスの設定
			dto.setBusinessState(BusinessState.Planned);
			companyService.addBusinessUnit(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateBusinessUnit(BusinessUnitDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_BusinessUnit_may_not_be_set_to_null");
			}
			
			if(dto.getBusinessUnitId() == null) {
				throw new Exception("The_BusinessUnitId_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitId().isEmpty()) {
				throw new Exception("Must_provide_a_businessUnitId");
			}
			if(dto.getBusinessDomainId() == null) {
				throw new Exception("The_businessDomainId_may_not_be_set_to_null");
			}
			if(dto.getBusinessDomainId().isEmpty()) {
				throw new Exception("Must_provide_a_businessDomainId");
			}
			if(dto.getIndustryId() == null) {
				throw new Exception("The_industryId_may_not_be_set_to_null");
			}
			if(dto.getIndustryId().isEmpty()) {
				throw new Exception("Must_provide_a_industryId");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_commentId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			if(dto.getBusinessUnitName() == null) {
				throw new Exception("The_BusinessUnitName_may_not_be_set_to_null");
			}
			if(dto.getBusinessUnitName().isEmpty()) {
				throw new Exception("Must_provide_a_businessUnitName");
			}
			if(dto.getStartDate() == null) {
				throw new Exception("The_startDate_may_not_be_set_to_null");
			}
			if(dto.getEndDate() == null) {
				throw new Exception("The_endDate_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			BusinessDomainDto businessDomain = companyService.findBusinessDomainById(dto.getBusinessDomainId());
			if(businessDomain == null) {
				throw new Exception("The_BusinessDomain_could_not_be_found");
			}
			BusinessUnitDto businessUnit = companyService.findBusinessUnitById(dto.getBusinessUnitId());
			if(businessUnit == null) {
				throw new Exception("The_BusinessUnit_could_not_be_found");
			}
			//ビジネスロジック
			//事業単位に関連する要素（部門、プロジェクト・プログラム）がある場合、変更、削除できない。
			//→ステータスが変更できなくなるのでやめる。
			/*
			if(businessUnitService.isUsed(new BusinessUnitId(dto.getBusinessUnitId()))) {
				throw new Exception("The_businessUnit_has_already_used_at_edit");
			}
			*/
			
			dto.setIndustryId(businessDomain.getIndustryId());
			dto.setIndustryName(businessDomain.getIndustryName());
			
			companyService.updateBusinessUnit(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeBusinessUnit(String businessUnitId) {
		MessageDto message = new MessageDto();
		try {
			if(businessUnitId == null) {
				throw new Exception("The_BusinessUnitId_may_not_be_set_to_null");
			}
			if(businessUnitId.isEmpty()) {
				throw new Exception("Must_provide_a_businessUnitId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			BusinessUnitDto businessUnit = companyService.findBusinessUnitById(businessUnitId);
			if(businessUnit == null) {
				throw new Exception("The_BusinessUnit_could_not_be_found");
			}
			//ビジネスロジック
			//製品カテゴリ、顧客カテゴリなど事業単位に関連する要素が割り当てられてている場合、事業単位を削除することはできません。
			if(businessUnitService.isUsed(new BusinessUnitId(businessUnitId))) {
				throw new Exception("The_BusinessUnit_can_not_be_removed");
			}
			//ステータスが計画済以外の場合、削除できない。
			if(!businessUnit.getBusinessState().equals(BusinessState.Planned)) {
				throw new Exception("The_BusinessState_can_not_be_removed");
			}
			
			
			
			companyService.deleteBusinessUnit(businessUnitId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

}
