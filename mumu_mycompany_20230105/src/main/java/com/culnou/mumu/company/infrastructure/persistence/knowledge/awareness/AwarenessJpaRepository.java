package com.culnou.mumu.company.infrastructure.persistence.knowledge.awareness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.knowledge.awareness.Awareness;
import com.culnou.mumu.company.domain.model.knowledge.awareness.AwarenessId;
import com.culnou.mumu.company.domain.model.knowledge.awareness.AwarenessRepository;
import com.culnou.mumu.company.domain.model.member.MemberId;

@Service("awarenessJpaRepository")
@Transactional
public class AwarenessJpaRepository implements AwarenessRepository{
	
	@Autowired
	private AwarenessJpaDataRepository repository;

	@Override
	public List<Awareness> findAwarenessesOfMember(MemberId memberId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findAwarenessesOfMember(memberId);
	}

	

	@Override
	public void removeAwareness(Awareness awareness) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(awareness);
		
	}

	@Override
	public void save(Awareness awareness) throws Exception {
		// TODO Auto-generated method stub
		repository.save(awareness);
		
	}



	@Override
	public Awareness findAwarenessOfId(AwarenessId awarenessId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByAwarenessId(awarenessId);
	}

}
