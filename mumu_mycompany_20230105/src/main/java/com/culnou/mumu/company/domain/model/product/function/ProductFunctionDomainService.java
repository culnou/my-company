package com.culnou.mumu.company.domain.model.product.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductCategoryDto;
import com.culnou.mumu.compnay.controller.ProductFunctionDto;
import com.culnou.mumu.compnay.controller.ProductTaskDto;

@Service
@Transactional
public class ProductFunctionDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	
	public MessageDto defineProductFunction(ProductFunctionDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new Exception("The_productFunction_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			if(dto.getProductCategoryId() == null) {
				throw new Exception("The_productCategoryId_may_not_be_set_to_null");
			}
			if(dto.getProductCategoryId().isEmpty()) {
				throw new Exception("Must_provide_a_productCategoryId");
			}
			if(dto.getProductTaskId() == null) {
				throw new Exception("The_productTaskId_may_not_be_set_to_null");
			}
			if(dto.getProductTaskId().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeId");
			}
			if(dto.getProductFunctionName() == null) {
				throw new Exception("The_productFunctionName_may_not_be_set_to_null");
			}
			if(dto.getProductFunctionName().isEmpty()) {
				throw new Exception("Must_provide_a_productFunctionName");
			}
			if(dto.getFunctionId() == null) {
				throw new Exception("The_FunctionId_may_not_be_set_to_null");
			}
			if(dto.getFunctionId().isEmpty()) {
				throw new Exception("Must_provide_a_FunctionId");
			}
			if(dto.getUrl() == null || dto.getUrl().isEmpty()) {
				throw new Exception("The_url_may_not_be_set_to_null");
			}
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ProductCategoryDto productCategory = companyService.findProductCategoryById(dto.getProductCategoryId());
			if(productCategory == null) {
				throw new Exception("The_productCategory_could_not_be_found");
			}
			ProductTaskDto productTask = companyService.findProductTaskById(dto.getProductTaskId());
			if(productTask == null) {
				throw new Exception("The_productTask_could_not_be_found");
			}
			companyService.addProductFunction(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateProductFunction(ProductFunctionDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto == null) {
				throw new Exception("The_productFunction_may_not_be_set_to_null");
			}
			if(dto.getProductFunctionId() == null) {
				throw new Exception("The_productFunctionId_may_not_be_set_to_null");
			}
			if(dto.getProductFunctionId().isEmpty()) {
				throw new Exception("Must_provide_a_productFunctionId");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			if(dto.getProductCategoryId() == null) {
				throw new Exception("The_productCategoryId_may_not_be_set_to_null");
			}
			if(dto.getProductCategoryId().isEmpty()) {
				throw new Exception("Must_provide_a_productCategoryId");
			}
			if(dto.getProductTaskId() == null) {
				throw new Exception("The_productTaskId_may_not_be_set_to_null");
			}
			if(dto.getProductTaskId().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeId");
			}
			if(dto.getProductFunctionName() == null) {
				throw new Exception("The_productFunctionName_may_not_be_set_to_null");
			}
			if(dto.getProductFunctionName().isEmpty()) {
				throw new Exception("Must_provide_a_productFunctionName");
			}
			if(dto.getFunctionId() == null) {
				throw new Exception("The_FunctionId_may_not_be_set_to_null");
			}
			if(dto.getFunctionId().isEmpty()) {
				throw new Exception("Must_provide_a_FunctionId");
			}
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ProductCategoryDto productCategory = companyService.findProductCategoryById(dto.getProductCategoryId());
			if(productCategory == null) {
				throw new Exception("The_productCategory_could_not_be_found");
			}
			ProductTaskDto productTask = companyService.findProductTaskById(dto.getProductTaskId());
			if(productTask == null) {
				throw new Exception("The_productTask_could_not_be_found");
			}
			ProductFunctionDto productFunction = companyService.findProductFunctionById(dto.getProductFunctionId());
			if(productFunction == null) {
				throw new Exception("The_productFunction_could_not_be_found");
			}
			companyService.updateProductFunction(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto deleteProductFunction(String productFunctionId) {
		MessageDto message = new MessageDto();
		try {
			if(productFunctionId == null) {
				throw new Exception("The_productFunctionId_may_not_be_set_to_null");
			}
			if(productFunctionId.isEmpty()) {
				throw new Exception("Must_provide_a_productFunctionId");
			}
			ProductFunctionDto productFunction = companyService.findProductFunctionById(productFunctionId);
			if(productFunction == null) {
				throw new Exception("The_productFunction_could_not_be_found");
			}
			companyService.deleteProductFunction(productFunctionId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	

}
