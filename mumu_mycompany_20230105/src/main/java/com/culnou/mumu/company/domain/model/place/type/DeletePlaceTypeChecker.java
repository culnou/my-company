package com.culnou.mumu.company.domain.model.place.type;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.place.category.PlaceCategory;
import com.culnou.mumu.company.domain.model.place.category.PlaceCategoryRegistry;

/*
 * メンバータイプが削除できるかチェックするドメインサービス
 */
@Service
@Transactional
public class DeletePlaceTypeChecker {
	@Autowired
	private PlaceCategoryRegistry registry;
	
	public boolean removable(String placeTypeId) throws Exception{
		boolean check = true;
		if(placeTypeId == null || placeTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_placeTypeId_may_not_be_set_to_null");
		}
		//対応するカテゴリがあるか検証する。
		List<PlaceCategory> categories = registry.findPlaceCategoriesOfPlaceType(placeTypeId);
		
		if(categories.size() > 0) {
			check = false;
		}
		return check;
	}

}
