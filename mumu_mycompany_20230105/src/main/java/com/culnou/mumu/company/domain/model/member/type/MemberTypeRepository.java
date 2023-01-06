package com.culnou.mumu.company.domain.model.member.type;

import java.util.List;





public interface MemberTypeRepository {
	
    public MemberType findMemberTypeOfId(MemberTypeId memberTypeId) throws Exception;
	
    public List<MemberType> findMemberTypesOfCompany(String companyId) throws Exception;
	
	public List<MemberType> findMemberTypesOfBusinessDomain(String businessDomainId) throws Exception;
	
	public List<MemberType> findMemberTypesOfJob(String jobId) throws Exception;
	
	public void save(MemberType memberType) throws Exception;
	
	public void remove(MemberType memberType) throws Exception;

}
