package com.culnou.mumu.company.infrastructure.persistence.knowledge.idea;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.culnou.mumu.company.domain.model.knowledge.idea.Idea;
import com.culnou.mumu.company.domain.model.knowledge.idea.IdeaId;


public interface IdeaJpaDataRepository extends JpaRepository<Idea, IdeaId> {
    public Idea findByIdeaId(IdeaId ideaId);
	
	@Query(value = "select idea from Idea idea where idea.targetId = :TargetId")
	public List<Idea> findIdeasOfTarget(@Param("TargetId") String targetId);

}
