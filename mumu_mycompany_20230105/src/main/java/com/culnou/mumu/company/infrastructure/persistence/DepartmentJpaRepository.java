package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.AbstractDepartmentRepository;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentType;
import com.culnou.mumu.company.domain.model.JobId;
@Service("departmentJpaRepository")
@Transactional
public class DepartmentJpaRepository extends AbstractDepartmentRepository {
	
	@Autowired
	private DepartmentJpaDataRepository repository;
	
	/*
	 * 変換処理
	 */
	private Department convertEntityToPojo(DepartmentEntity entity) {
		Department pojo = this.convertFromEntity(entity);
		//任意項目の変換
		if(entity.getBusinessUnitId() != null) {
			pojo.setBusinessUnitId(entity.getBusinessUnitId());
		}
		if(entity.getBusinessUnitName() != null) {
			pojo.setBusinessUnitName(entity.getBusinessUnitName());
		}
		if(entity.getDepartmentDescription() != null) {
			pojo.setDepartmentDescription(entity.getDepartmentDescription());
		}
		if(entity.getUrl() != null) {
			pojo.setUrl(entity.getUrl());
		}
		if(entity.getGoals() != null) {
			pojo.setGoals(entity.getGoals());
		}
		if(entity.getAchievements() != null) {
			pojo.setAchievements(entity.getAchievements());
		}
		pojo.setAssociatedApplicationCategories(entity.getAssociatedApplicationCategories());
		pojo.setAssociatedPartnerCategories(entity.getAssociatedPartnerCategories());
		
		pojo.setCreatedAt(entity.getCreatedAt());
		pojo.setUpdatedAt(entity.getUpdatedAt());
		
		return pojo;
	}
	
	private DepartmentEntity convertPojoToEntity(Department pojo) {
		DepartmentEntity entity = new DepartmentEntity();
		//Pojoの必須項目検証は事前条件として検証済み。
		entity.setJobId(pojo.getJobId());
		entity.setCompanyId(pojo.getCompanyId());
		entity.setDepartmentType(pojo.getDepartmentType());
		entity.setDepartmentId(pojo.getDepartmentId());
		entity.setJobName(pojo.getJobName());
		entity.setDepartmentName(pojo.getDepartmentName());
		//任意項目
		if(pojo.getBusinessUnitId() != null) {
			entity.setBusinessUnitId(pojo.getBusinessUnitId());
		}
		if(pojo.getBusinessUnitName() != null) {
			entity.setBusinessUnitName(pojo.getBusinessUnitName());
		}
		if(pojo.getDepartmentDescription() != null) {
			entity.setDepartmentDescription(pojo.getDepartmentDescription());
		}
		if(pojo.getUrl() != null) {
			entity.setUrl(pojo.getUrl());
		}
		if(pojo.getGoals() != null) {
			entity.setGoals(pojo.getGoals());
		}
		if(pojo.getAchievements() != null) {
			entity.setAchievements(pojo.getAchievements());
		}
		entity.setAssociatedApplicationCategories(pojo.getAssociatedApplicationCategories());
		entity.setAssociatedPartnerCategories(pojo.getAssociatedPartnerCategories());
		
		entity.setCreatedAt(pojo.getCreatedAt());
		entity.setUpdatedAt(pojo.getUpdatedAt());
		
		return entity;
	}
	
	private List<Department> convertEntitiesToPojos(List<DepartmentEntity> entities){
		List<Department> pojos = new ArrayList<>();
		for(DepartmentEntity entity : entities) {
			pojos.add(this.convertEntityToPojo(entity));
		}
		return pojos;
	}

	@Override
	public DepartmentId nextIdentity() throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new DepartmentId(str);
	}

	@Override
	public void save(Department department) throws Exception {
		// TODO Auto-generated method stub
		if(department == null) {
			throw new IllegalArgumentException("The department may not be set to null.");
		}
        repository.save(this.convertPojoToEntity(department));
	}

	@Override
	public void remove(Department department) throws Exception {
		// TODO Auto-generated method stub
		if(department == null) {
			throw new IllegalArgumentException("The department may not be set to null.");
		}
        repository.delete(this.convertPojoToEntity(department));

	}

	@Override
	public Department departmentOfId(DepartmentId departmentId) throws Exception {
		// TODO Auto-generated method stub
		if(departmentId == null) {
			throw new IllegalArgumentException("The departmentId may not be set to null.");
		}
		DepartmentEntity entity = repository.findByDepartmentId(departmentId);
		if(entity == null) {
			throw new IllegalArgumentException("The departmentEntity may not be set to null.");
		}
		return this.convertEntityToPojo(entity);
	}

	@Override
	public List<Department> departmentsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<DepartmentEntity> entities = repository.findDepartmentsOfCompany(companyId);
		return this.convertEntitiesToPojos(entities);
	}

	@Override
	public List<Department> departmentsOfJob(JobId jobId) throws Exception {
		// TODO Auto-generated method stub
		if(jobId == null) {
			throw new IllegalArgumentException("The jobId may not be set to null.");
		}
		List<DepartmentEntity> entities = repository.findDepartmentsOfJob(jobId);
		return this.convertEntitiesToPojos(entities);
	}
	
    public List<Department> departmentsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The businessUnitId may not be set to null.");
		}
		List<DepartmentEntity> entities = repository.findDepartmentsOfBusinessUnit(businessUnitId);
		return this.convertEntitiesToPojos(entities);
    }
	
    public List<Department> departmentsOfDepartmentType(DepartmentType departmentType) throws Exception{
    	if(departmentType == null) {
			throw new IllegalArgumentException("The departmentType may not be set to null.");
		}
		List<DepartmentEntity> entities = repository.findDepartmentsOfDepartmentType(departmentType);
		return this.convertEntitiesToPojos(entities);
    }
	public List<Department> departmentsOfNotBusinessUnit() throws Exception{
		List<DepartmentEntity> entities = repository.findDepartmentsOfNotBusinessUnit();
		return this.convertEntitiesToPojos(entities);
	}

}
