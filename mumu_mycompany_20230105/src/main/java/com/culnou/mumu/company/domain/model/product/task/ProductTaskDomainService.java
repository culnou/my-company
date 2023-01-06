package com.culnou.mumu.company.domain.model.product.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.ProductTaskId;
import com.culnou.mumu.company.domain.model.ProductTaskService;

import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductTaskDto;
import com.culnou.mumu.compnay.controller.ProductTypeDto;

@Service
@Transactional
public class ProductTaskDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private ProductTaskService productTaskService;
	
	
	public MessageDto definProductTask(ProductTaskDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ProductTask_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			if(dto.getProductTypeId() == null) {
				throw new Exception("The_productTypeId_may_not_be_set_to_null");
			}
			if(dto.getProductTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeId");
			}
			if(dto.getProductTypeName() == null) {
				throw new Exception("The_productTypeName_may_not_be_set_to_null");
			}
			if(dto.getProductTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeName");
			}
			if(dto.getProductTaskDescription() == null) {
				throw new Exception("The_productTypeDescription_may_not_be_set_to_null");
			}
			if(dto.getProductTaskDescription().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeDescripton");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ProductTypeDto productType = companyService.findProductTypeById(dto.getProductTypeId());
			if(productType == null) {
				throw new Exception("The_productType_could_not_be_found");
			}
			//ビジネスロジック
			companyService.addProductTask(dto);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateProductTask(ProductTaskDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ProductTask_may_not_be_set_to_null");
			}
			if(dto.getProductTaskId() == null) {
				throw new Exception("The_productTaskId_may_not_be_set_to_null");
			}
			if(dto.getProductTaskId().isEmpty()) {
				throw new Exception("Must_provide_a_productTaskId");
			}
			if(dto.getProductTypeId() == null) {
				throw new Exception("The_productTypeId_may_not_be_set_to_null");
			}
			if(dto.getProductTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeId");
			}
			if(dto.getProductTypeName() == null) {
				throw new Exception("The_productTypeName_may_not_be_set_to_null");
			}
			if(dto.getProductTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeName");
			}
			if(dto.getProductTaskDescription() == null) {
				throw new Exception("The_productTypeDescription_may_not_be_set_to_null");
			}
			if(dto.getProductTaskDescription().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeDescripton");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			ProductTaskDto productTask = companyService.findProductTaskById(dto.getProductTaskId());
			if(productTask == null) {
				throw new Exception("The_productTask_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ProductTypeDto productType = companyService.findProductTypeById(dto.getProductTypeId());
			if(productType == null) {
				throw new Exception("The_productType_could_not_be_found");
			}
			//ビジネスロジック
			//製品タスクが使用されている場合、名前、仕様を変更することはできない。
			if(productTaskService.isUsed(new ProductTaskId(dto.getProductTaskId()))) {
				if(!productTask.getProductTaskDescription().equals(dto.getProductTaskDescription())) {
					throw new Exception("productTaskDescription_can_not_be_changed");
				}
				if(!productTask.getProductTaskName().equals(dto.getProductTaskName())) {
					throw new Exception("productTaskName_can_not_be_changed");
				}
			}
			companyService.updateProductTask(dto);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto deleteProductTask(String productTaskId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(productTaskId == null) {
				throw new Exception("The_productTaskId_may_not_be_set_to_null");
			}
			if(productTaskId.isEmpty()) {
				throw new Exception("Must_provide_a_productTaskId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			ProductTaskDto productTask = companyService.findProductTaskById(productTaskId);
			if(productTask == null) {
				throw new Exception("The_productTask_could_not_be_found");
			}
			//ビジネスロジック
			//製品タスクが使用されている場合、削除することはできない。
			if(productTaskService.isUsed(new ProductTaskId(productTaskId))) {
				throw new Exception("The_ProductTask_can_not_be_removed");
			}
			companyService.deleteProductTask(productTaskId);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	

}
