package com.culnou.mumu.company.domain.model.member;

import java.io.Serializable;
import java.util.Date;



import lombok.Data;
@Data
public class MemberAssignedToDepartment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyId;
	private String departmentId;
	private String memberId;
	private Date occuredDate;

}
