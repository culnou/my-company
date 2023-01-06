package com.culnou.mumu.company.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.ApplicationCategoryApplicationService;
import com.culnou.mumu.company.application.DataCategoryApplicationService;
import com.culnou.mumu.company.application.FinancialAssetCategoryApplicationService;
import com.culnou.mumu.company.application.MemberTypeApplicationService;
import com.culnou.mumu.company.application.PartnerCategoryApplicationService;
import com.culnou.mumu.company.application.PlaceCategoryApplicationService;
import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.ActionPlanRepository;
import com.culnou.mumu.company.domain.model.AssociatedCustomerCategory;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;
import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.BusinessProcess;
import com.culnou.mumu.company.domain.model.BusinessProcessRepository;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.model.CustomerCategory;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerCategoryRepository;
import com.culnou.mumu.company.domain.model.CustomerType;
import com.culnou.mumu.company.domain.model.CustomerTypeRepository;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.ProductCategory;
import com.culnou.mumu.company.domain.model.ProductCategoryId;
import com.culnou.mumu.company.domain.model.ProductCategoryRepository;
import com.culnou.mumu.company.domain.model.ProductType;
import com.culnou.mumu.company.domain.model.ProductTypeRepository;
import com.culnou.mumu.company.domain.model.activity.action_plan.AssociatedActionPlan;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeService;
import com.culnou.mumu.company.domain.model.application.type.AssociatedApplicationType;
import com.culnou.mumu.company.domain.model.data.category.AssociatedDataCategory;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;
import com.culnou.mumu.company.domain.model.data.type.DataTypeService;
import com.culnou.mumu.company.domain.model.financial.asset.category.AssociatedFinancialAssetCategory;
import com.culnou.mumu.company.domain.model.financial.asset.type.AssociatedFinancialAssetType;
import com.culnou.mumu.company.domain.model.financial.asset.type.FinancialAssetTypeService;
import com.culnou.mumu.company.domain.model.member.category.AssociatedMemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryRegistry;
import com.culnou.mumu.company.domain.model.member.type.AssociatedMemberType;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.company.domain.model.partner.type.AssociatedPartnerType;
import com.culnou.mumu.company.domain.model.partner.type.PartnerType;
import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeRegistry;
import com.culnou.mumu.company.domain.model.place.category.AssociatedPlaceCategory;
import com.culnou.mumu.company.domain.model.place.type.AssociatedPlaceType;
import com.culnou.mumu.company.domain.model.place.type.PlaceTypeService;
import com.culnou.mumu.company.domain.model.program.ProgramService;
import com.culnou.mumu.compnay.controller.ApplicationCategoryDto;
import com.culnou.mumu.compnay.controller.ApplicationTypeDto;
import com.culnou.mumu.compnay.controller.DataCategoryDto;
import com.culnou.mumu.compnay.controller.DataTypeDto;
import com.culnou.mumu.compnay.controller.FinancialAssetCategoryDto;
import com.culnou.mumu.compnay.controller.FinancialAssetTypeDto;
import com.culnou.mumu.compnay.controller.MemberTypeInfoDto;
import com.culnou.mumu.compnay.controller.PartnerCategoryDto;
import com.culnou.mumu.compnay.controller.PlaceCategoryDto;
import com.culnou.mumu.compnay.controller.PlaceTypeDto;
import com.culnou.mumu.compnay.controller.ProgramDto;

@Service
@Transactional
public class FindProgramIndicator {
	@Autowired
	private ProgramService programService;
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	
	@Qualifier("customerTypeJpaRepository")
	@Autowired
	private CustomerTypeRepository customerTypeRepository;
	
	@Qualifier("productTypeJpaRepository")
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Qualifier("customerCategoryJpaRepository")
	@Autowired
	private CustomerCategoryRepository customerCategoryRepository;
	
	@Qualifier("productCategoryJpaRepository")
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	
	@Autowired
	private MemberCategoryRegistry memberCategoryRegistry;
	@Autowired
	private MemberTypeApplicationService memberTypeService;
	
	
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	
	@Autowired
	private PartnerCategoryApplicationService partnerCategoryRepository;
	
	@Autowired
	private PartnerTypeRegistry partnerTypeRepository;
	
	@Autowired
	private PlaceCategoryApplicationService placeCategoryRepository;
	
	@Autowired
	private PlaceTypeService placeTypeRepository;
	
	@Autowired
	private DataCategoryApplicationService dataCategoryRepository;
	
	@Autowired
	private DataTypeService dataTypeRepository;
	
	@Autowired
	private ApplicationCategoryApplicationService applicationCategoryRepository;
	
	@Autowired
	private ApplicationTypeService applicationTypeRepository;
	
	@Autowired
	private FinancialAssetCategoryApplicationService financialAssetCategoryRepository;
	
