package com.culnou.mumu.company.infrastructure.persistence.data.instance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.instance.Data;
import com.culnou.mumu.company.domain.model.data.instance.DataId;

public interface DataJpaDataRepository extends JpaRepository<Data, DataId> {
	public Data findByDataId(DataId dataId);
	@Query(value = "select data from Data data where data.companyId = :CompanyId")
	public List<Data> findDataOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select data from Data data where data.dataCategoryId = :DataCategoryId")
	public List<Data> findDataOfDataCategory(@Param("DataCategoryId") DataCategoryId dataCategoryId);
	@Query(value = "select data from Data data where data.workId = :WorkId")
	public List<Data> findDataOfWork(@Param("WorkId") WorkId workId);
}
