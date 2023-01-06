package com.culnou.mumu.company.domain.model.customer.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.CustomerTypeService;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.CustomerTypeDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class CustomerTypeDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private CustomerTypeService customerTypeService;
	
	/*
	 * 顧客は何に価値を見出すか定義する
	 */
	public MessageDto defineCustomerType(CustomerTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_CustomerType_may_not_be_set_to_null");
			}
			if(dto.getPersonality() == null) {
				throw new Exception("The_personality_may_not_be_set_to_null");
			}
			if(dto.getCustomerTypeName() == null) {
				throw new Exception("The_customerTypeName_may_not_be_set_to_null");
			}
			if(dto.getCustomerTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_customerTypeName");
			}
			if(dto.getValues() == null) {
				throw new Exception("The_values_may_not_be_set_to_null");
			}
			if(dto.getValues().isEmpty()) {
				throw new Exception("Must_provide_a_values");
			}
			if(dto.getIssue() == null) {
				throw new Exception("The_issue_may_not_be_set_to_null");
			}
			if(dto.getIssue().isEmpty()) {
				throw new Exception("Must_provide_a_issue");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			
			//ビジネスロジック
			companyService.addCustomerType(dto);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}
	
	public MessageDto updateCustomerType(CustomerTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_CustomerType_may_not_be_set_to_null");
			}
			if(dto.getCustomerTypeId() == null) {
				throw new Exception("The_customerTypeId_may_not_be_set_to_null");
			}
			if(dto.getCustomerTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_customerTypeId");
			}
			if(dto.getPersonality() == null) {
				throw new Exception("The_personality_may_not_be_set_to_null");
			}
			
			if(dto.getCustomerTypeName() == null) {
				throw new Exception("The_customerTypeName_may_not_be_set_to_null");
			}
			if(dto.getCustomerTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_customerTypeName");
			}
			if(dto.getValues() == null) {
				throw new Exception("The_values_may_not_be_set_to_null");
			}
			if(dto.getValues().isEmpty()) {
				throw new Exception("Must_provide_a_values");
			}
			if(dto.getIssue() == null) {
				throw new Exception("The_issue_may_not_be_set_to_null");
			}
			if(dto.getIssue().isEmpty()) {
				throw new Exception("Must_provide_a_issue");
			}
			
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			CustomerTypeDto customerType = companyService.findCustomerTypeById(dto.getCustomerTypeId());
			if(customerType == null) {
				throw new Exception("The_customerType_could_not_be_found");
			}
			//ビジネスロジック
			//顧客カテゴリや事業ドメインに使われている場合、人格を変更することはできない。
			if(customerTypeService.isUsed(new CustomerTypeId(customerType.getCustomerTypeId()))) {
				if(!customerType.getPersonality().equals(dto.getPersonality())) {
					throw new Exception("Personality_can_not_be_changed");
				}
			}
			companyService.updateCustomerType(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeCustomerType(String customerTypeId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(customerTypeId == null) {
				throw new Exception("The_customerTypeId_may_not_be_set_to_null");
			}
			if(customerTypeId.isEmpty()) {
				throw new Exception("Must_provide_a_customerTypeId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CustomerTypeDto customerType = companyService.findCustomerTypeById(customerTypeId);
			if(customerType == null) {
				throw new Exception("The_customerType_could_not_be_found");
			}
			//ビジネスロジック
			//顧客カテゴリや事業ドメインに使われている場合、削除することはできない。
			if(customerTypeService.isUsed(new CustomerTypeId(customerType.getCustomerTypeId()))) {
				throw new Exception("The_CustomerTyoe_can_not_be_removed");
			}
			companyService.deleteCustomerType(customerTypeId);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

}
