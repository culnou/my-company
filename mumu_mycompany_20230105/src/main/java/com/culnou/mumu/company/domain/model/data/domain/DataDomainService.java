package com.culnou.mumu.company.domain.model.data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;

import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.DataDomainDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class DataDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private DataDomainRegistry registry;
	
	public List<DataDomainDto> findDataDomainsOfCompany(String companyId) throws Exception{
		if(companyId == null) {
			throw new Exception("The_commentId_may_not_be_set_to_null");
		}
		if(companyId.isEmpty()) {
			throw new Exception("Must_provide_a_companyId");
		}
		return this.convertDataDomains(registry.findDataDomainsOfCompany(companyId));
	}
	
	
	public DataDomainDto findDataDomainsOfId(String id) throws Exception{
		if(id == null) {
			throw new Exception("The_dataDomainId_may_not_be_set_to_null");
		}
		if(id.isEmpty()) {
			throw new Exception("Must_provide_a_dataDomainId");
		}
		return this.convertDataDomain(registry.findDataDomainOfId(id));
	}
	
	public MessageDto defineDataDomain(DataDomainDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_DataDomain_may_not_be_set_to_null");
			}
			if(dto.getDataDomainName() == null) {
				throw new Exception("The_dataDomainName_may_not_be_set_to_null");
			}
			if(dto.getDataDomainName().isEmpty()) {
				throw new Exception("Must_provide_a_dataDomainName");
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
			DataDomain entity = this.convertFiancialAssetDomainDto(dto);
			entity.setDataDomainId(registry.nextIdentity());
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
	
	public MessageDto updateDataDomain(DataDomainDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_DataDomain_may_not_be_set_to_null");
			}
			if(dto.getDataDomainName() == null) {
				throw new Exception("The_dataDomainName_may_not_be_set_to_null");
			}
			if(dto.getDataDomainId() == null) {
				throw new Exception("The_dataDomainId_may_not_be_set_to_null");
			}
			if(dto.getDataDomainName().isEmpty()) {
				throw new Exception("Must_provide_a_dataDomainName");
			}
			if(dto.getDataDomainId().isEmpty()) {
				throw new Exception("Must_provide_a_dataDomainId");
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
			DataDomain result = registry.findDataDomainOfId(dto.getDataDomainId());
			if(result == null) {
				throw new Exception("The_DataDomain_could_not_be_found");
			}
			//ビジネスロジック
			//必要な属性だけ更新する
			DataDomain entity = this.convertFiancialAssetDomainDto(dto);
			entity.setDataDomainId(result.getDataDomainId());
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
	
	public MessageDto removeDataDomain(String dataDomainId) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dataDomainId == null) {
				throw new Exception("The_dataDomainId_may_not_be_set_to_null");
			}
			if(dataDomainId.isEmpty()) {
				throw new Exception("Must_provide_a_dataDomainId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			DataDomain result = registry.findDataDomainOfId(dataDomainId);
			if(result == null) {
				throw new Exception("The_DataDomain_could_not_be_found");
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
	
	
	private DataDomainDto convertDataDomain(DataDomain entity) {
		DataDomainDto dto = new DataDomainDto();
		dto.setDataDomainId(entity.getDataDomainId().getDataDomainId());
		dto.setCompanyId(entity.getCompanyId().id());
		
		dto.setDataDomainName(entity.getDataDomainName());
		dto.setDataDomainDescription(entity.getDataDomainDescription());
		
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<DataDomainDto> convertDataDomains(List<DataDomain> entities){
		List<DataDomainDto> dtos = new ArrayList<>();
		for(DataDomain entity : entities) {
			dtos.add(this.convertDataDomain(entity));
		}
		return dtos;
	}
	
	private DataDomain convertFiancialAssetDomainDto(DataDomainDto dto) {
		DataDomain entity = new DataDomain();
		if(dto.getDataDomainId() != null && !dto.getDataDomainId().isEmpty()) {
			entity.setDataDomainId(new DataDomainId(dto.getDataDomainId()));
		}
		entity.setDataDomainName(dto.getDataDomainName());
		entity.setDataDomainDescription(dto.getDataDomainDescription());
		
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		
		if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
			entity.setUrl(new Url(dto.getUrl()));
		}
		
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


	
	

}
