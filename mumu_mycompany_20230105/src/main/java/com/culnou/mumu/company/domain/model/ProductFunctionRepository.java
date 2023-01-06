package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface ProductFunctionRepository {
	//識別子オブジェクトはリポジトリが生成する。
	public ProductFunctionId nextIdentity() throws Exception;
	
	//コマンド
    public void save(ProductFunction productFunction) throws Exception;
	
	public void remove(ProductFunction productFunction) throws Exception;
	
	//クエリ
	public ProductFunction productFunctionOfId(ProductFunctionId productFunctionId) throws Exception;
	
	public List<ProductFunction> productFunctionsOfCompany(CompanyId companyId) throws Exception;
	
	public List<ProductFunction> productFunctionsOfProductCategory(ProductCategoryId productCategoryId) throws Exception;

	public List<ProductFunction> productFunctionsOfProductTask(ProductTaskId productTaskId) throws Exception;


}
