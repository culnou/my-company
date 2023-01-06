package com.culnou.mumu.company.domain.model.member;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceAdapter {
	
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository repository;
	
	protected Department findDepartmentOfId(DepartmentId departmentId) throws Exception{
		return repository.departmentOfId(departmentId);
	}
	/*
	 * 事業単位に所属する部門を探す
	 */
	protected List<Department> findDepartmentsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception{
		return repository.departmentsOfBusinessUnit(businessUnitId);
	}

}
