package com.culnou.mumu.company.domain.model.activity.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ActionId;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.workflow.Workflow;
import com.culnou.mumu.company.domain.model.activity.workflow.WorkflowId;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.ProductAdapter;
import com.culnou.mumu.company.domain.model.common.ProductServiceAdapter;
import com.culnou.mumu.company.domain.model.common.ProductWorkAdapter;

import com.culnou.mumu.company.domain.model.knowledge.evaluation.EvaluationService;
import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberId;

import com.culnou.mumu.compnay.controller.ActionDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.CompleteWorkDto;
import com.culnou.mumu.compnay.controller.EvaluationDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.ProductDto;
import com.culnou.mumu.compnay.controller.ProductServiceDto;
import com.culnou.mumu.compnay.controller.ProductWorkDto;
import com.culnou.mumu.compnay.controller.WorkDto;

@Service
@Transactional
public class WorkService {
	@Autowired
	private WorkRegistry registry;
	@Autowired
	private WorkflowAdapter workflowAdapter;
	@Autowired
	private WorkCompanyAdapter companyAdapter;
	@Autowired
	private WorkMemberAdapter memberAdapter;
	@Autowired
	private WorkActionAdapter actionAdapter;
	@Autowired
	private ProductAdapter productAdapter;
	@Autowired
	private ProductServiceAdapter productServiceAdapter;
	@Autowired
	private ProductWorkAdapter productWorkAdapter;
	@Autowired
	private EvaluationService evaluationService;
	
	public WorkDto findWorkOfId(String workId) throws Exception{
		if(workId == null || workId.isEmpty()) {
			throw new IllegalArgumentException("The_workId_may_not_be_set_to_null");
		}
		Work foundWork = registry.findWorkOfId(workId);
		//平均スコアを算定する。
		List<EvaluationDto> evals = evaluationService.findEvaluationsOfTarget(foundWork.getWorkId().getWorkId());
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
		foundWork.setAverageScore(Double.toString(ave2/10));
		
		return this.convertWork(foundWork);
	}
	
	/*
	 * ワークフローの計画済ワークを探す
	 */
	public List<WorkDto> findTodoWorks(String workflowId) throws Exception{
		List<WorkDto> dtos = new ArrayList<>();
		Workflow workflow = workflowAdapter.findWorkflowOfId(workflowId);
		if(workflow == null) {
			throw new Exception("The_workflow_may_not_exist");
		}else {
			List<Work> works = registry.findWorksOfWorkflow(new WorkflowId(workflowId), WorkType.ToDo);
			
			
			dtos.addAll(covertWorks(works));
		}
		return dtos;
	}
	
	/*
	 * ワークフローの実行中ワークを探す
	 */
	public List<WorkDto> findDoingWorks(String workflowId) throws Exception{
		List<WorkDto> dtos = new ArrayList<>();
		Workflow workflow = workflowAdapter.findWorkflowOfId(workflowId);
		if(workflow == null) {
			throw new Exception("The_workflow_may_not_exist");
		}else {
			List<Work> works = registry.findWorksOfWorkflow(new WorkflowId(workflowId), WorkType.Doing);
			dtos.addAll(covertWorks(works));
		}
		return dtos;
	}
	
	/*
	 * ワークフローの完了済ワークを探す
	 */
	public List<WorkDto> findDoneWorks(String workflowId) throws Exception{
		List<WorkDto> dtos = new ArrayList<>();
		Workflow workflow = workflowAdapter.findWorkflowOfId(workflowId);
		if(workflow == null) {
			throw new Exception("The_workflow_may_not_exist");
		}else {
			List<Work> works = registry.findWorksOfWorkflow(new WorkflowId(workflowId), WorkType.Done);
			
			//平均スコアを算定する。
			for(Work work : works) {
				List<EvaluationDto> evals = evaluationService.findEvaluationsOfTarget(work.getWorkId().getWorkId());
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
				work.setAverageScore(Double.toString(ave2/10));
			}
			
			dtos.addAll(covertWorks(works));
		}
		return dtos;
	}
	
