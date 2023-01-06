package com.culnou.mumu.company.infrastructure.persistence.application.function;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryId;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunction;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionId;
import com.culnou.mumu.company.domain.model.application.function.ApplicationFunctionRepository;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskId;

;

@Service("applicationFunctionJpaRepository")
@Transactional
public class ApplicationFunctionJpaRepository implements ApplicationFunctionRepository{
	@Autowired
	private ApplicationFunctionJpaDataRepository repository;

	

	@Override
	public void save(ApplicationFunction applicationFunction) throws Exception {
		// TODO Auto-generated method stub
		if(applicationFunction == null) {
			throw new IllegalArgumentException("The applicationFunction may not be set to null.");
		}
		repository.save(applicationFunction);


	}

	@Override
	public void remove(ApplicationFunction applicationFunction) throws Exception {
		// TODO Auto-generated method stub
		if(applicationFunction == null) {
			throw new IllegalArgumentException("The applicationFunction may not be set to null.");
		}
		repository.delete(applicationFunction);

	}

	@Override
	public ApplicationFunction applicationFunctionOfId(ApplicationFunctionId applicationFunctionId) throws Exception {
		// TODO Auto-generated method stub
		if(applicationFunctionId == null) {
			throw new IllegalArgumentException("The applicationFunctionId may not be set to null.");
		}
		//集約のライフサイクルはリポジトリで保証するため検索結果の検証を行う。
		ApplicationFunction entity = repository.findByApplicationFunctionId(applicationFunctionId);
		if(entity == null) {
			throw new IllegalArgumentException("The ApplicationFunctionEntity may not be set to null.");
		}
		return entity;
	}

	@Override
	public List<ApplicationFunction> applicationFunctionsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<ApplicationFunction> entities = repository.findApplicationFunctionsOfCompany(companyId);
		return entities;
	}

	@Override
	public List<ApplicationFunction> applicationFunctionsOfApplicationCategory(ApplicationCategoryId applicationCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(applicationCategoryId == null) {
			throw new IllegalArgumentException("The applicationCategoryId may not be set to null.");
		}
		List<ApplicationFunction> entities = repository.findApplicationFunctionsOfApplicationCategory(applicationCategoryId);
		return entities;
	}

	@Override
	public List<ApplicationFunction> applicationFunctionsOfApplicationTask(ApplicationTaskId applicationTaskId) throws Exception {
		// TODO Auto-generated method stub
		if(applicationTaskId == null) {
			throw new IllegalArgumentException("The applicationTaskId may not be set to null.");
		}
		List<ApplicationFunction> entities = repository.findApplicationFunctionsOfApplicationTask(applicationTaskId);
		return entities;
	}

}
