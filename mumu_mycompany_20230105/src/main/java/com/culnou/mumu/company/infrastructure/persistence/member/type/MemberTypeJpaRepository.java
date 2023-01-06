package com.culnou.mumu.company.infrastructure.persistence.member.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;

import com.culnou.mumu.company.domain.model.member.type.MemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeId;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeRepository;

@Service("memberTypeJpaRepository")
@Transactional
public class MemberTypeJpaRepository implements MemberTypeRepository {
	
	@Autowired
	private MemberTypeJpaDataRepository repository;

	@Override
	public MemberType findMemberTypeOfId(MemberTypeId memberTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByMemberTypeId(memberTypeId);
	}

	@Override
	public List<MemberType> findMemberTypesOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMemberTypesOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<MemberType> findMemberTypesOfBusinessDomain(String businessDomainId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMemberTypesOfBusinessDomain(new BusinessDomainId(businessDomainId));
	}

	@Override
	public void save(MemberType memberType) throws Exception {
		// TODO Auto-generated method stub
		repository.save(memberType);

	}

	@Override
	public void remove(MemberType memberType) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(memberType);

	}

	@Override
	public List<MemberType> findMemberTypesOfJob(String jobId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findMemberTypesOfJob(jobId);
	}

}
