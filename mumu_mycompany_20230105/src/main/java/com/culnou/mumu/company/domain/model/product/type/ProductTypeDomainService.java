package com.culnou.mumu.company.domain.model.product.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.ProductTypeId;
import com.culnou.mumu.company.domain.model.ProductTypeService;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;

import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductTypeDto;

@Service
@Transactional
public class ProductTypeDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private ProductTypeService productTypeService;
	
	
	/*
	 * 製品が課題をどう解決するか定義する
	 */
	public MessageDto defineProductType(ProductTypeDto dto) {
		
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ProductType_may_not_be_set_to_null");
			}
			if(dto.getProductClass() == null) {
				throw new Exception("The_productClass_may_not_be_set_to_null");
			}
			//顧客タイプと関連させるのではなく事業ドメインと関連させるのでいらない。2022/05/25
			/*
			if(dto.getCustomerTypeId() == null) {
				throw new Exception("The_customerTypeId_may_not_be_set_to_null");
			}
			
			if(dto.getCustomerTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_customerTypeId");
			}
			*/
			if(dto.getProductTypeName() == null) {
				throw new Exception("The_productTypeName_may_not_be_set_to_null");
			}
			if(dto.getProductTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeName");
			}
			if(dto.getValueProposition() == null) {
				throw new Exception("The_valueProposition_may_not_be_set_to_null");
			}
			if(dto.getValueProposition().isEmpty()) {
				throw new Exception("Must_provide_a_valueProposition");
			}
			if(dto.getProductClass() == null) {
				throw new Exception("The_productClass_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//顧客タイプと関連させるのではなく事業ドメインと関連させるのでいらない。2022/05/25
			/*
			CustomerTypeDto customerType = companyService.findCustomerTypeById(dto.getCustomerTypeId());
			if(customerType == null) {
				throw new Exception("The_customerType_could_not_be_found");
			}
			*/
			
			//ビジネスロジック
			companyService.addProductType(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateProductType(ProductTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ProductType_may_not_be_set_to_null");
			}
			if(dto.getProductTypeId() == null) {
				throw new Exception("The_productTypeId_may_not_be_set_to_null");
			}
			//顧客タイプと関連させるのではなく事業ドメインと関連させるのでいらない。2022/05/25
			/*
			if(dto.getCustomerTypeId() == null) {
				throw new Exception("The_customerTypeId_may_not_be_set_to_null");
			}
			
			if(dto.getCustomerTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_customerTypeId");
			}
			*/
			if(dto.getProductTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeId");
			}
			if(dto.getProductClass() == null) {
				throw new Exception("The_productClass_may_not_be_set_to_null");
			}
			if(dto.getProductTypeName() == null) {
				throw new Exception("The_productTypeName_may_not_be_set_to_null");
			}
			if(dto.getProductTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_productTypeName");
			}
			if(dto.getValueProposition() == null) {
				throw new Exception("The_valueProposition_may_not_be_set_to_null");
			}
			if(dto.getValueProposition().isEmpty()) {
				throw new Exception("Must_provide_a_valueProposition");
			}
			if(dto.getProductClass() == null) {
				throw new Exception("The_productClass_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//顧客タイプと関連させるのではなく事業ドメインと関連させるのでいらない。2022/05/25
			/*
			CustomerTypeDto customerType = companyService.findCustomerTypeById(dto.getCustomerTypeId());
			if(customerType == null) {
				throw new Exception("The_customerType_could_not_be_found");
			}
			*/
			ProductTypeDto productType = companyService.findProductTypeById(dto.getProductTypeId());
			if(productType == null) {
				throw new Exception("The_productType_could_not_be_found");
			}
			//ビジネスロジック
			//製品カテゴリで使用されるなど製品タイプが使用されている場合、製品クラスは変更できない。
			if(productTypeService.isUsed(new ProductTypeId(dto.getProductTypeId()))) {
				if(!productType.getProductClass().equals(dto.getProductClass())) {
					throw new Exception("ProductClass_can_not_be_changed");
				}
			}
			companyService.updateProductType(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeProductType(String productTypeId) {
		MessageDto message = new MessageDto();
		try {
			if(productTypeId == null) {
				throw new Exception("The_productTypeId_may_not_be_set_to_null");
			}
			if(productTypeId.isEmpty()) {
				throw new Exception("Must_provide_a_productTypeId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			ProductTypeDto productType = companyService.findProductTypeById(productTypeId);
			if(productType == null) {
				throw new Exception("The_productType_could_not_be_found");
			}
			//ビジネスロジック
			//製品カテゴリで使用されるなど製品タイプが使用されている場合、削除することはできない。
			if(productTypeService.isUsed(new ProductTypeId(productTypeId))) {
				throw new Exception("The_ProductTyoe_can_not_be_removed");
			}
			companyService.deleteProductType(productTypeId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

}
