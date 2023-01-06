package com.culnou.mumu.company.domain.model;

import java.util.List;

public interface TaskRepository {

	//識別子オブジェクトはリポジトリが生成する。
	public TaskId nextIdentity() throws Exception;
	
	//コマンド
    public void save(Task task) throws Exception;
	
	public void remove(Task task) throws Exception;
	
	//クエリ
	public Task TaskOfId(TaskId taskId) throws Exception;
	
	public List<Task> tasksOfCompany(CompanyId companyId) throws Exception;
	
	public List<Task> tasksOfJob(JobId jobId) throws Exception;

}
