package com.culnou.mumu.company.domain.model.product.category;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductCategoryRepository;
import com.culnou.mumu.company.domain.model.ProductCategoryService;
import com.culnou.mumu.company.domain.model.ProductClass;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.domain.model.common.Personality;

import com.culnou.mumu.company.domain.service.ProductCategoryChecker;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductCategoryDto;

@Service
@Transactional
public class ProductCategoryDomainService {
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Autowired
	ProductCategoryChecker checker;
	
	public MessageDto defineProductCategory(ProductCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ProductCategory_may_not_be_set_to_null");
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
			if(dto.getProductCategoryName() == null) {
				throw new Exception("The_ProductCategoryName_may_not_be_set_to_null");
			}
			if(dto.getProductCategoryName().isEmpty()) {
				throw new Exception("Must_provide_a_productCategoryName");
			}
			if(dto.getProductClass() == null) {
				throw new Exception("The_productClass_may_not_be_set_to_null");
			}
			//製品が商品の場合、商品は必須。
			if(dto.getProductClass().equals(ProductClass.Goods)) {
				if(dto.getGoodses().size() == 0) {
					throw new Exception("The_goodes_may_not_be_set_to_null");
				}
			//製品がサービスの場合、サービスは必須。
			}else {
				if(dto.getService() == null) {
					throw new Exception("The_service_may_not_be_set_to_null");
				}
			}
			//人格は必須
			if(dto.getPersonality() == null) {
				throw new Exception("The_personality_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//ビジネスロジック
			//製品カテゴリの顧客が会社の場合
			if(!dto.getPersonality().equals(Personality.Person)) {
				//産業は必須。
				//すべての産業が対象の事業の場合もあるので任意にする。2022/9/25
				/*
				if(dto.getIndustryId() == null) {
					throw new Exception("The_industryId_may_not_be_set_to_null");
				}
				if(dto.getIndustryId().isEmpty()) {
					throw new Exception("Must_provide_a_industryId");
				}
				*/
				//さらにサービスの場
				if(!dto.getProductClass().equals(ProductClass.Goods)) {
					//職務は必須。
					if(dto.getRoles().size() == 0) {
						throw new Exception("The_role_may_not_be_set_to_null");
					}
				}
			
			}
			companyService.addProductCategory(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}
	
	public MessageDto updateProductCategory(ProductCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ProductCategory_may_not_be_set_to_null");
			}
			if(dto.getProductCategoryId() == null) {
				throw new Exception("The_ProductCategoryId_may_not_be_set_to_null");
			}
			if(dto.getProductCategoryId().isEmpty()) {
				throw new Exception("Must_provide_a_productCategoryId");
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
			if(dto.getProductCategoryName() == null) {
				throw new Exception("The_ProductCategoryName_may_not_be_set_to_null");
			}
			if(dto.getProductCategoryName().isEmpty()) {
				throw new Exception("Must_provide_a_productCategoryName");
			}
			if(dto.getProductClass() == null) {
				throw new Exception("The_productClass_may_not_be_set_to_null");
			}
			//製品が商品の場合、商品は必須。
			if(dto.getProductClass().equals(ProductClass.Goods)) {
				if(dto.getGoodses().size() == 0) {
					throw new Exception("The_goodes_may_not_be_set_to_null");
				}
			//製品がサービスの場合、サービスは必須。
			}else {
				if(dto.getService() == null) {
					throw new Exception("The_service_may_not_be_set_to_null");
				}
			}
			//人格は必須
			if(dto.getPersonality() == null) {
				throw new Exception("The_personality_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ProductCategoryDto productCategory = companyService.findProductCategoryById(dto.getProductCategoryId());
			if(productCategory == null) {
				throw new Exception("The_productCategory_could_not_be_found");
			}
			//ビジネスロジック
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(dto.getProductCategoryId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getProductCategoryId()));
			}
			//製品カテゴリが使用されている場合、製品クラスを変更することはできない。
			if(productCategoryService.isUsed(new ProductCategoryId(dto.getProductCategoryId()))) {
				if(!productCategory.getProductClass().equals(dto.getProductClass())) {
					throw new Exception("ProductClass_can_not_be_changed");
				}
			}
			//製品カテゴリの顧客が会社の場合
			if(!dto.getPersonality().equals(Personality.Person)) {
				//産業は必須。
				//すべての産業が対象の事業の場合もあるので任意にする。2022/9/25
				/*
				if(dto.getIndustryId() == null) {
					throw new Exception("The_industryId_may_not_be_set_to_null");
				}
				if(dto.getIndustryId().isEmpty()) {
					throw new Exception("Must_provide_a_industryId");
				}
				*/
				//さらにサービスの場合
				if(!dto.getProductClass().equals(ProductClass.Goods)) {
					//職務は必須。
					if(dto.getRoles().size() == 0) {
						throw new Exception("The_role_may_not_be_set_to_null");
					}
				}
			
			}
			companyService.updateProductCategory(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}
	
	public MessageDto deleteProductCategory(String productCategoryId) {
		
		MessageDto message = new MessageDto();
		try {
			if(productCategoryId == null) {
				throw new Exception("The_ProductCategoryId_may_not_be_set_to_null");
			}
			if(productCategoryId.isEmpty()) {
				throw new Exception("Must_provide_a_productCategoryId");
			}
			ProductCategoryDto productCategory = companyService.findProductCategoryById(productCategoryId);
			if(productCategory == null) {
				throw new Exception("The_productCategory_could_not_be_found");
			}
			if(productCategoryService.isUsed(new ProductCategoryId(productCategoryId))) {
				throw new Exception("The_ProductCategorycan_not_be_removed");
			}
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(productCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(productCategoryId));
			}
			companyService.deleteProductCategory(productCategoryId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		
		return message;
	}
	
	

}
