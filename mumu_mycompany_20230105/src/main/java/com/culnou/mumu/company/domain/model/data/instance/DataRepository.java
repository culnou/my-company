package com.culnou.mumu.company.domain.model.data.instance;

import java.util.List;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;

public interface DataRepository {
	//コマンド
    public void save(Data data) throws Exception;
	
	public void remove(Data data) throws Exception;
	
	//クエリ
	public Data dataOfId(DataId dataId) throws Exception;
	
	public List<Data> dataOfCompany(CompanyId companyId) throws Exception;
	
	public List<Data> dataOfDataCategory(DataCategoryId dataCategoryId) throws Exception;
	
	public List<Data> dataOfWork(WorkId workId) throws Exception;


}
