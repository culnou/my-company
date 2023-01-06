package com.culnou.mumu.company.domain.model.application.type;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.application.task.ApplicationTask;
import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskRepository;

/*
 * アプリケーションタイプが削除できるかチェックするドメインサービス
 */
@Service
@Transactional
public class DeleteApplicationTypeChecker{
	
	@Qualifier("applicationTaskJpaRepository")
	@Autowired
	private ApplicationTaskRepository applicationTaskRepository;
	
	public boolean removable(String applicationTypeId) throws Exception{
		boolean check = true;
		List<ApplicationTask> tasks = applicationTaskRepository.findApplicationTasksOfApplicationType(applicationTypeId);
		if(tasks.size() > 0) {
			check = false;
		}
		return check;
	}

}
