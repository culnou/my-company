package com.culnou.mumu.company.infrastructure.persistence.knowledge.evaluation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.knowledge.evaluation.Evaluation;
import com.culnou.mumu.company.domain.model.knowledge.evaluation.EvaluationId;

public interface EvaluationJpaDataRepository extends JpaRepository<Evaluation, EvaluationId> {
	
    public Evaluation findByEvaluationId(EvaluationId evaluationId);
	
	@Query(value = "select evaluation from Evaluation evaluation where evaluation.targetId = :TargetId")
	public List<Evaluation> findEvaluationsOfTarget(@Param("TargetId") String targetId);

}
