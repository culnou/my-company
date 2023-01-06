package com.culnou.mumu.company.infrastructure.persistence.knowledge.evaluation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.knowledge.evaluation.Evaluation;
import com.culnou.mumu.company.domain.model.knowledge.evaluation.EvaluationId;
import com.culnou.mumu.company.domain.model.knowledge.evaluation.EvaluationRepository;

@Service("evaluationJpaRepository")
@Transactional
public class EvaluationJpaRepository implements EvaluationRepository {
	
	@Autowired
	private EvaluationJpaDataRepository repository;

	@Override
	public Evaluation findEvaluationOfId(EvaluationId evaluationId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByEvaluationId(evaluationId);
	}

	@Override
	public List<Evaluation> findEvaluationsOfTarget(String targetId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findEvaluationsOfTarget(targetId);
	}

	@Override
	public void save(Evaluation evaluation) throws Exception {
		// TODO Auto-generated method stub
		repository.save(evaluation);

	}

	@Override
	public void removeEvaluation(Evaluation evaluation) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(evaluation);

	}

}
