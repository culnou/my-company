package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.DataCategoryApplicationService;

import com.culnou.mumu.company.domain.model.data.item.DataItemService;
import com.culnou.mumu.compnay.controller.DataCategoryDto;
import com.culnou.mumu.compnay.controller.DataItemDto;

@Service
@Transactional
public class DataTypeChecker {
	
	@Autowired
	private DataItemService dataItemSerivice;
	@Autowired
	private DataCategoryApplicationService dataCategoryService;
	
	public String avarable(String dataTypeId) throws Exception{
		String message = "OK";
		List<DataItemDto> dataItems = dataItemSerivice.findDataItemsOfDataType(dataTypeId);
		if(dataItems.size() > 0) {
			message = "already_assigned_to_dataItem";
		}
		List<DataCategoryDto> dataCategories = dataCategoryService.findDataCategoriesOfDataType(dataTypeId);
		if(dataCategories.size() > 0) {
			message = "already_assigned_to_dataCategory";
		}
		
		return message;
	}

}
