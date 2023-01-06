package com.culnou.mumu.company.domain.model.knowledge.guideline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.Action;
import com.culnou.mumu.company.domain.model.ActionId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.work.WorkService;
import com.culnou.mumu.company.domain.model.common.ActionAdapter;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;

import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.GuidelineDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.WorkDto;

@Service
@Transactional
public class GuidelineService {
	
	@Autowired
	private GuidelineRegistry registry;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private WorkService workService;
	@Autowired
	private ActionAdapter actionService;
	
	/*
	 * ターゲットの評価を探す
	 */
	public List<GuidelineDto> findGuidelinesOfTarget(String targetId) throws Exception{
		if(targetId == null) {
			throw new Exception("The_targetId_may_not_be_set_to_null");
		}
		if(targetId.isEmpty()) {
			throw new Exception("Must_provide_a_targetId");
		}
		return this.convertGuidelines(registry.findGuidelinesOfTarget(targetId));
	}
	
	/*
	 * ワークのガイドラインを探す。
	 */
	public List<GuidelineDto> findGuidelinesOfWork(String workId) throws Exception{
		if(workId == null) {
			throw new Exception("The_workId_may_not_be_set_to_null");
		}
		if(workId.isEmpty()) {
			throw new Exception("Must_provide_a_workId");
		}
		WorkDto work = workService.findWorkOfId(workId);
		Action action = actionService.findActionOfId(new ActionId(work.getActionId()));
		List<GuidelineDto> guides = this.findGuidelinesOfTarget(action.getTaskId().taskId());
		return guides;
	}
	
	/*
	 * 評価を登録する
	 */
	public MessageDto registerGuideline(GuidelineDto guideline) {
		
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(guideline == null) {
				throw new Exception("The_guideline_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(guideline.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//ビジネスロジック
			Guideline entity = this.convertGuidelineDto(guideline);
			entity.setGuidelineId(registry.nextIdentity());
			entity.setCreatedAt(new Date());
			
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	/*
	 * コメントを更新する
	 */
	public MessageDto updateGuideline(GuidelineDto guideline) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(guideline == null) {
				throw new Exception("The_guideline_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Guideline result = registry.findGuidelineOfId(guideline.getGuidelineId());
			if(result == null) {
				throw new Exception("The_guideline_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(guideline.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			
			//更新のビジネスロジック
			//必要な属性だけ更新する
			Guideline entity = this.convertGuidelineDto(guideline);
			entity.setGuidelineId(result.getGuidelineId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
			entity.setTargetId(result.getTargetId());
			entity.setGuidelineTarget(result.getGuidelineTarget());
			entity.setCreatedAt(result.getCreatedAt());			
			entity.setUpdatedAt(new Date());
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	/*
	 * コメントを削除する
	 */
	public MessageDto removeGuideline(String guidelineId) {
		MessageDto message = new MessageDto();
		try {
			Guideline guideline = registry.findGuidelineOfId(guidelineId);
			if(guidelineId == null) {
				throw new Exception("The_guidelineId_could_not_be_found");
			}
			registry.remove(guideline);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}


	
	private GuidelineDto convertGuideline(Guideline entity) {
		GuidelineDto dto = new GuidelineDto();
		dto.setGuidelineId(entity.getGuidelineId().getGuidelineId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setGuidelineTarget(entity.getGuidelineTarget());
		dto.setTargetId(entity.getTargetId());
		dto.setGuidelineName(entity.getGuidelineName());
		dto.setGuidelineDescription(entity.getGuidelineDescription());
		dto.setVersion(entity.getVersion());
		dto.setUrl(entity.getUrl());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<GuidelineDto> convertGuidelines(List<Guideline> entities){
		List<GuidelineDto> dtos = new ArrayList<>();
		for(Guideline entity : entities) {
			dtos.add(this.convertGuideline(entity));
		}
		return dtos;
	}
	
	private Guideline convertGuidelineDto(GuidelineDto dto) {
		Guideline entity = new Guideline();
		if(dto.getGuidelineId() != null && !dto.getGuidelineId().isEmpty()) {
			entity.setGuidelineId(new GuidelineId(dto.getGuidelineId()));
		}
		entity.setGuidelineName(dto.getGuidelineName());
		entity.setGuidelineDescription(dto.getGuidelineDescription());
		entity.setGuidelineTarget(dto.getGuidelineTarget());
		entity.setTargetId(dto.getTargetId());
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		entity.setVersion(dto.getVersion());
		entity.setUrl(dto.getUrl());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


}
