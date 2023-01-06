package com.culnou.mumu.company.domain.model.knowledge.idea;

import java.util.List;



public interface IdeaRepository {
	public Idea finIdeaOfId(IdeaId ideaId) throws Exception;
	
    public List<Idea> findIdeasOfTarget(String targetId) throws Exception;
	
	public void save(Idea idea) throws Exception;
	
	public void removeIdea(Idea idea) throws Exception;

}
