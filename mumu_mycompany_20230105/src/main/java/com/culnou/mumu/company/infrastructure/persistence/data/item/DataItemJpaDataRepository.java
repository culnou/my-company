package com.culnou.mumu.company.infrastructure.persistence.data.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.item.DataItem;
import com.culnou.mumu.company.domain.model.data.item.DataItemId;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;



public interface DataItemJpaDataRepository extends JpaRepository<DataItem, DataItemId> {
	
	public DataItem findBydataItemId(DataItemId dataItemId);
	
	@Query(value = "select dataItem from DataItem dataItem where dataItem.companyId = :CompanyId")
	public List<DataItem> findDataItemsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select dataItem from DataItem dataItem where dataItem.dataTypeId = :DataTypeId")
	public List<DataItem> findDataItemsOfDataType(@Param("DataTypeId") DataTypeId dataTypeId);

}
