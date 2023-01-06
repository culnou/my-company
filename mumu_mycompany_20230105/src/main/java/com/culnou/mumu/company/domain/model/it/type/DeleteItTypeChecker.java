package com.culnou.mumu.company.domain.model.it.type;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.it.category.ItCategory;
import com.culnou.mumu.company.domain.model.it.category.ItCategoryRegistry;

/*
 * ITタイプが削除できるかチェックするドメインサービス
 */
@Service
@Transactional
public class DeleteItTypeChecker {
	@Autowired
	private ItCategoryRegistry registry;
	
	public boolean removable(String itTypeId) throws Exception{
		boolean check = true;
		if(itTypeId == null || itTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_itTypeId_may_not_be_set_to_null");
		}
		//対応するカテゴリがあるか検証する。
		List<ItCategory> categories = registry.findItCategoriesOfItType(itTypeId);
		
		if(categories.size() > 0) {
			check = false;
		}
		return check;
	}

}
