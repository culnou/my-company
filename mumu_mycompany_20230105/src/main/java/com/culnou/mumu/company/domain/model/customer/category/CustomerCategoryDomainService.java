package com.culnou.mumu.company.domain.model.customer.category;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CustomerCategory;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerCategoryRepository;
import com.culnou.mumu.company.domain.service.CustomerCategoryChecker;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CustomerCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.domain.model.AssociatedCode;




@Service
@Transactional
public class CustomerCategoryDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	CustomerCategoryChecker checker;
	
	@Qualifier("customerCategoryJpaRepository")
	@Autowired
	private CustomerCategoryRepository customerCategoryRepository;
	
	public MessageDto addAssociatedCodeToCustomerCategory(String customerCategoryId, AssociatedCode code){
		MessageDto message = new MessageDto();
		Logger logger = LogManager.getLogger();
		try {
			if(customerCategoryId == null) {
				throw new IllegalArgumentException("The_customerCategoryId_may_not_be_set_to_null");
			}
			if(customerCategoryId.isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_customerCategoryId");
			}
			if(code == null) {
				throw new IllegalArgumentException("The_associatedCode_may_not_be_set_to_null");
			}
			CustomerCategory customerCategory = customerCategoryRepository.customerCategoryOfId(new CustomerCategoryId(customerCategoryId));
			if(customerCategory == null) {
				throw new Exception("The_customerCategory_does_not_exist");
			}
			customerCategory.setAssociatedCode(code);
			customerCategoryRepository.save(customerCategory);
			message.setResult("OK");
			//ロギング
			logger.info("addAssociatedCodeToCustomerCategory");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			//ロギング
			logger.error("addAssociatedCodeToCustomerCategory error",ex);
			
		}
		return message;
		
	}
	
	public MessageDto removeAssociatedCodeToCustomerCategory(String customerCategoryId, AssociatedCode code){
		MessageDto message = new MessageDto();
		Logger logger = LogManager.getLogger();
		try {
			if(customerCategoryId == null) {
				throw new IllegalArgumentException("The_customerCategoryId_may_not_be_set_to_null");
			}
			if(customerCategoryId.isEmpty()) {
				throw new IllegalArgumentException("Must_provide_a_customerCategoryId");
			}
			if(code == null) {
				throw new IllegalArgumentException("The_associatedCode_may_not_be_set_to_null");
			}
			CustomerCategory customerCategory = customerCategoryRepository.customerCategoryOfId(new CustomerCategoryId(customerCategoryId));
			if(customerCategory == null) {
				throw new Exception("The_customerCategory_does_not_exist");
			}
			customerCategory.removeAssociatedCode(code);
			customerCategoryRepository.save(customerCategory);
			message.setResult("OK");
			//ロギング
			logger.info("removeAssociatedCodeToCustomerCategory");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			//ロギング
			logger.error("removeAssociatedCodeToCustomerCategory error",ex);
			
		}
		return message;
		
	}
	
	
	
	public MessageDto defineCustomerCategory(CustomerCategoryDto dto) {
		MessageDto message = new MessageDto();
		Logger logger = LogManager.getLogger();
		try {
			companyService.addCustomerCategory(dto);
			message.setResult("OK");
			//ロギング
			logger.info("defineCustomerCategory");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			//ロギング
			logger.error("defineCustomerCategory error",ex);
			
		}
		return message;
		
	}
	
	
	public MessageDto updateCustomerCategory(CustomerCategoryDto dto) {
		MessageDto message = new MessageDto();
		Logger logger = LogManager.getLogger();
		try {
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(dto.getCustomerCategoryId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getCustomerCategoryId()));
			}
			companyService.updateCustomerCategory(dto);
			message.setResult("OK");
			//ロギング
			logger.info("updateCustomerCategory");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			//ロギング
			logger.error("updateCustomerCategory error",ex);
			
		}
		return message;
		
	}
	
	public MessageDto deleteCustomerCategory(String id) {
		MessageDto message = new MessageDto();
		Logger logger = LogManager.getLogger();
		try {
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(id).equals("OK")) {
				throw new Exception(checker.avarable(id));
			}
			companyService.deleteCustomerCategory(id);
			message.setResult("OK");
			//ロギング
			logger.info("deleteCustomerCategory");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			//ロギング
			logger.error("deleteCustomerCategory error",ex);
		}
		return message;
		
	}

}
