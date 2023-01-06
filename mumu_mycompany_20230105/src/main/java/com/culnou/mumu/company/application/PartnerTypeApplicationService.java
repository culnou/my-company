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

import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

import com.culnou.mumu.company.domain.model.partner.type.DeletePartnerTypeChecker;
import com.culnou.mumu.company.domain.model.partner.type.EditPartnerTypeChecker;
import com.culnou.mumu.company.domain.model.partner.type.PartnerType;

import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeId;
import com.culnou.mumu.company.domain.model.partner.type.PartnerTypeRegistry;
import com.culnou.mumu.compnay.controller.PartnerTypeDto;
import com.culnou.mumu.compnay.controller.IndicatorsDto;

import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class PartnerTypeApplicationService {
	
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	@Autowired
	private PartnerTypeRegistry registry;
	@Autowired
	private EditPartnerTypeChecker editChecker;
	@Autowired
	private DeletePartnerTypeChecker removeChecker;
	
	
	//メンバータイプ情報の追加
	public MessageDto  addPartnerType(String businessDomainId, PartnerTypeDto partnerTypeDto) {
		MessageDto message = new MessageDto();
		try {
			//コントローラーの事前条件検証。
			if(partnerTypeDto.getCompanyId() == null || partnerTypeDto.getCompanyId().isEmpty()) {
				throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
			}
			if(partnerTypeDto.getBusinessDomainId() == null || partnerTypeDto.getBusinessDomainId().isEmpty()) {
				throw new IllegalArgumentException("The_businessDomainId_may_not_be_set_to_null");
			}
			if(partnerTypeDto.getPartnerTypeName() == null || partnerTypeDto.getPartnerTypeName().isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeName_may_not_be_set_to_null");
			}
	
			//メンバータイプの生成
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			PartnerTypeId menberTypeId = registry.nextIdentity();
			PartnerType partnerType = businessDomain.definePartnerType(menberTypeId, partnerTypeDto.getPartnerTypeName());
			partnerType.setPartnerTypeDescription(partnerTypeDto.getPartnerTypeDescription());
			if(!(partnerTypeDto.getDataTypeId() == null || partnerTypeDto.getDataTypeId().isEmpty())) {
				partnerType.setDataTypeId(new DataTypeId(partnerTypeDto.getDataTypeId()));
			}
			
			if(!(partnerTypeDto.getUrl() == null || partnerTypeDto.getUrl().isEmpty())) {
				partnerType.setUrl(new Url(partnerTypeDto.getUrl()));
			}
			partnerType.setCreatedAt(new Date());
			//メンバータイプの保存
			registry.save(partnerType);
			message.setReturnValue(menberTypeId.getPartnerTypeId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	//メンバータイプ情報の修正
	public MessageDto modifyPartnerType(String partnerTypeId, PartnerTypeDto partnerTypeDto) {
		MessageDto message = new MessageDto();
		try {
			if(!editChecker.editable(partnerTypeId)) {
				throw new IllegalArgumentException("The_partnerType_has_already_used_at_edit");
			}
	
			if(partnerTypeId == null || partnerTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			
			PartnerType partnerType = registry.findPartnerTypeOfId(partnerTypeId);
			if(partnerType == null) {
				throw new IllegalArgumentException("The_partnerType_dose_not_exist");
			}
			//メンバータイプ情報の更新
			//NULLでも空文字でも更新するようにする。
			partnerType.setPartnerTypeName(partnerTypeDto.getPartnerTypeName());
			partnerType.setPartnerTypeDescription(partnerTypeDto.getPartnerTypeDescription());
			if(!(partnerTypeDto.getDataTypeId() == null || partnerTypeDto.getDataTypeId().isEmpty())) {
				partnerType.setDataTypeId(new DataTypeId(partnerTypeDto.getDataTypeId()));
			}else {
				partnerType.setDataTypeId(null);			
			}
			if(!(partnerTypeDto.getUrl() == null || partnerTypeDto.getUrl().isEmpty())) {
				partnerType.setUrl(new Url(partnerTypeDto.getUrl()));
			}else {
				partnerType.setUrl(null);
			}
			partnerType.setUpdatedAt(new Date());
			//メンバータイプの保存
			registry.save(partnerType);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプ一覧情報の確認
	public List<PartnerTypeDto> confirmyPartnerTypeList(String businessDomainId) {
		List<PartnerTypeDto> dtos = new ArrayList<>();
		try {
			if(businessDomainId == null || businessDomainId.isEmpty()) {
				throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
			}
			BusinessDomain businessDomain = businessDomainRepository.businessDomainOfId(new BusinessDomainId(businessDomainId));
			if(businessDomain == null) {
				throw new IllegalArgumentException("The_businessDomain_dose_not_exist");
			}
			List<PartnerType> partnerTypes = registry.findPartnerTypesOfBusinessDomain(businessDomainId);
			
			for(PartnerType partnerType : partnerTypes) {
				PartnerTypeDto dto = new PartnerTypeDto();
				dto.setCompanyId(partnerType.getCompanyId().id());
				dto.setBusinessDomainId(partnerType.getBusinessDomainId().businessDomainId());
				dto.setPartnerTypeId(partnerType.getPartnerTypeId().getPartnerTypeId());
				dto.setPartnerTypeName(partnerType.getPartnerTypeName());
				dto.setPartnerTypeDescription(partnerType.getPartnerTypeDescription());
				
				if(partnerType.getUrl() != null) {
					dto.setUrl(partnerType.getUrl().getUrl());
				}
				if(partnerType.getDataTypeId() != null) {
					dto.setDataTypeId(partnerType.getDataTypeId().getDataTypeId());
				}
				dto.setIndicators(partnerType.getIndicators());
				dtos.add(dto);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return dtos;
	}
	//メンバータイプ情報の削除
	public MessageDto removePartnerTypeInfo(String partnerTypeId) {
		MessageDto message = new MessageDto();
		try {
			if(!removeChecker.removable(partnerTypeId)) {
				throw new IllegalArgumentException("The_partnerType_has_already_used_at_delete");
			}
			if(partnerTypeId == null || partnerTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			PartnerType partnerType = registry.findPartnerTypeOfId(partnerTypeId);
			registry.remove(partnerType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	//社員指標の定義
	public MessageDto definePartnerTypeIndicator(String partnerTypeId, Indicator indicator) {
		MessageDto message = new MessageDto();
		try {
			if(partnerTypeId == null || partnerTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			PartnerType partnerType = registry.findPartnerTypeOfId(partnerTypeId);
			partnerType.defineIndicator(indicator);
			registry.save(partnerType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

	//社員指標情報の追加
	public MessageDto addPartnerTypeIndicatorInfo(String partnerTypeId, Indicator indicator) {
		MessageDto message = new MessageDto();
		try {
			if(partnerTypeId == null || partnerTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			PartnerType partnerType = registry.findPartnerTypeOfId(partnerTypeId);
			partnerType.defineIndicator(indicator);
			registry.save(partnerType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//社員指標情報の修正
	public MessageDto modifyPartnerTypeIndicatorInfo(String partnerTypeId, IndicatorsDto indicators) {
		MessageDto message = new MessageDto();
		try {
			if(partnerTypeId == null || partnerTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
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
			PartnerType partnerType = registry.findPartnerTypeOfId(partnerTypeId);
			partnerType.replaceIndicator(indicators.getPreviousIndicator(), indicators.getPostIndicator());
			registry.save(partnerType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	//社員指標情報の削除
	public MessageDto removePartnerTypeIndicatorInfo(String partnerTypeId, Indicator indicator) {
		MessageDto message = new MessageDto();
		try {
			if(partnerTypeId == null || partnerTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			if(indicator == null) {
				throw new IllegalArgumentException("The_indicator_may_not_be_set_to_null");
			}
			PartnerType partnerType = registry.findPartnerTypeOfId(partnerTypeId);
			partnerType.removeIndicator(indicator);
			registry.save(partnerType);
			message.setResult("OK");
		}catch(Exception ex){
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	//メンバータイプの指標取得
	public List<Indicator> findIndicatorsOfPartnerType(String partnerTypeId){
		List<Indicator> indicators = new ArrayList<>();
		try {
			if(partnerTypeId == null || partnerTypeId.isEmpty()) {
				throw new IllegalArgumentException("The_partnerTypeId_may_not_be_set_to_null");
			}
			PartnerType partnerType = registry.findPartnerTypeOfId(partnerTypeId);
			indicators.addAll(partnerType.getIndicators());
		}catch(Exception ex) {
			ex.printStackTrace();	}
		return indicators;
	}
	



}
