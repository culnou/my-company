package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.culnou.mumu.compnay.controller.MessageDto;


@Service
@Transactional
public class BusinessDomainService {
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("jobJpaRepository")
	@Autowired
	private JobRepository jobRepository;
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Qualifier("customerCategoryJpaRepository")
	@Autowired
	private CustomerCategoryRepository customerCategoryRepository;
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	@Qualifier("customerTypeJpaRepository")
	@Autowired
	private CustomerTypeRepository customerTypeRepository;
	@Qualifier("productTypeJpaRepository")
	@Autowired
	private ProductTypeRepository productTypeRepository;
	/*
	@Autowired
	private MemberTypeApplicationService memberTypeService;
	@Autowired
	private PartnerTypeApplicationService partnerTypeService;
	@Autowired
	private PlaceTypeService placeTypeService;
	@Autowired
	private DataTypeService dataTypeService;
	@Autowired
	private ApplicationTypeService applicationTypeService;
	@Autowired
	private FinancialAssetTypeService financialAssetTypeService;
	*/
	
	
	
	public boolean isUsed(BusinessDomainId businessDomainId) throws Exception{
		
		boolean result = false;
		//サインアップしたときに企業の事業ドメインを産業なしで自動作成するので、その場合を除くためbd.industry() != nullを追加した。2022/08/16
		BusinessDomain bd = businessDomainRepository.businessDomainOfId(businessDomainId);
		if(bd.industry() != null) {
			
			//事業ドメインが使用済かどうかで判断するように変更した。2023/2/4
			if(bd.isUsed()) {
				result = true;
			}
			
			
			/*
			//事業単位
			List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfDomain(businessDomainId);
			if(businessUnits.size() > 0) {
				result = true;
			}
			//ジョブ
			List<Job> jobs = jobRepository.jobsOfBusinessDomain(businessDomainId);
			if(jobs.size() > 0) {
				result = true;
			}
			//ビジネスプロセス
			List<BusinessProcess> businessProcesses = businessProcessRepository.businessProcessesOfBusinessDomain(businessDomainId);
			if(businessProcesses.size() > 0) {
				result = true;
			}
			//顧客タイプ
			List<CustomerType> customerTypes = customerTypeRepository.customerTypesOfBusinessDomain(businessDomainId);
			if(customerTypes.size() > 0) {
				result = true;
			}
			//製品タイプ
			List<ProductType> productTypes = productTypeRepository.productTypesOfBusinessDomain(businessDomainId);
			if(productTypes.size() > 0) {
				result = true;
			}
			//メンバータイプ
			List<MemberTypeInfoDto> memberTypes = memberTypeService.confirmyMemberTypeList(businessDomainId.businessDomainId);
			if(memberTypes.size() > 0) {
				result = true;
			}
			//パートナータイプ
			List<PartnerTypeDto> partnerTypes = partnerTypeService.confirmyPartnerTypeList(businessDomainId.businessDomainId);
			if(partnerTypes.size() > 0) {
				result = true;
			}
			//場所タイプ
			List<PlaceTypeDto> placeTypes = placeTypeService.findPlaceTypesOfBusinessDomain(businessDomainId.businessDomainId);
			if(placeTypes.size() > 0) {
				result = true;
			}
			//データタイプ
			List<DataTypeDto> dataTypes = dataTypeService.findDataTypesOfBusinessDomain(businessDomainId.businessDomainId);
			if(dataTypes.size() > 0) {
				result = true;
			}
			//アプリケーションタイプ
			List<ApplicationTypeDto> applicationTypes = applicationTypeService.findApplicationTypesOfBusinessDomain(businessDomainId.businessDomainId);
			if(applicationTypes.size() > 0) {
				result = true;
			}
			//財務資産タイプ
			List<FinancialAssetTypeDto> financialAssetTypes = financialAssetTypeService.findFinancialAssetTypesOfBusinessDomain(businessDomainId.businessDomainId);
			if(financialAssetTypes.size() > 0) {
				result = true;
			}
			*/
		}
		
		
		return result;
	}
	
	public boolean indicatorIsUsed(BusinessDomainId businessDomainId, Indicator indicator) throws Exception{
		boolean result = false;
		List<BusinessUnit> businessUnits = businessUnitRepository.businessUnitsOfDomain(businessDomainId);
		for(BusinessUnit businessUnit : businessUnits) {
			for(Goal goal : businessUnit.getGoals()) {
				if(goal.getIndicator().equals(indicator)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	public List<ProductCategory> findProductCategoriesByBusinessDomain(BusinessDomainId businessDomainId) throws Exception{
		BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(businessDomainId);
		List<AssociatedProductType> productTypes = businessDomain.getAssociatedProductTypes();
		List<ProductCategory> productCategories = new ArrayList<>();
		for(AssociatedProductType productType : productTypes) {
			List<ProductCategory> pcs = productCategoryRepository.productCategoriesOfProductType(new ProductTypeId(productType.getProductTypeId()));
			productCategories.addAll(pcs);
		}
		return productCategories;
	}
	
	public List<CustomerCategory> findCustomerCategoriesByBusinessDomain(BusinessDomainId businessDomainId) throws Exception{
		BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(businessDomainId);
		List<CustomerCategory> customerCategories = new ArrayList<>();
		List<CustomerCategory> ccs = customerCategoryRepository.customerCategoriesOfCustomerType(businessDomain.customerTypeId());
		customerCategories.addAll(ccs);
		return customerCategories;
	}
	
	public MessageDto defineBusinessDomainIndicator(String businessDomainId, Indicator indicator){
		MessageDto message = new MessageDto();
		try {
			if(businessDomainId == null || businessDomainId.isEmpty()) {
				throw new IllegalArgumentException("The_businessDomainId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			
			businessDomain.defineIndicator(indicator);
			businessDomainRepository.save(businessDomain);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto replaceBusinessDomainIndicator(String businessDomainId, Indicator pre, Indicator post){
		MessageDto message = new MessageDto();
		try {
			if(businessDomainId == null || businessDomainId.isEmpty()) {
				throw new IllegalArgumentException("The_businessDomainId_may_not_be_set_to_null");
			}
			if(pre == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			if(post == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			businessDomain.replaceIndicator(pre, post);
			businessDomainRepository.save(businessDomain);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
		
	}
	
	public MessageDto removeBusinessDomainIndicator(String businessDomainId, Indicator indicator){
		MessageDto message = new MessageDto();
		try {
			if(businessDomainId == null || businessDomainId.isEmpty()) {
				throw new IllegalArgumentException("The_businessDomainId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			businessDomain.removeIndicator(indicator);
			businessDomainRepository.save(businessDomain);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
		
		
	}
	
	

	
	
	

}
