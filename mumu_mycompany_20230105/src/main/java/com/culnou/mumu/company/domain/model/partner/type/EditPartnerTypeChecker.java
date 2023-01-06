package com.culnou.mumu.company.domain.model.partner.type;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.partner.category.PartnerCategory;
import com.culnou.mumu.company.domain.model.partner.category.PartnerCategoryRegistry;

/*
 * メンバータイプが編集できるかチェックするドメインサービス
 */
@Service
@Transactional
public class EditPartnerTypeChecker {
	
	@Autowired
	private PartnerCategoryRegistry registry;
	
	public boolean editable(String partnerTypeId) throws Exception{
		boolean check = true;
		if(partnerTypeId == null || partnerTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
		}
		//対応するカテゴリがあるか検証する。
		List<PartnerCategory> categories = registry.findPartnerCategoriesOfPartnerType(partnerTypeId);
		if(categories.size() > 0) {
			check = false;
		}
		return check;
	}

}
