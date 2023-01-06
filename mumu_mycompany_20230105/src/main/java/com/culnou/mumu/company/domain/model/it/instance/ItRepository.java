package com.culnou.mumu.company.domain.model.it.instance;

import java.util.List;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;


public interface ItRepository {
	
	//コマンド
    public void save(It it) throws Exception;
	
	public void remove(It it) throws Exception;
	
	//クエリ
	public It itOfId(ItId itId) throws Exception;
	
	public List<It> itsOfCompany(CompanyId companyId) throws Exception;
	
	public List<It> itsOfItCategory(String itCategoryId) throws Exception;
	
	public List<It> itsOfBusinessUnit(BusinessUnitId businessUnitId) throws Exception;
	
	public List<It> itsOfPlace(String placeId) throws Exception;

}
