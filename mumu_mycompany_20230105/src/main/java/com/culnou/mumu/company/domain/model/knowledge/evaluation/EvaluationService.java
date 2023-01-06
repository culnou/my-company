package com.culnou.mumu.company.domain.model.knowledge.evaluation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.work.WorkService;
import com.culnou.mumu.company.domain.model.activity.work.WorkType;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.EvaluationDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.WorkDto;

@Service
@Transactional
public class EvaluationService {
	
	@Autowired
	private EvaluationRegistry registry;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private WorkService workService;
	
	/*
	 * ターゲットの評価を探す
	 */
	public List<EvaluationDto> findEvaluationsOfTarget(String targetId) throws Exception{
		if(targetId == null) {
			throw new Exception("The_targetId_may_not_be_set_to_null");
		}
		if(targetId.isEmpty()) {
			throw new Exception("Must_provide_a_targetId");
		}
		return this.convertEvaluations(registry.findEvaluationsOfTarget(targetId));
	}
	
	/*
	 * 評価を登録する
	 */
	public MessageDto registerEvaluation(EvaluationDto evaluation) {
		
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(evaluation == null) {
				throw new Exception("The_evaluation_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(evaluation.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//ビジネスロジック
			//ワークの場合の処理
			if(evaluation.getEvaluationTarget().equals(EvaluationTarget.Work)) {
				WorkDto work = workService.findWorkOfId(evaluation.getTargetId());
				if(work.getWorkType().equals(WorkType.Done)) {
					if(work.getBusinessState() != null) {
						if(work.getBusinessState().equals(BusinessState.Evaluated)) {
							throw new Exception("The_work_is_already_evaluated");
						}
					}
				}else {
					throw new Exception("The_work_is_not_done_yet");
				}
			}
			Evaluation entity = this.convertEvaluationDto(evaluation);
			entity.setEvaluationId(registry.nextIdentity());
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
	public MessageDto updateEvaluation(EvaluationDto evaluation) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(evaluation == null) {
				throw new Exception("The_evaluation_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Evaluation result = registry.findEvaluationOfId(evaluation.getEvaluationId());
			if(result == null) {
				throw new Exception("The_evaluation_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(evaluation.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			
			//更新のビジネスロジック
			//必要な属性だけ更新する
			Evaluation entity = this.convertEvaluationDto(evaluation);
			entity.setEvaluationId(result.getEvaluationId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
			entity.setTargetId(evaluation.getTargetId());
			entity.setCreatedAt(evaluation.getCreatedAt());			
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
	public MessageDto removeEvaluation(String evaluationId) {
		MessageDto message = new MessageDto();
		try {
			Evaluation evaluation = registry.findEvaluationOfId(evaluationId);
			if(evaluationId == null) {
				throw new Exception("The_evaluationId_could_not_be_found");
			}
			registry.remove(evaluation);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}


	
	private EvaluationDto convertEvaluation(Evaluation entity) {
		EvaluationDto dto = new EvaluationDto();
		dto.setEvaluationId(entity.getEvaluationId().getEvaluationId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setEvaluationTarget(entity.getEvaluationTarget());
		dto.setTargetId(entity.getTargetId());
		dto.setEvaluationName(entity.getEvaluationName());
		dto.setEvaluationDescription(entity.getEvaluationDescription());
		dto.setEvaluationNumber(entity.getEvaluationNumber());
		dto.setUrl(entity.getUrl());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<EvaluationDto> convertEvaluations(List<Evaluation> entities){
		List<EvaluationDto> dtos = new ArrayList<>();
		for(Evaluation entity : entities) {
			dtos.add(this.convertEvaluation(entity));
		}
		return dtos;
	}
	
	private Evaluation convertEvaluationDto(EvaluationDto dto) {
		Evaluation entity = new Evaluation();
		if(dto.getEvaluationId() != null && !dto.getEvaluationId().isEmpty()) {
			entity.setEvaluationId(new EvaluationId(dto.getEvaluationId()));
		}
		entity.setEvaluationName(dto.getEvaluationName());
		entity.setEvaluationDescription(dto.getEvaluationDescription());
		entity.setEvaluationTarget(dto.getEvaluationTarget());
		entity.setTargetId(dto.getTargetId());
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		entity.setEvaluationNumber(dto.getEvaluationNumber());
		entity.setUrl(dto.getUrl());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


}
