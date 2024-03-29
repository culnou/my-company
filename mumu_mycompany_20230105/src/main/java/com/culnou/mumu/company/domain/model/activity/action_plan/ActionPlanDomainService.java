package com.culnou.mumu.company.domain.model.activity.action_plan;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;




import com.culnou.mumu.company.application.CompanyService;
import com.culnou.mumu.company.domain.model.ActionPlan;
import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.ActionPlanRepository;
import com.culnou.mumu.company.domain.model.BusinessProcess;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessProcessRepository;
import com.culnou.mumu.company.domain.model.project.Project;
import com.culnou.mumu.company.domain.model.project.ProjectId;
import com.culnou.mumu.company.domain.service.ActionPlanChecker;
import com.culnou.mumu.compnay.controller.ActionDto;
import com.culnou.mumu.compnay.controller.ActionPlanDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class ActionPlanDomainService {
	@Autowired
	private ProjectAdapter projectAdapter;
	
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ActionPlanChecker checker;
	
	@Qualifier("actionPlanJpaRepository")
	@Autowired
	private ActionPlanRepository actionPlanRepository;
	
	
	
	public MessageDto addActionPlan(ActionPlanDto dto) {
		MessageDto message = new MessageDto();
		try {
			companyService.addActionPlan(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto updateActionPlan(ActionPlanDto dto) {
		MessageDto message = new MessageDto();
		try {
			//編集・削除チェック。
			if(!checker.avarable(dto.getActionPlanId()).equals("OK")) {
				throw new Exception(checker.avarable(dto.getActionPlanId()));
			}
			companyService.updateActionPlan(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	public MessageDto removeActionPlan(String actionPlanId) {
		MessageDto message = new MessageDto();
		try {
			//編集・削除チェック。
			if(!checker.avarable(actionPlanId).equals("OK")) {
				throw new Exception(checker.avarable(actionPlanId));
			}
			ActionPlan ap = actionPlanRepository.actionPlanOfId(new ActionPlanId(actionPlanId));
			String businessProcessId = ap.getBusinessProcessId().businessProcessId();
			companyService.deleteActionPlan(actionPlanId);
			BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(new BusinessProcessId(businessProcessId));
			if(businessProcess == null) {
				throw new Exception("The_businessUnitId_is_not_exist");
			}
			/*2023/5/14
			//非同期コマンドの実行
			Command command = new Command();
			command.setCommandName(CommandName.CheckBusinessProcessUsed);
			command.getMessage().put("BusinessProcessId", businessProcessId);
			commandExecutor.execute(command);
			*/
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
		
	}
	
	

	
	/*
	 * プロジェクトのアクションプランを探す
	 */
	List<ActionPlanDto> findActionPlansOfProject(String projectId) throws Exception{
		List<ActionPlanDto> actionPlanDtos = new ArrayList<>();
		try {
			//事前条件の検証
			Project project = projectAdapter.findProjectOfId(new ProjectId(projectId));
	        if(project == null) {
	        	throw new Exception("The_project_may_not_exist");
	        }else {
	        	actionPlanDtos = companyService.findActionPlansOfProject(projectId);
	        }
		}catch(Exception ex) {
			throw new Exception("The_action_plan_could_not_be_found");
		}
		return actionPlanDtos;
	}
	/*
	 * アクションプランを策定する
	 */
	public MessageDto developActionPlan(ActionPlanDto actionPlanDto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件の検証
			Project project = projectAdapter.findProjectOfId(new ProjectId(actionPlanDto.getProjectId()));
			if(project == null) {
				message.setResult("NG");
				message.setErrorMessage("The_project_may_not_exist");
			}else {
				companyService.addActionPlan(actionPlanDto);
				message.setResult("OK");
			}
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage("You_could_not_develop_action_plan");
		}
		return message;
	}
	/*
	 * アクションプランに部門のアクションを割り当てる
	 */
	public MessageDto assignActionToActionPlan(ActionDto action, String actionPlanId) {
		MessageDto message = new MessageDto();
		try {
			companyService.assignActionToActionPlan(action, actionPlanId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto releaseActionFromActionPlan(ActionDto action, String actionPlanId) {
		MessageDto message = new MessageDto();
		try {
			companyService.releaseActionFromActionPlan(action, actionPlanId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

	

}
