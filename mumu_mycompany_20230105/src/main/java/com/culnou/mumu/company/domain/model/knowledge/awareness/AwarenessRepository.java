package com.culnou.mumu.company.domain.model.knowledge.awareness;

import java.util.List;

import com.culnou.mumu.company.domain.model.member.MemberId;

public interface AwarenessRepository {
	
	public Awareness findAwarenessOfId(AwarenessId awarenessId) throws Exception;
	
	public List<Awareness> findAwarenessesOfMember(MemberId memberId) throws Exception;
	
	public void save(Awareness awareness) throws Exception;
	
	public void removeAwareness(Awareness awareness) throws Exception;

}
