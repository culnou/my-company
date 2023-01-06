package com.culnou.mumu.company.domain.model.data.category;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.type.DataClass;




public interface DataCategoryRepository {
	
	//コマンド
    public void save(DataCategory dataCategory) throws Exception;
	
	public void remove(DataCategory dataCategory) throws Exception;
	
	//クエリ
	public DataCategory dataCategoryOfId(DataCategoryId dataCategoryId) throws Exception;
	
	public List<DataCategory> dataCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<DataCategory> dataCategoriesOfDataType(String dataTypeId) throws Exception;
	
	public List<DataCategory> dataCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;
	
	public List<DataCategory> dataCategoriesOfBusinessUnitAndDataClass(BusinessUnitId businessUnitId, DataClass dataClass) throws Exception;
	


}
