package com.culnou.mumu.company.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentType;
import com.culnou.mumu.company.domain.model.JobId;

public interface DepartmentJpaDataRepository extends JpaRepository<DepartmentEntity, DepartmentId> {
	
    public DepartmentEntity findByDepartmentId(DepartmentId departmentId);
	
	@Query(value = "select department from DepartmentEntity department where department.companyId = :CompanyId")
	public List<DepartmentEntity> findDepartmentsOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select department from DepartmentEntity department where department.jobId = :JobId")
	public List<DepartmentEntity> findDepartmentsOfJob(@Param("JobId") JobId jobId);
	
	@Query(value = "select department from DepartmentEntity department where department.businessUnitId = :BusinessUnitId")
	public List<DepartmentEntity> findDepartmentsOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);
	
	@Query(value = "select department from DepartmentEntity department where department.departmentType = :DepartmentType")
	public List<DepartmentEntity> findDepartmentsOfDepartmentType(@Param("DepartmentType") DepartmentType departmentType);
	
	@Query(value = "select department from DepartmentEntity department where department.businessUnitId IS NULL")
	public List<DepartmentEntity> findDepartmentsOfNotBusinessUnit();

}
