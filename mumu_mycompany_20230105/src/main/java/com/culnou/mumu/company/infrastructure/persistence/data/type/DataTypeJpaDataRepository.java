package com.culnou.mumu.company.infrastructure.persistence.data.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.type.DataClass;
import com.culnou.mumu.company.domain.model.data.type.DataType;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;



public interface DataTypeJpaDataRepository extends JpaRepository<DataType, DataTypeId> {
	
    public DataType findBydataTypeId(DataTypeId dataTypeId);
	
	@Query(value = "select dataType from DataType dataType where dataType.companyId = :CompanyId")
	public List<DataType> findDatasTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select dataType from DataType dataType where dataType.businessDomainId = :BusinessDomainId")
	public List<DataType> findDataTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	@Query(value = "select dataType from DataType dataType where dataType.businessDomainId = :BusinessDomainId and dataType.dataClass = :DataClass")
	public List<DataType> findDataTypesOfBusinessDomainAndDataClass(@Param("BusinessDomainId") BusinessDomainId businessDomainId, @Param("DataClass") DataClass dataClass);

	@Query(value = "select dataType from DataType dataType where dataType.parentId = :ParentId")
	public List<DataType> findChildrenOfParent(@Param("ParentId") String parentId);
}
