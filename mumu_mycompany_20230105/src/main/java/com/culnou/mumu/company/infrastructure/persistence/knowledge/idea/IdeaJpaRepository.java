package com.culnou.mumu.company.infrastructure.persistence.knowledge.idea;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.knowledge.idea.Idea;
import com.culnou.mumu.company.domain.model.knowledge.idea.IdeaId;
import com.culnou.mumu.company.domain.model.knowledge.idea.IdeaRepository;


@Service("ideaJpaRepository")
@Transactional
public class IdeaJpaRepository implements IdeaRepository{
	@Autowired
	private IdeaJpaDataRepository repository;

	@Override
	public List<Idea> findIdeasOfTarget(String targetId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findIdeasOfTarget(targetId);
	}

	@Override
	public void save(Idea idea) throws Exception {
		// TODO Auto-generated method stub
		repository.save(idea);

	}

	@Override
	public void removeIdea(Idea idea) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(idea);

	}

	@Override
	public Idea finIdeaOfId(IdeaId ideaId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByIdeaId(ideaId);
	}

}
