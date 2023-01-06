package com.culnou.mumu.company.domain.model.application.type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;

import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ApplicationTypeDto;

@Service
@Transactional
public class ApplicationTypeService {
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private ApplicationTypeRegistry registry;
	@Autowired
	private DeleteApplicationTypeChecker checker;
	
	public List<ApplicationTypeDto> findApplicationTypesOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_commentId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertApplicationTypes(registry.findApplicationTypesOfCompany(companyId));
	}
	
	public List<ApplicationTypeDto> findApplicationTypesOfBusinessDomain(String businessDomainId) throws Exception{
		if(businessDomainId == null) {
			throw new Exception("The_businessDomainId_may_not_be_set_to_null");
		}
		if(businessDomainId.isEmpty()) {
			throw new Exception("Must_provide_a_businessDomainId");
		}
		return this.convertApplicationTypes(registry.findApplicationTypesOfBusinessDomain(businessDomainId));
	}
	
	public List<ApplicationTypeDto> findApplicationTypesOfJob(String jobId) throws Exception{
		if(jobId == null) {
			throw new Exception("The_jobId_may_not_be_set_to_null");
		}
		if(jobId.isEmpty()) {
			throw new Exception("Must_provide_a_jobId");
		}
		return this.convertApplicationTypes(registry.findApplicationTypesOfJob(jobId));
	}
	
	public ApplicationTypeDto findApplicationTypesOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_applicationTypeId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_applicationTypeId");
		}
		return this.convertApplicationType(registry.findApplicationTypeOfId(id));
	}
	
	public MessageDto defineApplicationType(ApplicationTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ApplicationType_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeName() == null) {
				throw new Exception("The_applicationTypeName_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeName");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_commentId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//ビジネスロジック
			ApplicationType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setApplicationTypeId(registry.nextIdentity());
			entity.setCreatedAt(new Date());
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateApplicationType(ApplicationTypeDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_ApplicationType_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeName() == null) {
				throw new Exception("The_applicationTypeName_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeId() == null) {
				throw new Exception("The_applicationTypeId_may_not_be_set_to_null");
			}
			if(dto.getApplicationTypeName().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeName");
			}
			if(dto.getApplicationTypeId().isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeId");
			}
			if(dto.getCompanyId() == null) {
				throw new Exception("The_commentId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			ApplicationType result = registry.findApplicationTypeOfId(dto.getApplicationTypeId());
			if(result == null) {
				throw new Exception("The_ApplicationType_could_not_be_found");
			}
			//ビジネスロジック
			//必要な属性だけ更新する
			ApplicationType entity = this.convertFiancialAssetTypeDto(dto);
			entity.setApplicationTypeId(result.getApplicationTypeId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
			entity.setCreatedAt(dto.getCreatedAt());			
			entity.setUpdatedAt(new Date());
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeApplicationType(String applicationTypeId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(applicationTypeId == null) {
				throw new Exception("The_applicationTypeId_may_not_be_set_to_null");
			}
			if(applicationTypeId.isEmpty()) {
				throw new Exception("Must_provide_a_applicationTypeId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			ApplicationType result = registry.findApplicationTypeOfId(applicationTypeId);
			if(result == null) {
				throw new Exception("The_ApplicationType_could_not_be_found");
			}
			//アプリケーションタスクがあるタイプは削除できない。
			if(!checker.removable(applicationTypeId)) {
				throw new Exception("The_apptype_has_already_used_at_delete");
			}
			registry.remove(result);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	private ApplicationTypeDto convertApplicationType(ApplicationType entity) {
		ApplicationTypeDto dto = new ApplicationTypeDto();
		dto.setApplicationTypeId(entity.getApplicationTypeId().getApplicationTypeId());
		dto.setCompanyId(entity.getCompanyId().id());
		if(entity.getBusinessDomainId() != null) {
			dto.setBusinessDomainId(entity.getBusinessDomainId().businessDomainId());
		}
		if(entity.getBusinessDomainName() != null) {
			dto.setBusinessDomainName(entity.getBusinessDomainName());
		}
		if(entity.getJobId() != null) {
			dto.setJobId(entity.getJobId().jobId());
		}
		if(entity.getJobName() != null) {
			dto.setJobName(entity.getJobName());
		}
		dto.setApplicationTypeName(entity.getApplicationTypeName());
		dto.setApplicationTypeDescription(entity.getApplicationTypeDescription());
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setIndicators(entity.getIndicators());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<ApplicationTypeDto> convertApplicationTypes(List<ApplicationType> entities){
		List<ApplicationTypeDto> dtos = new ArrayList<>();
		for(ApplicationType entity : entities) {
			dtos.add(this.convertApplicationType(entity));
		}
		return dtos;
	}
	
	private ApplicationType convertFiancialAssetTypeDto(ApplicationTypeDto dto) {
		ApplicationType entity = new ApplicationType();
		if(dto.getApplicationTypeId() != null && !dto.getApplicationTypeId().isEmpty()) {
			entity.setApplicationTypeId(new ApplicationTypeId(dto.getApplicationTypeId()));
		}
		entity.setApplicationTypeName(dto.getApplicationTypeName());
		entity.setApplicationTypeDescription(dto.getApplicationTypeDescription());
		if(dto.getBusinessDomainId() != null && !dto.getBusinessDomainId().isEmpty()) {
			entity.setBusinessDomainId(new BusinessDomainId(dto.getBusinessDomainId()));
		}
		if(dto.getBusinessDomainName() != null && !dto.getBusinessDomainName().isEmpty()) {
			entity.setBusinessDomainName(dto.getBusinessDomainName());
		}
		if(dto.getJobId() != null && !dto.getJobId().isEmpty()) {
			entity.setJobId(new JobId(dto.getJobId()));
		}
		if(dto.getJobName() != null && !dto.getJobName().isEmpty()) {
			entity.setJobName(dto.getJobName());
		}
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
			entity.setUrl(new Url(dto.getUrl()));
		}
		entity.setIndicators(dto.getIndicators());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


}
