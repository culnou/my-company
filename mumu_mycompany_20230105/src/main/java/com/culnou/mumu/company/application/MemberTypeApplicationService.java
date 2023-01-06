package com.culnou.mumu.company.application;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.Attribute;
import com.culnou.mumu.company.domain.model.common.Email;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;
import com.culnou.mumu.company.domain.model.member.type.DeleteMemberTypeChecker;
import com.culnou.mumu.company.domain.model.member.type.EditMemberTypeChecker;
import com.culnou.mumu.company.domain.model.member.type.MemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeId;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeRegistry;
import com.culnou.mumu.compnay.controller.AttributesDto;
import com.culnou.mumu.compnay.controller.IndicatorsDto;
import com.culnou.mumu.compnay.controller.MemberTypeDto;
import com.culnou.mumu.compnay.controller.MemberTypeInfoDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class MemberTypeApplicationService {
	
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	@Autowired
	private MemberTypeRegistry registry;
	@Autowired
	private EditMemberTypeChecker checker;
	@Autowired
	private DeleteMemberTypeChecker removeChecker;
	
	public MemberTypeInfoDto findMemberTypeOfId(String memberTypeId) throws Exception{
		MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
		MemberTypeInfoDto dto = new MemberTypeInfoDto();
		dto.setCompanyId(memberType.getCompanyId().id());
		dto.setBusinessDomainId(memberType.getBusinessDomainId().businessDomainId());
		dto.setMemberTypeId(memberType.getMemberTypeId().getMemberTypeId());
		dto.setMemberTypeName(memberType.getMemberTypeName());
		dto.setMemberClass(memberType.getMemberClass());
		dto.setMemberTypeDescription(memberType.getMemberTypeDescription());
		dto.setValues(memberType.getValues());
		dto.setIssue(memberType.getIssue());
		dto.setJobId(memberType.getJobId());
		dto.setJobName(memberType.getJobName());
		dto.setAssociatedJobs(memberType.getAssociatedJobs());				
		dto.setEntityName(memberType.getEntityName());
		dto.setEntityEnglishName(memberType.getEntityEnglishName());
		dto.setEntityDescription(memberType.getEntityDescription());
		if(memberType.getAddress() != null) {
			dto.setAddress(memberType.getAddress().address());
		}
		if(memberType.getUrl() != null) {
			dto.setUrl(memberType.getUrl().getUrl());
		}
		dto.setAssociatedConstraint(memberType.getAssociatedConstraint());
		dto.setExistenceConstraint(memberType.getExistenceConstraint());
		dto.setDataOwner(memberType.getDataOwner());
		dto.setDataAmount(memberType.getDataAmount());
		if(memberType.getDataTypeId() != null) {
			dto.setDataTypeId(memberType.getDataTypeId().getDataTypeId());
		}
		dto.setIndicators(memberType.getIndicators());
		dto.setAttributes(memberType.getAttributes());
		return dto;
	}
	
	//メンバータイプの定義
	public MessageDto defineMemberType(String businessDomainId, MemberTypeDto memberTypeDto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(memberTypeDto.getCompanyId() == null || memberTypeDto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(memberTypeDto.getBusinessDomainId() == null || memberTypeDto.getBusinessDomainId().isEmpty()) {
				throw new IllegalArgumentException("The_businessDomainId_may_not_be_set_to_null");
			}
			if(memberTypeDto.getMemberTypeName() == null || memberTypeDto.getMemberTypeName().isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeName_may_not_be_set_to_null");
			}
			if(memberTypeDto.getMemberClass() == null) {
				throw new IllegalArgumentException("The_memberClass_may_not_be_set_to_null");
			}
			//メンバータイプの生成
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			MemberTypeId menberTypeId = registry.nextIdentity();
			MemberType memberType = businessDomain.defineMemberType(menberTypeId, memberTypeDto.getMemberTypeName(), memberTypeDto.getMemberClass());
			memberType.setJobId(memberTypeDto.getJobId());
			memberType.setJobName(memberTypeDto.getJobName());
			memberType.setAssociatedJobs(memberTypeDto.getAssociatedJobs());
			memberType.setValues(memberTypeDto.getValues());
			memberType.setIssue(memberTypeDto.getIssue());
			memberType.setDataTypeId(new DataTypeId(memberTypeDto.getDataTypeId()));
			memberType.setMemberTypeDescription(memberTypeDto.getMemberTypeDescription());
			memberType.setCreatedAt(new Date());
			//メンバータイプの保存
			registry.save(memberType);
			message.setReturnValue(menberTypeId.getMemberTypeId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	//社員マスターの設定
	public MessageDto  setMemberMaster(String memberTypeId, String memberMasterUrl) {
		return null;
	}
	//メンバータイプ情報の追加
	public MessageDto  addMemberTypeInfo(String businessDomainId, MemberTypeInfoDto memberTypeDto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(memberTypeDto.getCompanyId() == null || memberTypeDto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(memberTypeDto.getBusinessDomainId() == null || memberTypeDto.getBusinessDomainId().isEmpty()) {
				throw new IllegalArgumentException("The_businessDomainId_may_not_be_set_to_null");
			}
			if(memberTypeDto.getMemberTypeName() == null || memberTypeDto.getMemberTypeName().isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeName_may_not_be_set_to_null");
			}
			if(memberTypeDto.getMemberClass() == null) {
				throw new IllegalArgumentException("The_memberClass_may_not_be_set_to_null");
			}
			//メンバータイプの生成
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			MemberTypeId menberTypeId = registry.nextIdentity();
			MemberType memberType = businessDomain.defineMemberType(menberTypeId, memberTypeDto.getMemberTypeName(), memberTypeDto.getMemberClass());
			memberType.setValues(memberTypeDto.getValues());
			memberType.setIssue(memberTypeDto.getIssue());
			memberType.setJobId(memberTypeDto.getJobId());
			memberType.setJobName(memberTypeDto.getJobName());
			memberType.setAssociatedJobs(memberTypeDto.getAssociatedJobs());
			memberType.setMemberTypeDescription(memberTypeDto.getMemberTypeDescription());
			memberType.setEntityName(memberTypeDto.getEntityName());
			memberType.setEntityEnglishName(memberTypeDto.getEntityEnglishName());
			memberType.setEntityDescription(memberTypeDto.getEntityDescription());
			memberType.setDataOwner(memberTypeDto.getDataOwner());
			if(!(memberTypeDto.getDataTypeId() == null || memberTypeDto.getDataTypeId().isEmpty())) {
				memberType.setDataTypeId(new DataTypeId(memberTypeDto.getDataTypeId()));
			}
			if(!(memberTypeDto.getAddress() == null || memberTypeDto.getAddress().isEmpty())) {
				memberType.setAddress(new Email(memberTypeDto.getAddress()));
			}
			if(!(memberTypeDto.getUrl() == null || memberTypeDto.getUrl().isEmpty())) {
				memberType.setUrl(new Url(memberTypeDto.getUrl()));
			}
			memberType.setAssociatedConstraint(memberTypeDto.getAssociatedConstraint());
			memberType.setExistenceConstraint(memberTypeDto.getExistenceConstraint());
			memberType.setDataAmount(memberTypeDto.getDataAmount());
			memberType.setCreatedAt(new Date());
			//メンバータイプの保存
			registry.save(memberType);
			message.setReturnValue(menberTypeId.getMemberTypeId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプ情報の修正
	public MessageDto modifyMemberTypeInfo(String memberTypeId, MemberTypeInfoDto memberTypeDto) {
		MessageDto message = new MessageDto();
		try {
			if(!checker.editable(memberTypeId)) {
				throw new IllegalArgumentException("The_memberType_has_already_used_at_edit");
			}
			
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			if(memberType == null) {
				throw new IllegalArgumentException("The_memberType_dose_not_exist");
			}
			//メンバータイプ情報の更新
			//NULLでも空文字でも更新するようにする。
			memberType.setMemberTypeName(memberTypeDto.getMemberTypeName());
			memberType.setMemberTypeDescription(memberTypeDto.getMemberTypeDescription());
			memberType.setValues(memberTypeDto.getValues());
			memberType.setIssue(memberTypeDto.getIssue());
			memberType.setJobId(memberTypeDto.getJobId());
			memberType.setJobName(memberTypeDto.getJobName());
			memberType.setAssociatedJobs(memberTypeDto.getAssociatedJobs());
			memberType.setEntityName(memberTypeDto.getEntityName());
			memberType.setMemberClass(memberTypeDto.getMemberClass());
			memberType.setEntityEnglishName(memberTypeDto.getEntityEnglishName());
			memberType.setEntityDescription(memberTypeDto.getEntityDescription());
			//アドレスがNULLでなくかつ空文字でない場合はメールアドレスを更新し、それ以外はメールアドレスをNULLにセットする。
			if(!(memberTypeDto.getAddress() == null || memberTypeDto.getAddress().isEmpty())) {
				memberType.setAddress(new Email(memberTypeDto.getAddress()));
			}else {
				memberType.setAddress(null);
			}
			memberType.setDataOwner(memberTypeDto.getDataOwner());
			memberType.setAssociatedConstraint(memberTypeDto.getAssociatedConstraint());
			memberType.setExistenceConstraint(memberTypeDto.getExistenceConstraint());
			memberType.setDataAmount(memberTypeDto.getDataAmount());
			if(!(memberTypeDto.getDataTypeId() == null || memberTypeDto.getDataTypeId().isEmpty())) {
				memberType.setDataTypeId(new DataTypeId(memberTypeDto.getDataTypeId()));
			}else {
				memberType.setDataTypeId(null);			}
			if(!(memberTypeDto.getUrl() == null || memberTypeDto.getUrl().isEmpty())) {
				memberType.setUrl(new Url(memberTypeDto.getUrl()));
			}else {
				memberType.setUrl(null);
			}
			memberType.setUpdatedAt(new Date());
			//メンバータイプの保存
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプ一覧情報の確認
	public List<MemberTypeInfoDto> confirmyMemberTypeList(String businessDomainId) {
		List<MemberTypeInfoDto> dtos = new ArrayList<>();
		try {
			if(businessDomainId == null || businessDomainId.isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			if(businessDomain == null) {
				throw new IllegalArgumentException("The_businessDomain_dose_not_exist");
			}
			List<MemberType> memberTypes = registry.findMemberTypesOfBusinessDomain(businessDomainId);
			
			for(MemberType memberType : memberTypes) {
				MemberTypeInfoDto dto = new MemberTypeInfoDto();
				dto.setCompanyId(memberType.getCompanyId().id());
				dto.setBusinessDomainId(memberType.getBusinessDomainId().businessDomainId());
				dto.setMemberTypeId(memberType.getMemberTypeId().getMemberTypeId());
				dto.setMemberTypeName(memberType.getMemberTypeName());
				dto.setMemberClass(memberType.getMemberClass());
				dto.setMemberTypeDescription(memberType.getMemberTypeDescription());
				dto.setValues(memberType.getValues());
				dto.setIssue(memberType.getIssue());
				dto.setJobId(memberType.getJobId());
				dto.setJobName(memberType.getJobName());
				dto.setAssociatedJobs(memberType.getAssociatedJobs());				
				dto.setEntityName(memberType.getEntityName());
				dto.setEntityEnglishName(memberType.getEntityEnglishName());
				dto.setEntityDescription(memberType.getEntityDescription());
				if(memberType.getAddress() != null) {
					dto.setAddress(memberType.getAddress().address());
				}
				if(memberType.getUrl() != null) {
					dto.setUrl(memberType.getUrl().getUrl());
				}
				dto.setAssociatedConstraint(memberType.getAssociatedConstraint());
				dto.setExistenceConstraint(memberType.getExistenceConstraint());
				dto.setDataOwner(memberType.getDataOwner());
				dto.setDataAmount(memberType.getDataAmount());
				if(memberType.getDataTypeId() != null) {
					dto.setDataTypeId(memberType.getDataTypeId().getDataTypeId());
				}
				dto.setIndicators(memberType.getIndicators());
				dto.setAttributes(memberType.getAttributes());
				dtos.add(dto);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return dtos;
	}
	//メンバータイプ情報の削除
	public MessageDto removeMemberTypeInfo(String memberTypeId) {
		MessageDto message = new MessageDto();
		try {
			if(!removeChecker.removable(memberTypeId)) {
				throw new IllegalArgumentException("The_memberType_has_already_used_at_delete");
			}
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			registry.remove(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	//社員指標の定義
	public MessageDto defineMemberTypeIndicator(String memberTypeId, Indicator indicator) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.defineIndicator(indicator);
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

	//社員指標情報の追加
	public MessageDto addMemberTypeIndicatorInfo(String memberTypeId, Indicator indicator) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.defineIndicator(indicator);
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//社員指標情報の修正
	public MessageDto modifyMemberTypeIndicatorInfo(String memberTypeId, IndicatorsDto indicators) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(indicators == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			if(indicators.getPostIndicator() == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			if(indicators.getPreviousIndicator() == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.replaceIndicator(indicators.getPreviousIndicator(), indicators.getPostIndicator());
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	//社員指標情報の削除
	public MessageDto removeMemberTypeIndicatorInfo(String memberTypeId, Indicator indicator) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.removeIndicator(indicator);
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプの指標取得
	public List<Indicator> findIndicatorsOfMemberType(String memberTypeId){
		List<Indicator> indicators = new ArrayList<>();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			indicators.addAll(memberType.getIndicators());
		}catch(Exception ex) {
			ex.printStackTrace();	}
		return indicators;
	}
	
	//メンバータイプの属性定義
	public MessageDto defineMemberTypeAttribute(String memberTypeId, Attribute attribute) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(attribute == null) {
				throw new IllegalArgumentException("The_attribute_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.defineAttribute(attribute);
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプ属性情報の追加
	public MessageDto addMemberTypeAttribute(String memberTypeId, Attribute attribute) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(attribute == null) {
				throw new IllegalArgumentException("The_attribute_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.defineAttribute(attribute);
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプ属性情報の修正
	public MessageDto modifyMemberTypeAttribute(String memberTypeId, AttributesDto attributes) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(attributes == null) {
				throw new IllegalArgumentException("The_attribute_may_not_be_set_to_null");
			}
			if(attributes.getPreviousAttribute() == null) {
				throw new IllegalArgumentException("The_attribute_may_not_be_set_to_null");
			}
			if(attributes.getPostAttribute() == null) {
				throw new IllegalArgumentException("The_attribute_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.replaceAttribute(attributes.getPreviousAttribute(), attributes.getPostAttribute());
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプ属性情報の削除
	public MessageDto removeMemberTypeAttribute(String memberTypeId, Attribute attribute) {
		MessageDto message = new MessageDto();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			if(attribute == null) {
				throw new IllegalArgumentException("The_attribute_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			memberType.removeAttribute(attribute);
			registry.save(memberType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプの属性取得
	public List<Attribute> findAttributesOfMemberType(String memberTypeId){
		List<Attribute> attributes = new ArrayList<>();
		try {
			if(memberTypeId == null || memberTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_memberTypeId_may_not_be_set_to_null");
			}
			MemberType memberType = registry.findMemberTypeOfId(memberTypeId);
			attributes.addAll(memberType.getAttributes());
		}catch(Exception ex) {
			ex.printStackTrace();	}
		return attributes;
	}
	
	
	

}
