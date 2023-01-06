package com.culnou.mumu.company.infrastructure.persistence.data.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.data.category.DataCategory;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.type.DataClass;





public interface DataCategoryJpaDataRepository extends JpaRepository<DataCategory, DataCategoryId> {
	public DataCategory findByDataCategoryId(DataCategoryId dataCategoryId);
	@Query(value = "select dataCategory from DataCategory dataCategory where dataCategory.companyId = :CompanyId")
	public List<DataCategory> findDataCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	//@ElementCollectionのJoin
	@Query(value = "select dataCategory from DataCategory dataCategory join dataCategory.associatedDataTypes dataTypes where dataTypes.dataTypeId = :DataTypeId")
	public List<DataCategory> findDataCategoriesOfDataType(@Param("DataTypeId") String dataTypeId);
	@Query(value = "select dataCategory from DataCategory dataCategory where dataCategory.businessUnitId = :BusinessUnitId")
	public List<DataCategory> findDataCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	@Query(value = "select dataCategory from DataCategory dataCategory where dataCategory.businessUnitId = :BusinessUnitId and dataCategory.dataClass = :DataClass")
	public List<DataCategory> findDataCategoriesOfBusinessUnitAndDataClass(@Param("BusinessUnitId") BusinessUnitId businessUnitId, @Param("DataClass") DataClass dataClass);



}
