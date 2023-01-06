package com.culnou.mumu.compnay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;
import com.culnou.mumu.company.domain.model.data.type.DataClass;

import lombok.Data;


@Data
public class DataCategoryDto {
	
	private String dataCategoryId;
	private String dataCategoryName;
	private String dataCategoryDescription;
	private String companyId;
	private String businessUnitId;
	private String dataTypeId;
	private DataClass dataClass;
	private String url;
	private BusinessState businessState;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	private Date updatedAt;
	private List<Goal> goals = new ArrayList<>();
	private List<Achievement> achievements = new ArrayList<>();
	private List<AssociatedDataType> associatedDataTypes = new ArrayList<>();

}
