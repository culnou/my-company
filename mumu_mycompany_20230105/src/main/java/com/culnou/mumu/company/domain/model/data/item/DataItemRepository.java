package com.culnou.mumu.company.domain.model.data.item;

import java.util.List;



public interface DataItemRepository {
	
    public DataItem findDataItemOfId(DataItemId dataItemId) throws Exception;
	
    public List<DataItem> findDataItemsOfCompany(String companyId) throws Exception;
	
	public List<DataItem> findDataItemsOfDataType(String dataTypeId) throws Exception;
	
	public void save(DataItem dataItem) throws Exception;
	
	public void remove(DataItem dataItem) throws Exception;

}
