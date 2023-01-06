package com.culnou.mumu.company.domain.model.organization.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.JobType;
import com.culnou.mumu.company.domain.model.Role;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.infrastructure.application.CompanyServiceImpl;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.JobDto;
import com.culnou.mumu.compnay.controller.MessageDto;

@Service
@Transactional
public class JobDomainService {
	
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	private CompanyAdapter companyAdapter;
	//@Autowired
	//private JobService jobService;
	@Autowired
	private EditJobChecker editJobChecker;
	@Autowired
	private DeleteJobChecker deleteJobChecker;
	
	public MessageDto defineJob(JobDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_Job_may_not_be_set_to_null");
			}
			if(dto.getJobName() == null) {
				throw new Exception("The_jobName_may_not_be_set_to_null");
			}
			if(dto.getJobName().isEmpty()) {
				throw new Exception("Must_provide_a_jobName");
			}
			if(dto.getRoles().size() == 0) {
				throw new Exception("Must_provide_a_role");
			}
			//JobTypeは必須なのでBusinessを固定的に設定する。
			//JobTypeは必須なのでBusinessを固定的に設定する。
			if(dto.getJobType() == null) {
				dto.setJobType(JobType.Company);
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//ビジネスロジック
			companyService.addJob(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateJob(JobDto dto) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(dto == null) {
				throw new Exception("The_Job_may_not_be_set_to_null");
			}
			if(dto.getJobId() == null) {
				throw new Exception("The_jobId_may_not_be_set_to_null");
			}
			if(dto.getJobId().isEmpty()) {
				throw new Exception("Must_provide_a_jobId");
			}
			if(dto.getJobName() == null) {
				throw new Exception("The_jobName_may_not_be_set_to_null");
			}
			if(dto.getJobName().isEmpty()) {
				throw new Exception("Must_provide_a_jobName");
			}
			if(dto.getRoles().size() == 0) {
				throw new Exception("Must_provide_a_role");
			}
			//JobTypeは必須なのでBusinessを固定的に設定する。
			if(dto.getJobType() == null) {
				dto.setJobType(JobType.Company);
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(dto.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			JobDto job = companyService.findJobById(dto.getJobId());
			if(job == null) {
				throw new Exception("The_Job_could_not_be_found");
			}
			//ビジネスロジック
			//部門で使用されるなどジョブが使用されている場合、役割は変更できない。
			//ただし、アプリケーションタイプの関連の場合、役割の変更は可能。
			//if(jobService.isUsedExceptApp(new JobId(dto.getJobId()))) {
			if(!editJobChecker.editable(dto.getJobId())) {
				if(job.getRoles().size() != dto.getRoles().size()) {
					throw new Exception("Role_can_not_be_changed");
				}
				boolean check = false;
				for(Role role : job.getRoles()) {
					//System.out.println("***既存ジョブ：" + role.getRoleId());
					if(dto.getRoles().contains(role)) {
						//System.out.println("***新規ジョブ含まれる" );
					}else {
						//System.out.println("***新規ジョブ含まれない" );
						check = true;
						break;
					}
					/*
					for(Role role2 : dto.getRoles()) {
						System.out.println("***新規ジョブ：" + role2.getRoleId());
						
						if(!role.getRoleId().equals(role2.getRoleId())) {
							System.out.println("***新規ジョブNG");
							check = true;
							break;
						}
					}//新規
					*/
				}//既存
				if(check) {
					//System.out.println("***例外！" );
					throw new Exception("Role_can_not_be_changed");
				}
			}//if
			companyService.updateJob(dto);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeJob(String jobId) {
		
		MessageDto message = new MessageDto();
		try {
			if(jobId == null) {
				throw new Exception("The_jobId_may_not_be_set_to_null");
			}
			if(jobId.isEmpty()) {
				throw new Exception("Must_provide_a_jobId");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			JobDto job = companyService.findJobById(jobId);
			if(job == null) {
				throw new Exception("The_Job_could_not_be_found");
			}
			//部門で使用されるなどジョブが使用されている場合、削除できない。
			if(!deleteJobChecker.removable(jobId)) {
			//if(jobService.isUsed(new JobId(jobId))) {
				throw new Exception("The_job_can_not_be_removed");
			}
			companyService.deleteJob(jobId);
			message.setResult("OK");
		}catch(Exception ex) {
			
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	

}
