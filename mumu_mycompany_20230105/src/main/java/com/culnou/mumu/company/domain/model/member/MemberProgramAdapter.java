package com.culnou.mumu.company.domain.model.member;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.program.Program;
import com.culnou.mumu.company.domain.model.program.ProgramService;

@Service
@Transactional
public class MemberProgramAdapter {
	
	@Autowired
	private ProgramService programService;
	
	/*
	 * プロジェクトが所属する事業単位を探す
	 */
	BusinessUnitId findBusinessUnitOfProgram(String programId) throws Exception{
		Program program = programService.findProgramOfId(programId);
		if(program == null) {
			throw new Exception("The_program_may_not_exist");
		}
		return program.getBusinessUnitId();
	}
	
	Program findProgramOfId(String programId) throws Exception{
		Program program = programService.findProgramOfId(programId);
		if(program == null) {
			throw new Exception("The_program_may_not_exist");
		}
		return program;
	}

}
