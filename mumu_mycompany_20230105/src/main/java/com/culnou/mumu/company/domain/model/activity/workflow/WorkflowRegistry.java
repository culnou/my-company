package com.culnou.mumu.company.domain.model.activity.workflow;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ActionPlanId;
import com.culnou.mumu.company.domain.model.program.ProgramId;
import com.culnou.mumu.company.domain.model.project.ProjectId;


@Service
@Transactional
public class WorkflowRegistry {
	
	
	@Qualifier("workflowJpaRepository")
	@Autowired
	private WorkflowRepository repository;
	
	
	
	protected WorkflowId nextIdentity() {
		UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
		return new WorkflowId(str);
	}
	
	protected Workflow findWorkflowOfId(String workflowId) throws Exception{
		return repository.findWorkflowOfId(new WorkflowId(workflowId));
	}
	
	protected List<Workflow> findWorkflowsOfActionPlan(ActionPlanId actionPlanId) throws Exception{
		return repository.findWorkflowsOfActionPlan(actionPlanId);
	}
	
	protected List<Workflow> findWorkflowsOfProject(ProjectId projectId) throws Exception{
		return repository.findWorkflowsOfProject(projectId);
	}
	
	protected List<Workflow> findWorkflowsOfProgram(ProgramId programId) throws Exception{
		return repository.findWorkflowsOfProgram(programId);
	}
	
	protected void remove(Workflow workflow) throws Exception{
		repository.remove(workflow);
	}
	
	protected void registerWorkflow(Workflow workflow) throws Exception{
		//エンティティ整合性が保持できているかチェックする
		//Java Validationを使う
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
		Set<ConstraintViolation<Workflow>> violations = validator.validate(workflow);
		if (!violations.isEmpty()) {
			throw new Exception("entity_validation_error");
		  //Server Erroになるので使わない。
		  //throw new ConstraintViolationException(violations);
		}//if
		repository.save(workflow);
	}

}
