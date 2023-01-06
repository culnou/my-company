package com.culnou.mumu.company.domain.model.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Department;
import com.culnou.mumu.company.domain.model.DepartmentId;
import com.culnou.mumu.company.domain.model.DepartmentRepository;

@Service
@Transactional
public class DepartmentAdapter {

	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository repository;
	
	public Department findDepartmentOfId(DepartmentId departmentId) throws Exception{
		return repository.departmentOfId(departmentId);
	}
}
