package com.culnou.mumu.company.infrastructure.persistence.member.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;


import com.culnou.mumu.company.domain.model.member.category.MemberCategoryRepository;

@Service("memberCategoryJpaRepository")
@Transactional
public class MemberCategoryJpaRepository implements MemberCategoryRepository {

	@Autowired
	private MemberCategoryJpaDataRepository repository;

	@Override
	public void save(MemberCategory memberCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.save(memberCategory);

	}

	@Override
	public void remove(MemberCategory memberCategory) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(memberCategory);

	}

	@Override
	public MemberCategory memberCategoryOfId(MemberCategoryId memberCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByMemberCategoryId(memberCategoryId);
	}

	@Override
	public List<MemberCategory> memberCategoriesOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMemberCategoriesOfCompany(companyId);
	}

	@Override
	public List<MemberCategory> memberCategoriesOfMemberType(String memberTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findMemberCategoriesOfMemberType(memberTypeId);
	}

	@Override
	public List<MemberCategory> memberCategoriesOfBusinessUnit(BusinessUnitId businessUnitId)
			throws Exception {
		// TODO Auto-generated method stub
		return repository.findMemberCategoriesOfBusinessUnit(businessUnitId);
	}

}
