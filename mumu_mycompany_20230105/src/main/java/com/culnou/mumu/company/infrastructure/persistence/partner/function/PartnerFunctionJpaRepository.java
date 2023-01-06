package com.culnou.mumu.company.infrastructure.persistence.partner.function;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.TaskId;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryId;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunction;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunctionId;
import com.culnou.mumu.company.domain.model.partner.function.PartnerFunctionRepository;

@Service("partnerFunctionJpaRepository")
@Transactional
public class PartnerFunctionJpaRepository implements PartnerFunctionRepository {

	@Autowired
	private PartnerFunctionJpaDataRepository repository;

	

	@Override
	public void save(PartnerFunction partnerFunction) throws Exception {
		// TODO Auto-generated method stub
		if(partnerFunction == null) {
			throw new IllegalArgumentException("The partnerFunction may not be set to null.");
		}
		repository.save(partnerFunction);


	}

	@Override
	public void remove(PartnerFunction partnerFunction) throws Exception {
		// TODO Auto-generated method stub
		if(partnerFunction == null) {
			throw new IllegalArgumentException("The partnerFunction may not be set to null.");
		}
		repository.delete(partnerFunction);

	}

	@Override
	public PartnerFunction partnerFunctionOfId(PartnerFunctionId partnerFunctionId) throws Exception {
		// TODO Auto-generated method stub
		if(partnerFunctionId == null) {
			throw new IllegalArgumentException("The partnerFunctionId may not be set to null.");
		}
		//集約のライフサイクルはリポジトリで保証するため検索結果の検証を行う。
		PartnerFunction entity = repository.findByPartnerFunctionId(partnerFunctionId);
		if(entity == null) {
			throw new IllegalArgumentException("The PartnerFunctionEntity may not be set to null.");
		}
		return entity;
	}

	@Override
	public List<PartnerFunction> partnerFunctionsOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		if(companyId == null) {
			throw new IllegalArgumentException("The companyId may not be set to null.");
		}
		List<PartnerFunction> entities = repository.findPartnerFunctionsOfCompany(companyId);
		return entities;
	}

	@Override
	public List<PartnerFunction> partnerFunctionsOfPartnerCategory(PartnerCategoryId partnerCategoryId) throws Exception {
		// TODO Auto-generated method stub
		if(partnerCategoryId == null) {
			throw new IllegalArgumentException("The partnerCategoryId may not be set to null.");
		}
		List<PartnerFunction> entities = repository.findPartnerFunctionsOfPartnerCategory(partnerCategoryId);
		return entities;
	}

	@Override
	public List<PartnerFunction> partnerFunctionsOfTask(TaskId taskId) throws Exception {
		// TODO Auto-generated method stub
		if(taskId == null) {
			throw new IllegalArgumentException("The taskId may not be set to null.");
		}
		List<PartnerFunction> entities = repository.findPartnerFunctionsOfTask(taskId);
		return entities;
	}


}
