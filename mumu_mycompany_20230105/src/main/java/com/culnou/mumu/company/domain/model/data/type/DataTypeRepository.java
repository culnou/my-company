package com.culnou.mumu.company.domain.model.data.type;

import java.util.List;



public interface DataTypeRepository {
	
    public DataType findDataTypeOfId(DataTypeId dataTypeId) throws Exception;
	
    public List<DataType> findDataTypesOfCompany(String companyId) throws Exception;
	
	public List<DataType> findDataTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public List<DataType> findDataTypesOfBusinessDomainAndDataClass(String businessDomainId, DataClass dataClass) throws Exception;
	
	public List<DataType> findChildrenOfParent(String parentId) throws Exception;
	
	public void save(DataType dataType) throws Exception;
	
	public void remove(DataType dataType) throws Exception;

}
