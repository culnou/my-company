package com.culnou.mumu.company.adapter.messaging;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommandExecutor {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void execute(Command command) throws Exception{
		jmsTemplate.convertAndSend("commandQueue", command);
	}

}
