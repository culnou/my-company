package com.culnou.mumu.company.domain.model.organization.job.task;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Job;
import com.culnou.mumu.company.domain.model.JobId;
import com.culnou.mumu.company.domain.model.JobRepository;
import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskRepository;

/*
 * タスクが追加できるかチェックするドメインサービス
 */
@Service
@Transactional
public class AddTaskChecker {
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskRepository;
	@Qualifier("jobJpaRepository")
	@Autowired
	private JobRepository jobRepository;
	
	//ビジネスロジック
	//同じタスク名のタスクは追加できない。
	public boolean addable(String jobId, String taskName) throws Exception{
		boolean check = true;
		if(jobId == null || jobId.isEmpty()) {
			throw new IllegalArgumentException("The_jobId_may_not_be_set_to_null");
		}
		Job job = jobRepository.jobOfId(new JobId(jobId));
		if(job == null) {
			throw new IllegalArgumentException("The_jobId_may_not_be_wrong");
		}
		
		List<Task> tasks = taskRepository.tasksOfJob(new JobId(jobId));
		for(Task tsk : tasks) {
			if(tsk.getTaskName().equals(taskName)) {
				check = false;
				break;
			}
		}
		return check;
	}

}
