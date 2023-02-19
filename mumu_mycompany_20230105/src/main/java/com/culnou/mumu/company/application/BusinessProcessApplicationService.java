package com.culnou.mumu.company.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.AssociatedBusinessProcess;
import com.culnou.mumu.company.domain.model.BusinessProcess;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessProcessRepository;
import com.culnou.mumu.compnay.controller.BusinessProcessDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.TaskDto;


@Service
@Transactional
public class BusinessProcessApplicationService {
	
	@Qualifier("companyServiceImpl")
	@Autowired
	CompanyService companyService;
	
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	
	public MessageDto defineBusinessProcess(BusinessProcessDto businessProcess) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.addBusinessProcess(businessProcess);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto updateBusinessProcess(BusinessProcessDto businessProcess) throws Exception{
		MessageDto message = new MessageDto();
		try {
			
			//親プロセスの子供の名前更新
			if(businessProcess.getParent() != null) {
				BusinessProcess parent = businessProcessRepository.businessProcessOfId(new BusinessProcessId(businessProcess.getParent()));
				List<AssociatedBusinessProcess> bps = parent.getAssociatedBusinessProcesses();
				for(AssociatedBusinessProcess bp : bps) {
					if(bp.getBusinessProcessId().equals(businessProcess.getBusinessProcessId())) {
						bp.setBusinessProcessName(businessProcess.getBusinessProcessName());
						businessProcessRepository.save(parent);
						break;
					}
				}
			}
			
			companyService.updateBusinessProcess(businessProcess);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto removeBusinessProcess(String businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			BusinessProcess child = businessProcessRepository.businessProcessOfId(new BusinessProcessId(businessProcessId));
			//親プロセスの子供の削除
			if(child.getParent() != null) {
				BusinessProcess parent = businessProcessRepository.businessProcessOfId(new BusinessProcessId(child.getParent()));
				List<AssociatedBusinessProcess> bps = parent.getAssociatedBusinessProcesses();
				AssociatedBusinessProcess check = null;
				for(AssociatedBusinessProcess bp : bps) {
					if(bp.getBusinessProcessId().equals(businessProcessId)) {
						check = bp;
						break;
					}
				}
				if(check != null) {
					bps.remove(check);
					businessProcessRepository.save(parent);
				}
			}
			
			companyService.deleteBusinessProcess(businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto assignTaskToBusinessProcess(TaskDto task,String businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.assignTaskToBusinessProcess(task, businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto releaseTaskFromBusinessProcess(TaskDto task,String businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.releaseTaskFromBusinessProcess(task, businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	public MessageDto replaceTaskOrder(TaskDto source, TaskDto target, BusinessProcessId businessProcessId) throws Exception{
		MessageDto message = new MessageDto();
		try {
			companyService.replaceTaskOrder(source, target, businessProcessId);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	
	public MessageDto addSubProcess(BusinessProcessDto businessProcess,String businessProcessId, String order) throws Exception{
		MessageDto message = new MessageDto();
		try {
			BusinessProcess parent = businessProcessRepository.businessProcessOfId(new BusinessProcessId(businessProcessId));
			if(parent == null) {
				throw new Exception("The_parent_businessProcess_does_not_exist");
			}
			businessProcess.setParent(parent.getBusinessProcessId().businessProcessId());
			BusinessProcessDto childDto = companyService.addBusinessProcess(businessProcess);
			AssociatedBusinessProcess child = new AssociatedBusinessProcess();
			child.setBusinessProcessId(childDto.getBusinessProcessId());
			child.setBusinessProcessName(childDto.getBusinessProcessName());
			child.setBusinessProcessOrder(Integer.parseInt(order));
			parent.getAssociatedBusinessProcesses().add(child);
			businessProcessRepository.save(parent);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	

}
