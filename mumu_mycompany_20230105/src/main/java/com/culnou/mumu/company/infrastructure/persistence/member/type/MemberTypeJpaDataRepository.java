package com.culnou.mumu.company.infrastructure.persistence.member.type;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;


import com.culnou.mumu.company.domain.model.member.type.MemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeId;

public interface MemberTypeJpaDataRepository extends JpaRepository<MemberType, MemberTypeId> {
	
	public MemberType findByMemberTypeId(MemberTypeId memberTypeId);
	
	@Query(value = "select memberType from MemberType memberType where memberType.companyId = :CompanyId")
	public List<MemberType> findMemberTypesOfCompany(@Param("CompanyId") CompanyId companyId);
	
	@Query(value = "select memberType from MemberType memberType where memberType.businessDomainId = :BusinessDomainId")
	public List<MemberType> findMemberTypesOfBusinessDomain(@Param("BusinessDomainId") BusinessDomainId businessDomainId);
	
	//@ElementCollectionのJoin
	@Query(value = "select memberType from MemberType memberType join memberType.associatedJobs jobs where jobs.jobId = :JobId")
	public List<MemberType> findMemberTypesOfJob(@Param("JobId") String jobId);

}
