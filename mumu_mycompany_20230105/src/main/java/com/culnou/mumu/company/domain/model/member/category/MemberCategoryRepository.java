package com.culnou.mumu.company.domain.model.member.category;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;



public interface MemberCategoryRepository {
	
	//コマンド
    public void save(MemberCategory memberCategory) throws Exception;
	
	public void remove(MemberCategory memberCategory) throws Exception;
	
	//クエリ
	public MemberCategory memberCategoryOfId(MemberCategoryId memberCategoryId) throws Exception;
	
	public List<MemberCategory> memberCategoriesOfCompany(CompanyId companyId) throws Exception;
	
	public List<MemberCategory> memberCategoriesOfMemberType(String memberTypeId) throws Exception;
	
	public List<MemberCategory> memberCategoriesOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;


}
