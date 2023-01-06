package com.culnou.mumu.company.domain.model.data.instance;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.category.DataCategory;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryRepository;
import com.culnou.mumu.company.domain.model.data.type.DataClass;

/*
 * データが追加できるかチェックするドメインサービス
 */
@Service
@Transactional
public class AddDataChecker {
	@Qualifier("dataCategoryJpaRepository")
	@Autowired
	private DataCategoryRepository repository;
	
	public boolean addable(String dataCategoryId) throws Exception{
		boolean check = true;
		if(dataCategoryId == null || dataCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_dataCategoryId_may_not_be_set_to_null");
		}
		DataCategory dataCategory = repository.dataCategoryOfId(new DataCategoryId(dataCategoryId));
		//データカテゴリが有効でない場合、データを追加できない。
		if(!dataCategory.getBusinessState().equals(BusinessState.Executing)) {
			check = false;
		}
		//データカテゴリがマネジメントデータでない場合、データを追加できない。
		if(!dataCategory.getDataClass().equals(DataClass.ManagementData)) {
			check = false;
		}
		return check;
	}

}
