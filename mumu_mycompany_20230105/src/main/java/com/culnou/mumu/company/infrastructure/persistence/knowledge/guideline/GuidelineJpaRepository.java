package com.culnou.mumu.company.infrastructure.persistence.knowledge.guideline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.knowledge.guideline.Guideline;
import com.culnou.mumu.company.domain.model.knowledge.guideline.GuidelineId;
import com.culnou.mumu.company.domain.model.knowledge.guideline.GuidelineRepository;
@Service("guidelineJpaRepository")
@Transactional
public class GuidelineJpaRepository implements GuidelineRepository {
	
	
	@Autowired
	private GuidelineJpaDataRepository repository;

	@Override
	public List<Guideline> findGuidelinesOfTarget(String targetId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findGuidelinesOfTarget(targetId);
	}

	@Override
	public void save(Guideline guideline) throws Exception {
		// TODO Auto-generated method stub
		repository.save(guideline);

	}

	@Override
	public void removeGuideline(Guideline guideline) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(guideline);

	}

	@Override
	public Guideline finGuidelineOfId(GuidelineId guidelineId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByGuidelineId(guidelineId);
	}

}
