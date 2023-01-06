package com.culnou.mumu.company.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryRepository;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunction;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunctionId;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunctionRegistry;
import com.culnou.mumu.company.domain.service.PartnerFunctionChecker;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.PartnerFunctionDto;

@Service
@Transactional
public class PartnerFunctionApplicationService {
	@Autowired
	private PartnerFunctionRegistry registry;
	
	@Qualifier("partnerCategoryJpaRepository")
	@Autowired
	private PartnerCategoryRepository partnerCategoryRepository;
	@Autowired
	private PartnerFunctionChecker checker;
	
	public MessageDto addPartnerFunction(PartnerFunctionDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getPartnerFunctionName() == null || dto.getPartnerFunctionName().isEmpty()) {
				throw new IllegalArgumentException("The_partnerFunctionName_may_not_be_set_to_null");
			}
			if(dto.getPartnerCategoryId() == null || dto.getPartnerCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
			}
			if(dto.getPartnerCategoryName() == null || dto.getPartnerCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_partnerCategoryName_may_not_be_set_to_null");
			}
			/*
			if(dto.getTaskId() == null || dto.getTaskId().isEmpty()) {
				throw new IllegalArgumentException("The_partnerTaskId_may_not_be_set_to_null");
			}
			if(dto.getTaskName() == null || dto.getTaskName().isEmpty()) {
				throw new IllegalArgumentException("The_partnerTaskName_may_not_be_set_to_null");
			}
			*/
			PartnerCategory partnerCategory = partnerCategoryRepository.partnerCategoryOfId(new PartnerCategoryId(dto.getPartnerCategoryId()));
			if(partnerCategory == null) {
				throw new IllegalArgumentException("The_partnerCategory_is_not_exist");
			}
			PartnerFunctionId id = registry.nextIdentity();
			PartnerFunction function = partnerCategory.definePartnerFunction(id, dto.getPartnerFunctionName());
			//function.setTaskId(new TaskId(dto.getTaskId()));
			//function.setTaskName(dto.getTaskName());
			if(dto.getPartnerFunctionDescription() != null && !dto.getPartnerFunctionDescription().isEmpty()) {
				function.setPartnerFunctionDescription(dto.getPartnerFunctionDescription());
			}
			if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
				function.setUrl(new Url(dto.getUrl()));
			}
			message.setReturnValue(id.getPartnerFunctionId());
			function.setCreatedAt(new Date());
			registry.save(function);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updatePartnerFunction(PartnerFunctionDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto.getPartnerFunctionId() == null || dto.getPartnerFunctionId().isEmpty()) {
				throw new IllegalArgumentException("The_partnerFunctionId_may_not_be_set_to_null");
			}
			PartnerFunction function = registry.partnerFunctionOfId(new PartnerFunctionId(dto.getPartnerFunctionId()));
			if(function == null) {
				throw new IllegalArgumentException("The_partnerFunction_dose_not_exist");
			}
			if(dto.getPartnerFunctionName() != null && !dto.getPartnerFunctionName().isEmpty()) {
				function.setPartnerFunctionName(dto.getPartnerFunctionName());
			}
			if(dto.getPartnerFunctionDescription() != null && !dto.getPartnerFunctionDescription().isEmpty()) {
				function.setPartnerFunctionDescription(dto.getPartnerFunctionDescription());
			}
			/*
			if(dto.getTaskId() != null && !dto.getTaskId().isEmpty()) {
				function.setTaskId(new TaskId(dto.getTaskId()));
			}
			if(dto.getTaskName() != null && !dto.getTaskName().isEmpty()) {
				function.setTaskName(dto.getTaskName());
			}
			*/
			if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
				function.setUrl(new Url(dto.getUrl()));
			}else {
				
				function.setUrl(null);
			}
			//編集・削除チェック。
			if(!checker.avarable(dto.getPartnerFunctionId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getPartnerFunctionId()));
			}
			function.setUpdatedAt(new Date());
			registry.save(function);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removePartnerFunction(String partnerFunctionId) {
		MessageDto message = new MessageDto();
		try {
			if(partnerFunctionId == null || partnerFunctionId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerFunctionId_may_not_be_set_to_null");
			}
			PartnerFunction function = registry.partnerFunctionOfId(new PartnerFunctionId(partnerFunctionId));
			if(function == null) {
				throw new IllegalArgumentException("The_partnerFunction_dose_not_exist");
			}
			//編集・削除チェック。
			if(!checker.avarable(partnerFunctionId).equals("OK")) {
				throw new Exception(checker.avarable(partnerFunctionId));
			}
			registry.remove(function);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public PartnerFunctionDto findPartnerFunctionOfId(String partnerFunctionId) throws Exception{
		if(partnerFunctionId == null || partnerFunctionId.isEmpty()) {
			throw new IllegalArgumentException("The_partnerFunctionId_may_not_be_set_to_null");
		}
		PartnerFunction function = registry.partnerFunctionOfId(new PartnerFunctionId(partnerFunctionId));
		return this.convertEntityToDto(function);
	}
	
	public List<PartnerFunctionDto> findPartnerFunctionsOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		return this.convertDtos(registry.partnerFunctionsOfCompany(new CompanyId(companyId)));
	}
	
	public List<PartnerFunctionDto> findPartnerFunctionsOfPartnerCategory(String partnerCategoryId) throws Exception{
		if(partnerCategoryId == null || partnerCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_partnerCategoryId_may_not_be_set_to_null");
		}
		return this.convertDtos(registry.partnerFunctionsOfPartnerCategory(new PartnerCategoryId(partnerCategoryId)));
	}
	
	public List<PartnerFunctionDto> findPartnerFunctionsOfPartnerTask(String taskId) throws Exception{
		if(taskId == null || taskId.isEmpty()) {
			throw new IllegalArgumentException("The_partnerTaskId_may_not_be_set_to_null");
		}
		return this.convertDtos(registry.partnerFunctionsOfPartnerTask(new TaskId(taskId)));
	}
	
	
	
	/*
	 * 変換処理
	 */
	private PartnerFunctionDto convertEntityToDto(PartnerFunction entity) {
		PartnerFunctionDto pojo = new PartnerFunctionDto();
		if(entity.getPartnerFunctionId() != null) {
			pojo.setPartnerFunctionId(entity.getPartnerFunctionId().getPartnerFunctionId());
		}
		if(entity.getCompanyId() != null) {
			pojo.setCompanyId(entity.getCompanyId().id());
		}
		if(entity.getPartnerCategoryId() != null) {
			pojo.setPartnerCategoryId(entity.getPartnerCategoryId().getPartnerCategoryId());
		}
		if(entity.getPartnerCategoryName() != null) {
			pojo.setPartnerCategoryName(entity.getPartnerCategoryName());
		}
		if(entity.getTaskId() != null) {
			pojo.setTaskId(entity.getTaskId().taskId());
		}
		if(entity.getTaskName() != null) {
			pojo.setTaskName(entity.getTaskName());
		}
		if(entity.getPartnerFunctionName() != null) {
			pojo.setPartnerFunctionName(entity.getPartnerFunctionName());
		}
		if(entity.getPartnerFunctionDescription() != null) {
			pojo.setPartnerFunctionDescription(entity.getPartnerFunctionDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl().getUrl());
		}
		
		
		
		return pojo;
	}
	
	private List<PartnerFunctionDto> convertDtos(List<PartnerFunction> functions) {
		List<PartnerFunctionDto> dtos = new ArrayList<>();
		for(PartnerFunction function : functions) {
			dtos.add(this.convertEntityToDto(function));
		}
		return dtos;
	}
}