	@Autowired
	private FinancialAssetTypeService financialAssetTypeRepository;
	
	
	public List<Indicator> findIndicator(String programId, IndicatorType indicatorType) throws Exception{
		List<Indicator> indicators = new ArrayList<>();
		if(programId == null || programId.isEmpty()) {
			throw new IllegalArgumentException("The_programId_may_not_be_set_to_null");
		}
		ProgramDto program = programService.findProgramDtoOfId(programId);
		if(indicatorType.equals(IndicatorType.Business)) {
			
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(program.getBusinessUnitId()));
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(businessUnit.businessDomainId());
			indicators.addAll(businessDomain.getIndicators());
		}else if(indicatorType.equals(IndicatorType.Customer)) {
			List<CustomerType> customerTypes = new ArrayList<>();
			for(AssociatedCustomerCategory customerCategory : program.getAssociatedCustomerCategories()) {
				CustomerCategory custCat = customerCategoryRepository.customerCategoryOfId(new CustomerCategoryId(customerCategory.getCustomerCategoryId()));
				CustomerType customerType = customerTypeRepository.customerTypeOfId(custCat.customerTypeId());
				boolean check = true;
				//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
				for(CustomerType custType : customerTypes) {
					if(customerType.customerTypeId().equals(custType.customerTypeId())) {
						check = false;
					}
				}//for
				if(check) {
					customerTypes.add(customerType);
				}
			}//for
			for(CustomerType customerType : customerTypes) {
				indicators.addAll(customerType.getIndicators());
			}//for
		}else if(indicatorType.equals(IndicatorType.Product)) {
			List<ProductType> productTypes = new ArrayList<>();
			for(AssociatedProductCategory productCategory : program.getAssociatedProductCategories()) {
				ProductCategory custCat = productCategoryRepository.productCategoryOfId(new ProductCategoryId(productCategory.getProductCategoryId()));
				ProductType productType = productTypeRepository.productTypeOfId(custCat.productTypeId());
				boolean check = true;
				//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
				for(ProductType custType : productTypes) {
					if(productType.productTypeId().equals(custType.productTypeId())) {
						check = false;
					}
				}//for
				if(check) {
					productTypes.add(productType);
				}
			}//for
			for(ProductType productType : productTypes) {
				indicators.addAll(productType.getIndicators());
			}//for
		}else if(indicatorType.equals(IndicatorType.Member)) {
			List<MemberTypeInfoDto> memberTypes = new ArrayList<>();
			for(AssociatedMemberCategory memberCategory : program.getAssociatedMemberCategories()) {
				MemberCategory custCat = memberCategoryRegistry.findMemberCategoryOfId(memberCategory.getMemberCategoryId());
				List<AssociatedMemberType> types = custCat.getAssociatedMemberTypes();
				for(AssociatedMemberType type : types) {
					MemberTypeInfoDto memberType = memberTypeService.findMemberTypeOfId(type.getMemberTypeId());
					//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
					boolean check = true;
					for(MemberTypeInfoDto custType : memberTypes) {
						if(memberType.getMemberTypeId().equals(custType.getMemberTypeId())) {
							check = false;
						}
					}//for
					if(check) {
						memberTypes.add(memberType);
					}//if
					
				}//for
			}//for
			for(MemberTypeInfoDto memberType : memberTypes) {
				indicators.addAll(memberType.getIndicators());
			}//for
		}else if(indicatorType.equals(IndicatorType.Partner)) {
			List<PartnerType> partnerTypes = new ArrayList<>();
			for(AssociatedPartnerCategory partnerCategory : program.getAssociatedPartnerCategories()) {
				PartnerCategoryDto custCat = partnerCategoryRepository.findPartnerCategoryOfId(partnerCategory.getPartnerCategoryId());
				List<AssociatedPartnerType> types = custCat.getAssociatedPartnerTypes();
				for(AssociatedPartnerType type : types) {
					PartnerType partnerType = partnerTypeRepository.findPartnerTypeOfId(type.getPartnerTypeId());		
					boolean check = true;
					//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
					for(PartnerType custType : partnerTypes) {
						if(partnerType.getPartnerTypeId().equals(custType.getPartnerTypeId())) {
							check = false;
						}
					}//for
					if(check) {
						partnerTypes.add(partnerType);
					}
				}//for
			}//for
			for(PartnerType partnerType : partnerTypes) {
				indicators.addAll(partnerType.getIndicators());
			}//for

			
		}else if(indicatorType.equals(IndicatorType.Place)) {
			List<PlaceTypeDto> placeTypes = new ArrayList<>();
			for(AssociatedPlaceCategory placeCategory : program.getAssociatedPlaceCategories()) {
				PlaceCategoryDto custCat = placeCategoryRepository.findPlaceCategoryOfId(placeCategory.getPlaceCategoryId());
				List<AssociatedPlaceType> types = custCat.getAssociatedPlaceTypes();
				for(AssociatedPlaceType type : types) {
					PlaceTypeDto placeType = placeTypeRepository.findPlaceTypesOfId(type.getPlaceTypeId());			
					boolean check = true;
					//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
					for(PlaceTypeDto custType : placeTypes) {
						if(placeType.getPlaceTypeId().equals(custType.getPlaceTypeId())) {
							check = false;
						}
					}//for
					if(check) {
						placeTypes.add(placeType);
					}
					
				}//for
			}//for
			for(PlaceTypeDto placeType : placeTypes) {
				indicators.addAll(placeType.getIndicators());
			}//for

			
		}else if(indicatorType.equals(IndicatorType.Data)) {
			List<DataTypeDto> dataTypes = new ArrayList<>();
			for(AssociatedDataCategory dataCategory : program.getAssociatedDataCategories()) {
				DataCategoryDto custCat = dataCategoryRepository.findDataCategoryOfId(dataCategory.getDataCategoryId());
				List<AssociatedDataType> types = custCat.getAssociatedDataTypes();
				for(AssociatedDataType type : types) {
					DataTypeDto dataType = dataTypeRepository.findDataTypesOfId(type.getDataTypeId());			
					boolean check = true;
					//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
					for(DataTypeDto custType : dataTypes) {
						if(dataType.getDataTypeId().equals(custType.getDataTypeId())) {
							check = false;
						}
					}//for
					if(check) {
						dataTypes.add(dataType);
					}
				}//for
			}//for
			for(DataTypeDto dataType : dataTypes) {
				indicators.addAll(dataType.getIndicators());
			}//for
			
		}else if(indicatorType.equals(IndicatorType.Application)) {
			List<ApplicationTypeDto> applicationTypes = new ArrayList<>();
			for(AssociatedApplicationCategory applicationCategory : program.getAssociatedApplicationCategories()) {
				ApplicationCategoryDto custCat = applicationCategoryRepository.findApplicationCategoryOfId(applicationCategory.getApplicationCategoryId());
				List<AssociatedApplicationType> types = custCat.getAssociatedApplicationTypes();
				for(AssociatedApplicationType type : types) {
					ApplicationTypeDto applicationType = applicationTypeRepository.findApplicationTypesOfId(type.getApplicationTypeId());			
					boolean check = true;
					//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
					for(ApplicationTypeDto custType : applicationTypes) {
						if(applicationType.getApplicationTypeId().equals(custType.getApplicationTypeId())) {
							check = false;
						}
					}//for
					if(check) {
						applicationTypes.add(applicationType);
					}
				}//for
				
				
			}//for
			for(ApplicationTypeDto applicationType : applicationTypes) {
				indicators.addAll(applicationType.getIndicators());
			}//for
			
		}else if(indicatorType.equals(IndicatorType.FinancialAsset)) {
			List<FinancialAssetTypeDto> financialAssetTypes = new ArrayList<>();
			for(AssociatedFinancialAssetCategory financialAssetCategory : program.getAssociatedFinancialAssetCategories()) {
				FinancialAssetCategoryDto custCat = financialAssetCategoryRepository.findFinancialAssetCategoryOfId(financialAssetCategory.getFinancialAssetCategoryId());
				List<AssociatedFinancialAssetType> types = custCat.getAssociatedFinancialAssetTypes();
				for(AssociatedFinancialAssetType type : types) {
					FinancialAssetTypeDto financialAssetType = financialAssetTypeRepository.findFinancialAssetTypesOfId(type.getFinancialAssetTypeId());			
					boolean check = true;
					//異なる顧客カテゴリで同じ顧客タイプがある場合は、それを除く。
					for(FinancialAssetTypeDto custType : financialAssetTypes) {
						if(financialAssetType.getFinancialAssetTypeId().equals(custType.getFinancialAssetTypeId())) {
							check = false;
						}
					}//for
					if(check) {
						financialAssetTypes.add(financialAssetType);
					}
				}//for
			}//for
			for(FinancialAssetTypeDto financialAssetType : financialAssetTypes) {
				indicators.addAll(financialAssetType.getIndicators());
			}//for
		}else if(indicatorType.equals(IndicatorType.ActionPlan)) {
			List<BusinessProcess> businessProcesses = new ArrayList<>();
			for(AssociatedActionPlan actionPlan : program.getAssociatedActionPlans()) {
				ActionPlan actPlan = actionPlanRepository.actionPlanOfId(new ActionPlanId(actionPlan.getActionPlanId()));
				BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(actPlan.getBusinessProcessId());
				boolean check = true;
				for(BusinessProcess busPro : businessProcesses) {
					if(busPro.getBusinessProcessId().equals(businessProcess.getBusinessProcessId())) {
						check = false;
					}
				}//for
				if(check) {
					businessProcesses.add(businessProcess);
				}
			}//for
			for(BusinessProcess businessProcess : businessProcesses) {
				indicators.addAll(businessProcess.getIndicators());
			}//for
			
		}
		
		return indicators;
	}

}
