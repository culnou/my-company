package com.culnou.mumu.company.domain.model.knowledge.guideline;

import java.util.List;





public interface GuidelineRepository {
	
	public Guideline finGuidelineOfId(GuidelineId guidelineId) throws Exception;
	
    public List<Guideline> findGuidelinesOfTarget(String targetId) throws Exception;
	
	public void save(Guideline guideline) throws Exception;
	
	public void removeGuideline(Guideline guideline) throws Exception;

}
