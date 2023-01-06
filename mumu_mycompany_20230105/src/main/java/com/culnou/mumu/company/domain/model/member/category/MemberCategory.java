package com.culnou.mumu.company.domain.model.member.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Url;

import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.type.AssociatedMemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeId;
import com.culnou.mumu.company.domain.model.common.BusinessState;

import lombok.Data;

@Entity
@Table(name = "member_categories")
@XmlRootElement
@Data
public class MemberCategory {
	
	@Id
	@Embedded
	private MemberCategoryId memberCategoryId;
	@Column(name = "member_category_name")
	private String memberCategoryName;
	@Column(name = "member_category_description")
	private String memberCategoryDescription;
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private MemberTypeId memberTypeId;
	@Embedded
	private Url url;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@ElementCollection
	private List<AssociatedMemberType> associatedMemberTypes = new ArrayList<>();
	
	protected MemberCategory() {};
	
	public MemberCategory(MemberCategoryId memberCategoryId, String memberCategoryName, CompanyId companyId, BusinessUnitId businessUnitId) {
		this.setMemberCategoryId(memberCategoryId);
		this.setMemberCategoryName(memberCategoryName);
		this.setCompanyId(companyId);
		this.setBusinessUnitId(businessUnitId);
		
	}
	private void setCompanyId(CompanyId companyId) {
		if(companyId == null) {
			throw new IllegalArgumentException("The_companyId_may_not_be_set_to_null");
		}
		this.companyId = companyId;
	}
	private void setMemberCategoryId(MemberCategoryId memberCategoryId) {
		if(memberCategoryId == null) {
			throw new IllegalArgumentException("The_memberCategoryId_may_not_be_set_to_null");
		}
		this.memberCategoryId = memberCategoryId;
	}
	
	private void setBusinessUnitId(BusinessUnitId businessUnitId) {
		if(businessUnitId == null) {
			throw new IllegalArgumentException("The_businessUnitId_may_not_be_set_to_null");
		}
		this.businessUnitId = businessUnitId;
	}
	
	


}
