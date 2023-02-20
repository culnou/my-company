package com.culnou.mumu.company.adapter.messaging;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessProcess;
import com.culnou.mumu.company.domain.model.BusinessProcessId;
import com.culnou.mumu.company.domain.model.BusinessProcessRepository;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.service.business.process.CheckBusinessProcessUsed;
import com.culnou.mumu.company.domain.service.business.unit.CheckBusinessUnitUsed;


@Service
@Transactional
public class CommandReceiver {
	
	
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Qualifier("businessProcessJpaRepository")
	@Autowired
	private BusinessProcessRepository businessProcessRepository;
	
	@Autowired
	private CheckBusinessUnitUsed checkBusinessUnitUsed;
	@Autowired
	private CheckBusinessProcessUsed checkBusinessProcessUsed;
	
	@JmsListener(destination = "commandQueue", containerFactory = "mumuFactory")
	public void receive(Command command) throws Exception{
		switch (command.getCommandName()) {
		case CheckBusinessUnitUsed:
	        /*
             * 事業単位が使用されているかチェックし、使用されていない場合、BusinessUnit#used=falseにする。
             */
			BusinessUnitId businessUnitId = new BusinessUnitId(command.getMessage().get("BusinessUnitId"));
			BusinessUnit businessUnit = businessUnitRepository.businessUnitOfId(businessUnitId);
			if(businessUnit == null) {
				throw new Exception("The_businessUnitId_is_not_exist");
			}
			if(!checkBusinessUnitUsed.check(businessUnitId)) {
				businessUnit.setUsed(false);
				businessUnitRepository.save(businessUnit);
			}
			break;
		case CheckBusinessProcessUsed:
			BusinessProcessId businessProcessId = new BusinessProcessId(command.getMessage().get("BusinessProcessId"));
			BusinessProcess businessProcess = businessProcessRepository.businessProcessOfId(businessProcessId);
			if(businessProcess == null) {
				throw new Exception("The_businessProcessId_is_not_exist");
			}
			if(!checkBusinessProcessUsed.check(businessProcessId)) {
				businessProcess.setUsed(false);
				businessProcessRepository.save(businessProcess);
			}
			break;

		default:
			break;
		}
		
		
	}
	
	

}
