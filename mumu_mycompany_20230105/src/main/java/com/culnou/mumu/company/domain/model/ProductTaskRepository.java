package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface ProductTaskRepository {
	
	//識別子オブジェクトはリポジトリが生成する。
	public ProductTaskId nextIdentity() throws Exception;
	
	//コマンド
    public void save(ProductTask productTask) throws Exception;
	
	public void remove(ProductTask productTask) throws Exception;
	
	//クエリ
	public ProductTask productTaskOfId(ProductTaskId productTaskId) throws Exception;
	
	public List<ProductTask> productTasksOfCompany(CompanyId companyId) throws Exception;
	
	public List<ProductTask> productTasksOfProductType(ProductTypeId productTypeId) throws Exception;


}
