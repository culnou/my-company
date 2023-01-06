package com.culnou.mumu.company.infrastructure.persistence.knowledge.awareness;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import com.culnou.mumu.company.domain.model.knowledge.awareness.Awareness;
import com.culnou.mumu.company.domain.model.knowledge.awareness.AwarenessId;
import com.culnou.mumu.company.domain.model.member.MemberId;


public interface AwarenessJpaDataRepository extends JpaRepository<Awareness, AwarenessId> {
	
	public Awareness findByAwarenessId(AwarenessId awarenessId);
	
	@Query(value = "select awareness from Awareness awareness where awareness.memberId = :MemberId")
	public List<Awareness> findAwarenessesOfMember(@Param("MemberId") MemberId memberId);

}
