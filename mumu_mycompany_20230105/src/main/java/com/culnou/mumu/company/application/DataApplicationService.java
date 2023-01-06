package com.culnou.mumu.company.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryRepository;
import com.culnou.mumu.company.domain.model.data.instance.AddDataChecker;
import com.culnou.mumu.company.domain.model.data.instance.Data;
import com.culnou.mumu.company.domain.model.data.instance.DataId;
import com.culnou.mumu.company.domain.model.data.instance.DataRegistory;

import com.culnou.mumu.compnay.controller.DataDto;
import com.culnou.mumu.compnay.controller.MessageDto;
@Service
@Transactional
public class DataApplicationService {
	@Autowired
	private DataRegistory registry;
	@Autowired
	private AddDataChecker addDataChecker;
	@Qualifier("dataCategoryJpaRepository")
	@Autowired
	private DataCategoryRepository repository;
	
	public MessageDto addData(DataDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getWorkId() == null || dto.getWorkId().isEmpty()) {
				throw new IllegalArgumentException("The_workId_may_not_be_set_to_null");
			}
			if(dto.getDataCategoryId() == null || dto.getDataCategoryId().isEmpty()) {
				throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
			}
			if(dto.getDataName() == null || dto.getDataName().isEmpty()) {
				throw new IllegalArgumentException("The_dataName_may_not_be_set_to_null");
			}
			
			if(!addDataChecker.addable(dto.getDataCategoryId())) {
				throw new IllegalArgumentException("data_category_avarable_error");
			}
			DataId dataId = registry.nextIdentity();
			Data data = new Data(new CompanyId(dto.getCompanyId()), new DataCategoryId(dto.getDataCategoryId()), new WorkId(dto.getWorkId()), dataId, dto.getDataName());
			data.setDataDescription(dto.getDataDescription());
			data.setDataCategoryName(dto.getDataCategoryName());
			if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
				data.setUrl(new Url(dto.getUrl()));
			}
			data.setStartDate(dto.getStartDate());
			data.setEndDate(dto.getEndDate());
			data.setCreatedAt(new Date());			
			registry.save(data);
			message.setResult(data.getDataId().getDataId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateData(DataDto dto) {
		MessageDto message = new MessageDto();
		try {
			if(dto.getDataId() == null || dto.getDataId().isEmpty()) {
				throw new IllegalArgumentException("The_dataId_may_not_be_set_to_null");
			}
			Data data = registry.findDataOfId(dto.getDataId());
			if(data == null) {
				throw new IllegalArgumentException("The_data_dose_not_exist");
			}
			if(dto.getDataCategoryId() != null || dto.getDataCategoryId().isEmpty()) {
				data.setDataCategoryId(new DataCategoryId(dto.getDataCategoryId()));			
			}
			data.setDataDescription(dto.getDataDescription());
			if(dto.getUrl() != null && !dto.getUrl().isEmpty()) {
				data.setUrl(new Url(dto.getUrl()));
			}else {
				data.setUrl(null);
			}
			data.setDataName(dto.getDataName());
			data.setStartDate(dto.getStartDate());
			data.setDataCategoryName(dto.getDataCategoryName());
			data.setEndDate(dto.getEndDate());
			data.setUpdatedAt(new Date());
			registry.save(data);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeData(String dataId) {
		MessageDto message = new MessageDto();
		
		try {
			if(dataId == null || dataId.isEmpty()) {
				throw new IllegalArgumentException("The_dataId_may_not_be_set_to_null");
			}
			Data data = registry.findDataOfId(dataId);
			if(data == null) {
				throw new IllegalArgumentException("The_data_dose_not_exist");
			}
			registry.remove(data);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public DataDto findDataOfId(String dataId) throws Exception{
		return this.convertData(this.registry.findDataOfId(dataId));
	}
	
	public List<DataDto> findDataOfDataCategory(String dataCategoryId) throws Exception{
		List<DataDto> dtos = new ArrayList<>();
		List<Data> entities = registry.findDataOfDataCategory(dataCategoryId);
		for(Data entity : entities) {
			dtos.add(this.convertData(entity));
		}
		return dtos;
	}
	
	public List<DataDto> findDataOfWork(String workId) throws Exception{
		List<DataDto> dtos = new ArrayList<>();
		List<Data> entities = registry.findDataOfWork(workId);
		for(Data entity : entities) {
			dtos.add(this.convertData(entity));
		}
		return dtos;
	}
	
	public List<DataDto> findDataOfCompany(String companyId) throws Exception{
		List<DataDto> dtos = new ArrayList<>();
		List<Data> entities = registry.findDataOfCompany(companyId);
		for(Data entity : entities) {
			dtos.add(this.convertData(entity));
		}
		return dtos;
	}
	
	
	
	
	private DataDto convertData(Data entity) {
		DataDto dto = new DataDto();
		dto.setDataId(entity.getDataId().getDataId());
		dto.setDataName(entity.getDataName());
		dto.setDataDescription(entity.getDataDescription());
		dto.setDataCategoryId(entity.getDataCategoryId().getDataCategoryId());	
		dto.setDataCategoryName(entity.getDataCategoryName());
		dto.setCompanyId(entity.getCompanyId().id());
		if(entity.getUrl() != null) {
			dto.setUrl(entity.getUrl().getUrl());
		}
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}

}
