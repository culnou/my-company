package com.culnou.mumu.company.domain.model.knowledge.evaluation;

import java.util.List;



public interface EvaluationRepository {
	
public Evaluation findEvaluationOfId(EvaluationId evaluationId) throws Exception;
	
	public List<Evaluation> findEvaluationsOfTarget(String targetId) throws Exception;
	
	public void save(Evaluation evaluation) throws Exception;
	
	public void removeEvaluation(Evaluation evaluation) throws Exception;

}
