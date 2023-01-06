package com.culnou.mumu.company.infrastructure.persistence.knowledge.guideline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.knowledge.guideline.Guideline;
import com.culnou.mumu.company.domain.model.knowledge.guideline.GuidelineId;

public interface GuidelineJpaDataRepository extends JpaRepository<Guideline, GuidelineId> {
	
    public Guideline findByGuidelineId(GuidelineId guidelineId);
	
	@Query(value = "select guideline from Guideline guideline where guideline.targetId = :TargetId")
	public List<Guideline> findGuidelinesOfTarget(@Param("TargetId") String targetId);

}
