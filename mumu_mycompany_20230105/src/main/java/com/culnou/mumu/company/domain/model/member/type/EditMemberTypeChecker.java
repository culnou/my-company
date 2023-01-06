package com.culnou.mumu.company.domain.model.member.type;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryRegistry;

/*
 * メンバータイプが編集できるかチェックするドメインサービス
 */
@Service
@Transactional
public class EditMemberTypeChecker {
	
	@Autowired
	private MemberCategoryRegistry registry;
	
	public boolean editable(String memberTypeId) throws Exception{
		boolean check = true;
		if(memberTypeId == null || memberTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
		}
		//対応するカテゴリがあるか検証する。
		List<MemberCategory> categories = registry.findMemberCategoriesOfMemberType(memberTypeId);
		
		if(categories.size() > 0) {
			check = false;
		}
		return check;
	}

}
