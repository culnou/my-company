package com.culnou.mumu.company.infrastructure.persistence.member.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;



public interface MemberCategoryJpaDataRepository extends JpaRepository<MemberCategory, MemberCategoryId> {
	public MemberCategory findByMemberCategoryId(MemberCategoryId memberCategoryId);
	@Query(value = "select memberCategory from MemberCategory memberCategory where memberCategory.companyId = :CompanyId")
	public List<MemberCategory> findMemberCategoriesOfCompany(@Param("CompanyId") CompanyId companyId);
	@Query(value = "select memberCategory from MemberCategory memberCategory join memberCategory.associatedMemberTypes memberTypes where memberTypes.memberTypeId = :MemberTypeId")
	public List<MemberCategory> findMemberCategoriesOfMemberType(@Param("MemberTypeId") String memberTypeId);
	@Query(value = "select memberCategory from MemberCategory memberCategory where memberCategory.businessUnitId = :BusinessUnitId")
	public List<MemberCategory> findMemberCategoriesOfBusinessUnit(@Param("BusinessUnitId") BusinessUnitId businessUnitId);

	

}
