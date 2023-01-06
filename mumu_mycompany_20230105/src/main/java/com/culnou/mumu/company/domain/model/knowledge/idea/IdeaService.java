package com.culnou.mumu.company.domain.model.knowledge.idea;

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
import com.culnou.mumu.company.domain.model.knowledge.evaluation.EvaluationService;
import com.culnou.mumu.company.domain.model.member.MemberId;
import com.culnou.mumu.company.domain.model.member.MemberService;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.EvaluationDto;
import com.culnou.mumu.compnay.controller.IdeaDto;
import com.culnou.mumu.compnay.controller.MemberDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.WorkDto;

@Service
@Transactional
public class IdeaService {
	@Autowired
	private IdeaRegistry registry;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private WorkService workService;
	@Autowired
	private ActionAdapter actionService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private EvaluationService evaluationService;
	
	public IdeaDto findIdeaOfId(String ideaId) throws Exception{
		if(ideaId == null || ideaId.isEmpty()) {
			throw new IllegalArgumentException("The_ideaId_may_not_be_set_to_null");
		}
		Idea foundIdea = registry.findIdeaOfId(ideaId);
		//平均スコアを算定する。
		List<EvaluationDto> evals = evaluationService.findEvaluationsOfTarget(foundIdea.getIdeaId().getIdeaId());
		double totalNum = 0;
		double ave = 0;
		if(evals.size() > 0) {
			for(EvaluationDto eval : evals) {
				int num = eval.getEvaluationNumber();
				totalNum = totalNum + num;
			}
			ave = totalNum/evals.size();
		}
		double ave2 = Math.round(ave*10);
		foundIdea.setAverageScore(Double.toString(ave2/10));
		
		return this.convertIdea(foundIdea);
	}
	
	
	/*
	 * ターゲットの評価を探す
	 */
	public List<IdeaDto> findIdeasOfTarget(String targetId) throws Exception{
		List<IdeaDto> dtos = new ArrayList<>();
		if(targetId == null) {
			throw new Exception("The_targetId_may_not_be_set_to_null");
		}
		if(targetId.isEmpty()) {
			throw new Exception("Must_provide_a_targetId");
		}
		List<Idea> ideas = registry.findIdeasOfTarget(targetId);
		for(Idea idea : ideas) {
			List<EvaluationDto> evals = evaluationService.findEvaluationsOfTarget(idea.getIdeaId().getIdeaId());
			double totalNum = 0;
			double ave = 0;
			if(evals.size() > 0) {
				for(EvaluationDto eval : evals) {
					int num = eval.getEvaluationNumber();
					totalNum = totalNum + num;
				}
				ave = totalNum/evals.size();
			}
			double ave2 = Math.round(ave*10);
			idea.setAverageScore(Double.toString(ave2/10));

		}
		dtos.addAll(convertIdeas(ideas));
		return dtos;
	}
	
	/*
	 * ワークのガイドラインを探す。
	 */
	public List<IdeaDto> findIdeasOfWork(String workId) throws Exception{
		if(workId == null) {
			throw new Exception("The_workId_may_not_be_set_to_null");
		}
		if(workId.isEmpty()) {
			throw new Exception("Must_provide_a_workId");
		}
		WorkDto work = workService.findWorkOfId(workId);
		Action action = actionService.findActionOfId(new ActionId(work.getActionId()));
		List<IdeaDto> guides = this.findIdeasOfTarget(action.getTaskId().taskId());
		return guides;
	}
	
	/*
	 * 評価を登録する
	 */
	public MessageDto registerIdea(IdeaDto idea) {
		
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(idea == null) {
				throw new Exception("The_idea_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(idea.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			if(idea.getMemberId() == null) {
				throw new Exception("The_memberId_may_not_be_set_to_null");
			}
			MemberDto member = memberService.findMemberOfMemberId(idea.getMemberId());
			idea.setLastName(member.getLastName());
			idea.setFirstName(member.getFirstName());
			//ビジネスロジック
			Idea entity = this.convertIdeaDto(idea);
			entity.setIdeaId(registry.nextIdentity());
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
	public MessageDto updateIdea(IdeaDto idea) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(idea == null) {
				throw new Exception("The_idea_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Idea result = registry.findIdeaOfId(idea.getIdeaId());
			if(result == null) {
				throw new Exception("The_idea_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(idea.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			
			//更新のビジネスロジック
			//必要な属性だけ更新する
			Idea entity = this.convertIdeaDto(idea);
			entity.setIdeaId(result.getIdeaId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
			entity.setMemberId(result.getMemberId());
			entity.setLastName(result.getLastName());
			entity.setFirstName(result.getFirstName());
			entity.setTargetId(result.getTargetId());
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
	public MessageDto removeIdea(String ideaId) {
		MessageDto message = new MessageDto();
		try {
			Idea idea = registry.findIdeaOfId(ideaId);
			if(ideaId == null) {
				throw new Exception("The_ideaId_could_not_be_found");
			}
			registry.remove(idea);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}


	
	private IdeaDto convertIdea(Idea entity) {
		IdeaDto dto = new IdeaDto();
		dto.setIdeaId(entity.getIdeaId().getIdeaId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setMemberId(entity.getMemberId().getMemberId());
		dto.setLastName(entity.getLastName());
		dto.setFirstName(entity.getFirstName());
		dto.setIdeaTarget(entity.getIdeaTarget());
		dto.setTargetId(entity.getTargetId());
		dto.setIdeaName(entity.getIdeaName());
		dto.setIdeaDescription(entity.getIdeaDescription());
		dto.setUrl(entity.getUrl());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		dto.setAverageScore(entity.getAverageScore());
		return dto;
	}
	
	private List<IdeaDto> convertIdeas(List<Idea> entities){
		List<IdeaDto> dtos = new ArrayList<>();
		for(Idea entity : entities) {
			dtos.add(this.convertIdea(entity));
		}
		return dtos;
	}
	
	private Idea convertIdeaDto(IdeaDto dto) {
		Idea entity = new Idea();
		if(dto.getIdeaId() != null && !dto.getIdeaId().isEmpty()) {
			entity.setIdeaId(new IdeaId(dto.getIdeaId()));
		}
		entity.setIdeaName(dto.getIdeaName());
		entity.setIdeaDescription(dto.getIdeaDescription());
		entity.setIdeaTarget(dto.getIdeaTarget());
		entity.setMemberId(new MemberId(dto.getMemberId()));
		entity.setLastName(dto.getLastName());
		entity.setFirstName(dto.getFirstName());
		entity.setTargetId(dto.getTargetId());
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		entity.setUrl(dto.getUrl());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}


}
