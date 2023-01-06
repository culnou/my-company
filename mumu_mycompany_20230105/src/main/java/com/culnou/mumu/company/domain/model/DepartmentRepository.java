package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface DepartmentRepository {

	//識別子オブジェクトはリポジトリが生成する。
	public DepartmentId nextIdentity() throws Exception;
	
	//コマンド
    public void save(Department department) throws Exception;
	
	public void remove(Department department) throws Exception;
	
	//クエリ
	public Department departmentOfId(DepartmentId departmentId) throws Exception;
	
	public List<Department> departmentsOfCompany(CompanyId companyId) throws Exception;
	
	public List<Department> departmentsOfJob(JobId jobId) throws Exception;
	
	public List<Department> departmentsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;
	
	public List<Department> departmentsOfDepartmentType(DepartmentType departmentType) throws Exception;
	
	public List<Department> departmentsOfNotBusinessUnit() throws Exception;

}
