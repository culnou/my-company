package com.culnou.mumu.company.domain.model.customer.category;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.culnou.mumu.company.domain.service.CustomerCategoryChecker;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CustomerCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;




@Service
@Transactional
public class CustomerCategoryDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	CustomerCategoryChecker checker;
	
	public MessageDto defineCustomerCategory(CustomerCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.addCustomerCategory(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
			
		}
		return message;
		
	}
	
	
	public MessageDto updateCustomerCategory(CustomerCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(dto.getCustomerCategoryId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getCustomerCategoryId()));
			}
			companyService.updateCustomerCategory(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
			
		}
		return message;
		
	}
	
	public MessageDto deleteCustomerCategory(String id) {
		MessageDto message = new MessageDto();
		try {
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(id).equals("OK")) {
				throw new Exception(checker.avarable(id));
			}
			companyService.deleteCustomerCategory(id);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
			
		}
		return message;
		
	}

}