	/*
	 * ワークを作成する
	 */
	public MessageDto createWork(WorkDto work) {
		MessageDto message = new MessageDto();
		try {
			CompanyDto company = companyAdapter.findCompayOfId(work.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			Member member = memberAdapter.findMemberOfId(work.getMemberId());
			if(member == null) {
				throw new Exception("The_member_may_not_exist");
			}
			Workflow workflow = workflowAdapter.findWorkflowOfId(work.getWorkflowId());
			if(workflow == null) {
				throw new Exception("The_workflow_may_not_exist");
			}
			//ワークフローが実行中でなければワークは作成できない。
			if(!workflow.getBusinessState().equals(BusinessState.Executing)) {
				throw new Exception("The_workflow_may_not_executing");
			}
			ActionDto action = actionAdapter.findActionOfId(work.getActionId());
			if(action == null) {
				throw new Exception("The_action_may_not_exist");
			}
			Work entity = this.convertWorkDto(work);
			entity.setWorkId(registry.nextIdentity());
			entity.setWorkType(WorkType.ToDo);
			registry.save(entity);
			message.setReturnValue(entity.getWorkId().getWorkId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	/*
	 * ワークを実行する
	 */
	public MessageDto executeWork(WorkDto work) {
		MessageDto message = new MessageDto();
		try {
			CompanyDto company = companyAdapter.findCompayOfId(work.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			Member member = memberAdapter.findMemberOfId(work.getMemberId());
			if(member == null) {
				throw new Exception("The_member_may_not_exist");
			}
			Workflow workflow = workflowAdapter.findWorkflowOfId(work.getWorkflowId());
			if(workflow == null) {
				throw new Exception("The_workflow_may_not_exist");
			}
			ActionDto action = actionAdapter.findActionOfId(work.getActionId());
			if(action == null) {
				throw new Exception("The_action_may_not_exist");
			}
			Work entity = this.convertWorkDto(work);
			entity.setWorkType(WorkType.Doing);
			registry.save(entity);
			message.setReturnValue(entity.getWorkId().getWorkId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}	
	/*
	 * ワークを終了する
	 */
	public MessageDto completeWork(CompleteWorkDto work) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(work.getWorkId() == null) {
				throw new Exception("The_workId_may_not_be_set_to_null");
			}
			if(work.getWorkId().isEmpty()){
				throw new Exception("Must_provide_a_workId");
			}
			if(work.getEndDate() == null) {
				work.setEndDate(new Date());
			}
			if(work.getCompanyId() == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(work.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			if(work.getProductId() == null) {
				throw new Exception("The_productId_may_not_be_set_to_null");
			}
			if(work.getProductId().isEmpty()) {
				throw new Exception("Must_provide_a_productId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Work workEntity = registry.findWorkOfId(work.getWorkId());
			if(workEntity == null) {
				throw new Exception("The_work_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(work.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_could_not_be_found");
			}
			ProductDto product = productAdapter.findProductOfId(work.getProductId());
			if(product == null) {
				throw new Exception("The_product_could_not_be_found");
			}
			//ビジネスロジック
			//startDateとendDateより消費時間を計算する
			long startTime = workEntity.getStartDate().getTime();
			long endTime = work.getEndDate().getTime();
			//日にちの差を求めたい場合
			//long one_date_time = 1000*60*60*24;
			//long diffDay = (endTime - startTime)/one_date_time;
			//ミリ秒の差を求めたい場合
			long diffTime = endTime - startTime;
			if(diffTime < 0) {
				throw new Exception("Time_is_minus");
			}
			workEntity.setExpendedTime(diffTime);
			workEntity.setEndDate(work.getEndDate());
			//WorkをDoneにして保存する
			workEntity.setWorkType(WorkType.Done);
			registry.save(workEntity);
			//ProductWorkを作成する
			ProductWorkDto productWorkDto = new ProductWorkDto();
			List<ProductServiceDto> serviceDtos = productServiceAdapter.findProductsOfProduct(work.getProductId());
		    if(serviceDtos.size() == 0) {
		    	throw new Exception("The_product_service_could_not_be_found");
		    }
		    //ProductServiceとProductは１対１であることと前提とする
		    ProductServiceDto serviceDto = serviceDtos.get(0);
		    productWorkDto.setProductServiceId(serviceDto.getProductServiceId().getProductServiceId());
		    productWorkDto.setCompanyId(work.getCompanyId());
		    productWorkDto.setProductId(work.getProductId());
		    productWorkDto.setWorkId(work.getWorkId());
		    productWorkDto.setWorkName(workEntity.getWorkName());
		    productWorkDto.setWorkDescription(workEntity.getWorkDescription());
		    productWorkDto.setUrl(workEntity.getUrl());
		    productWorkDto.setExpendedTime(diffTime);
		    MessageDto msg = productWorkAdapter.createProductWork(productWorkDto);
		    if(msg.getResult().equals("NG")) {
		    	throw new Exception(msg.getErrorMessage());
		    }
		    message.setResult("OK");
		    
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	/*
	 * ワークを評価済にする。
	 */
	public MessageDto evaluatedWork(WorkDto work) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(work.getWorkId() == null) {
				throw new Exception("The_workId_may_not_be_set_to_null");
			}
			if(work.getWorkId().isEmpty()){
				throw new Exception("Must_provide_a_workId");
			}
			if(work.getEndDate() == null) {
				work.setEndDate(new Date());
			}
			if(work.getCompanyId() == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(work.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			
			
			
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Work workEntity = registry.findWorkOfId(work.getWorkId());
			if(workEntity == null) {
				throw new Exception("The_work_could_not_be_found");
			}
			if(!workEntity.getWorkType().equals(WorkType.Done)) {
				throw new Exception("The_work_may_not_be_done");
			}
			CompanyDto company = companyAdapter.findCompayOfId(work.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_could_not_be_found");
			}
			
			//ビジネスロジック
			workEntity.setBusinessState(BusinessState.Evaluated);
			registry.save(workEntity);
			
		    message.setResult("OK");
		    
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto completeWorkWithoutProduct(CompleteWorkDto work) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(work.getWorkId() == null) {
				throw new Exception("The_workId_may_not_be_set_to_null");
			}
			if(work.getWorkId().isEmpty()){
				throw new Exception("Must_provide_a_workId");
			}
			if(work.getEndDate() == null) {
				work.setEndDate(new Date());
			}
			if(work.getCompanyId() == null) {
				throw new Exception("The_companyId_may_not_be_set_to_null");
			}
			if(work.getCompanyId().isEmpty()) {
				throw new Exception("Must_provide_a_companyId");
			}
			
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Work workEntity = registry.findWorkOfId(work.getWorkId());
			if(workEntity == null) {
				throw new Exception("The_work_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(work.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_could_not_be_found");
			}
			
			//ビジネスロジック
			//startDateとendDateより消費時間を計算する
			long startTime = workEntity.getStartDate().getTime();
			long endTime = work.getEndDate().getTime();
			//日にちの差を求めたい場合
			//long one_date_time = 1000*60*60*24;
			//long diffDay = (endTime - startTime)/one_date_time;
			//ミリ秒の差を求めたい場合
			long diffTime = endTime - startTime;
			if(diffTime < 0) {
				throw new Exception("Time_is_minus");
			}
			workEntity.setExpendedTime(diffTime);
			workEntity.setEndDate(work.getEndDate());
			//WorkをDoneにして保存する
			workEntity.setWorkType(WorkType.Done);
			registry.save(workEntity);
			
		    message.setResult("OK");
		    
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	/*
	public MessageDto completeWork(WorkDto work) {
		MessageDto message = new MessageDto();
		try {
			CompanyDto company = companyAdapter.findCompayOfId(work.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			Member member = memberAdapter.findMemberOfId(work.getMemberId());
			if(member == null) {
				throw new Exception("The_member_may_not_exist");
			}
			Workflow workflow = workflowAdapter.findWorkflowOfId(work.getWorkflowId());
			if(workflow == null) {
				throw new Exception("The_workflow_may_not_exist");
			}
			ActionDto action = actionAdapter.findActionOfId(work.getActionId());
			if(action == null) {
				throw new Exception("The_action_may_not_exist");
			}
			Work entity = this.convertWorkDto(work);
			entity.setWorkType(WorkType.Done);
			registry.save(entity);
			message.setReturnValue(entity.getWorkId().getWorkId());
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	*/
	
	private WorkDto convertWork(Work entity) {
		WorkDto dto = new WorkDto();
		dto.setWorkId(entity.getWorkId().getWorkId());
		dto.setMemberId(entity.getMemberId().getMemberId());
		dto.setMemberName(entity.getMemberName());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setWorkflowId(entity.getWorkflowId().getWorkflowId());
		dto.setActionId(entity.getActionId().actionId());
		dto.setWorkName(entity.getWorkName());
		dto.setWorkDescription(entity.getWorkDescription());
		dto.setWorkType(entity.getWorkType());
		dto.setApplicationUrl(entity.getApplicationUrl());
		dto.setPersonUrl(entity.getPersonUrl());
		dto.setStartDate(entity.getStartDate());
		dto.setEndDate(entity.getEndDate());
		dto.setExpendedTime(entity.getExpendedTime());		
		dto.setUrl(entity.getUrl());
		dto.setBusinessState(entity.getBusinessState());
		dto.setAverageScore(entity.getAverageScore());
		return dto;
	}
	
	private List<WorkDto> covertWorks(List<Work> works){
		List<WorkDto> dtos = new ArrayList<>();
		for(Work work : works) {
			dtos.add(this.convertWork(work));
		}
		return dtos;
	}
	
	private Work convertWorkDto(WorkDto dto) {
		Work work = new Work();
		if(dto.getWorkId() != null) {
			work.setWorkId(new WorkId(dto.getWorkId()));
		}
		work.setCompanyId(new CompanyId(dto.getCompanyId()));
		work.setMemberId(new MemberId(dto.getMemberId()));
		work.setMemberName(dto.getMemberName());
		work.setActionId(new ActionId(dto.getActionId()));
		work.setWorkflowId(new WorkflowId(dto.getWorkflowId()));
		work.setWorkType(dto.getWorkType());
		work.setWorkName(dto.getWorkName());
		work.setWorkDescription(dto.getWorkDescription());
		work.setApplicationUrl(dto.getApplicationUrl());
		work.setPersonUrl(dto.getPersonUrl());
		work.setStartDate(dto.getStartDate());
		work.setEndDate(dto.getEndDate());
		work.setUrl(dto.getUrl());
		work.setBusinessState(dto.getBusinessState());	
		return work;
	}

}
