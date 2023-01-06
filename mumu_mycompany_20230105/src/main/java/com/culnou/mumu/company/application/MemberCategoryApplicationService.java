package com.culnou.mumu.company.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryRegistry;
import com.culnou.mumu.company.domain.service.MemberCategoryChecker;
import com.culnou.mumu.compnay.controller.MemberCategoryDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class MemberCategoryApplicationService {
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private MemberCategoryRegistry registry;
	@Autowired
	private MemberCategoryChecker checker;
	
	public MessageDto addMemberCategory(MemberCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(dto.getBusinessUnitId() == null || dto.getBusinessUnitId().isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getMemberCategoryName() == null || dto.getMemberCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_memberCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedMemberTypes().size() > 0)) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			
			//ビジネスロジック
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(new BusinessUnitId(dto.getBusinessUnitId()));
			if(businessUnit == null) {
				throw new Exception("The_businessUnit_is_not_exist");
			}
			MemberCategoryId memberId = registry.nextIdentity();
			MemberCategory memberCategory = businessUnit.defineMemberCategory(memberId, dto.getMemberCategoryName());
			memberCategory.setAssociatedMemberTypes(dto.getAssociatedMemberTypes());
			memberCategory.setMemberCategoryDescription(dto.getMemberCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				memberCategory.setUrl(new Url(dto.getUrl()));
			}
			memberCategory.setBusinessState(dto.getBusinessState());
			memberCategory.setStartDate(dto.getStartDate());
			memberCategory.setEndDate(dto.getEndDate());
			memberCategory.setGoals(dto.getGoals());
			memberCategory.setAchievements(dto.getAchievements());
			memberCategory.setCreatedAt(new Date());
			registry.save(memberCategory);
			message.setReturnValue(memberId.getMemberCategoryId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateMemberCategory(String memberCategoryId, MemberCategoryDto dto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(memberCategoryId == null || memberCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_memberCategoryId_may_not_be_set_to_null");
			}
			if(dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(dto.getMemberCategoryName() == null || dto.getMemberCategoryName().isEmpty()) {
				throw new IllegalArgumentException("The_memberCategoryName_may_not_be_set_to_null");
			}
			if(!(dto.getAssociatedMemberTypes().size() > 0)) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			//ビジネスロジック
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(memberCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(memberCategoryId));
			}
			//NULLでも空文字でも更新するようにする。
			MemberCategory memberCategory = registry.findMemberCategoryOfId(memberCategoryId);
			if(memberCategory == null) {
				throw new Exception("The_memberCategory_is_not_exist");
			}
			memberCategory.setAssociatedMemberTypes(dto.getAssociatedMemberTypes());
			memberCategory.setMemberCategoryName(dto.getMemberCategoryName());
			memberCategory.setMemberCategoryDescription(dto.getMemberCategoryDescription());
			if(!(dto.getUrl() == null || dto.getUrl().isEmpty())) {
				memberCategory.setUrl(new Url(dto.getUrl()));
			}else {
				memberCategory.setUrl(null);
			}
			memberCategory.setBusinessState(dto.getBusinessState());
			memberCategory.setStartDate(dto.getStartDate());
			memberCategory.setEndDate(dto.getEndDate());
			memberCategory.setGoals(dto.getGoals());
			memberCategory.setAchievements(dto.getAchievements());
			memberCategory.setUpdatedAt(new Date());
			registry.save(memberCategory);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeMemberCategory(String memberCategoryId) {
		MessageDto message = new MessageDto();
		try {
			if(memberCategoryId == null || memberCategoryId.isEmpty()) {
				throw new IllegalArgumentException("The_memberCategoryId_may_not_be_set_to_null");
			}
			MemberCategory memberCategory = registry.findMemberCategoryOfId(memberCategoryId);
			if(memberCategory == null) {
				throw new Exception("The_memberCategory_is_not_exist");
			}
			//プロジェクトやプログラムに割当てられている場合編集・削除できない。
			if(!checker.avarable(memberCategoryId).equals("OK")) {
				throw new Exception(checker.avarable(memberCategoryId));
			}
			registry.remove(memberCategory);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public List<MemberCategoryDto> findMemberCategoriesOfBusinessUnit(String businessUnitId) throws Exception{
		if(businessUnitId == null || businessUnitId.isEmpty()) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		List<MemberCategory> memberCategories = registry.findMemberCategoriesOfBusinessUnit(businessUnitId);
		List<MemberCategoryDto> dtos = new ArrayList<>();
		for(MemberCategory entity : memberCategories) {
			dtos.add(this.convertMemberCategory(entity));
		}
		return dtos;
	}
	
	public List<MemberCategoryDto> findMemberCategoriesOfCompany(String companyId) throws Exception{
		if(companyId == null || companyId.isEmpty()) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		List<MemberCategory> memberCategories = registry.findMemberCategoriesOfCompany(companyId);
		List<MemberCategoryDto> dtos = new ArrayList<>();
		for(MemberCategory entity : memberCategories) {
			dtos.add(this.convertMemberCategory(entity));
		}
		return dtos;
	}
	
	public List<MemberCategoryDto> findMemberCategoriesOfMemberType(String memberTypeId) throws Exception{
		if(memberTypeId == null || memberTypeId.isEmpty()) {
			throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
		}
		List<MemberCategory> memberCategories = registry.findMemberCategoriesOfMemberType(memberTypeId);
		List<MemberCategoryDto> dtos = new ArrayList<>();
		for(MemberCategory entity : memberCategories) {
			dtos.add(this.convertMemberCategory(entity));
		}
		return dtos;
	}
	
	public MemberCategoryDto findMemberCategoryOfId(String memberCategoryId) throws Exception{
		if(memberCategoryId == null || memberCategoryId.isEmpty()) {
			throw new IllegalArgumentException("The_memberCategoryId_may_not_be_set_to_null");
		}
		return this.convertMemberCategory(registry.findMemberCategoryOfId(memberCategoryId));
	}
	
	private MemberCategoryDto convertMemberCategory(MemberCategory entity) {
		MemberCategoryDto dto = new MemberCategoryDto();
		dto.setMemberCategoryId(entity.getMemberCategoryId().getMemberCategoryId());
		dto.setMemberCategoryName(entity.getMemberCategoryName());
		dto.setMemberCategoryDescription(entity.getMemberCategoryDescription());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setBusinessUnitId(entity.getBusinessUnitId().businessUnitId());
		dto.setAssociatedMemberTypes(entity.getAssociatedMemberTypes());
		dto.setBusinessState(entity.getBusinessState());
		dto.setGoals(entity.getGoals());
		dto.setAchievements(entity.getAchievements());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}


}
