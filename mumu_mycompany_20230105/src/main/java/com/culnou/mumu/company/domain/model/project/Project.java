package com.culnou.mumu.company.domain.model.project;



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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.AssociatedCustomerCategory;
import com.culnou.mumu.company.domain.model.AssociatedProductCategory;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.activity.action_plan.AssociatedActionPlan;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.category.AssociatedDataCategory;
import com.culnou.mumu.company.domain.model.financial.asset.category.AssociatedFinancialAssetCategory;
import com.culnou.mumu.company.domain.model.member.category.AssociatedMemberCategory;
import com.culnou.mumu.company.domain.model.partner.category.AssociatedPartnerCategory;
import com.culnou.mumu.company.domain.model.place.category.AssociatedPlaceCategory;

import lombok.Data;

@Entity
@Table(name = "projects")
@XmlRootElement
@Data
public class Project {
	
	@Id
	@Embedded
	@NotNull
	private ProjectId projectId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private BusinessUnitId businessUnitId;
	
	@NotNull
	@NotEmpty
	@Column(name = "project_name")
	
	private String projectName;
	
	@Column(name = "project_description")
	private String projectDescription;
	
	@Column(name = "vision")
	private String vision;
	
	@Column(name = "hypothesis")
	private String hypothesis;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "slogan")
	private String slogan;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "startdate")
	private Date startDate;
	
	@Column(name = "enddate")
	private Date endDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;
	
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	
	
	
	//プロジェクトに関連する事業単位の資産
	@ElementCollection
	private List<AssociatedCustomerCategory> associatedCustomerCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedProductCategory> associatedProductCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedMemberCategory> associatedMemberCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedPartnerCategory> associatedPartnerCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedDataCategory> associatedDataCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedApplicationCategory> associatedApplicationCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedPlaceCategory> associatedPlaceCategories = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedActionPlan> associatedActionPlans = new ArrayList<>();
	
	@ElementCollection
	private List<AssociatedFinancialAssetCategory> associatedFinancialAssetCategories = new ArrayList<>();
	
	public void addCustomerCategory(AssociatedCustomerCategory customerCategory) throws Exception{
		if(customerCategory == null) {
			throw new IllegalArgumentException("The_customerCategory_may_not_be_set_to_null");
		}
		this.getAssociatedCustomerCategories().add(customerCategory);
	}
	
	public void removeCustomerCategory(AssociatedCustomerCategory customerCategory) throws Exception{
		if(customerCategory == null) {
			throw new IllegalArgumentException("The_customerCategory_may_not_be_set_to_null");
		}
		this.getAssociatedCustomerCategories().remove(customerCategory);
	}
	
	public void addActionPlan(AssociatedActionPlan actionPlan) throws Exception{
		if(actionPlan == null) {
			throw new IllegalArgumentException("The_actionPlan_may_not_be_set_to_null");
		}
		this.getAssociatedActionPlans().add(actionPlan);
	}
	
	public void removeActionPlan(AssociatedActionPlan actionPlan) throws Exception{
		if(actionPlan == null) {
			throw new IllegalArgumentException("The_actionPlan_may_not_be_set_to_null");
		}
		this.getAssociatedActionPlans().remove(actionPlan);
	}
	
	public void addProductCategory(AssociatedProductCategory productCategory) throws Exception{
		if(productCategory == null) {
			throw new IllegalArgumentException("The_productCategory_may_not_be_set_to_null");
		}
		this.getAssociatedProductCategories().add(productCategory);
	}
	
	public void removeProductCategory(AssociatedProductCategory productCategory) throws Exception{
		if(productCategory == null) {
			throw new IllegalArgumentException("The_productCategory_may_not_be_set_to_null");
		}
		this.getAssociatedProductCategories().remove(productCategory);
	}
	
	public void addMemberCategory(AssociatedMemberCategory memberCategory) throws Exception{
		if(memberCategory == null) {
			throw new IllegalArgumentException("The_memberCategory_may_not_be_set_to_null");
		}
		this.getAssociatedMemberCategories().add(memberCategory);
	}
	
	public void removeMemberCategory(AssociatedMemberCategory memberCategory) throws Exception{
		if(memberCategory == null) {
			throw new IllegalArgumentException("The_memberCategory_may_not_be_set_to_null");
		}
		this.getAssociatedMemberCategories().remove(memberCategory);
	}
	
	public void addPartnerCategory(AssociatedPartnerCategory partnerCategory) throws Exception{
		if(partnerCategory == null) {
			throw new IllegalArgumentException("The_partnerCategory_may_not_be_set_to_null");
		}
		this.getAssociatedPartnerCategories().add(partnerCategory);
	}
	
	public void removePartnerCategory(AssociatedPartnerCategory partnerCategory) throws Exception{
		if(partnerCategory == null) {
			throw new IllegalArgumentException("The_partnerCategory_may_not_be_set_to_null");
		}
		this.getAssociatedPartnerCategories().remove(partnerCategory);
	}
	
	public void addPlaceCategory(AssociatedPlaceCategory placeCategory) throws Exception{
		if(placeCategory == null) {
			throw new IllegalArgumentException("The_placeCategory_may_not_be_set_to_null");
		}
		this.getAssociatedPlaceCategories().add(placeCategory);
	}
	
	public void removePlaceCategory(AssociatedPlaceCategory placeCategory) throws Exception{
		if(placeCategory == null) {
			throw new IllegalArgumentException("The_placeCategory_may_not_be_set_to_null");
		}
		this.getAssociatedPlaceCategories().remove(placeCategory);
	}
	
	public void addDataCategory(AssociatedDataCategory dataCategory) throws Exception{
		if(dataCategory == null) {
			throw new IllegalArgumentException("The_dataCategory_may_not_be_set_to_null");
		}
		this.getAssociatedDataCategories().add(dataCategory);
	}
	
	public void removeDataCategory(AssociatedDataCategory dataCategory) throws Exception{
		if(dataCategory == null) {
			throw new IllegalArgumentException("The_dataCategory_may_not_be_set_to_null");
		}
		this.getAssociatedDataCategories().remove(dataCategory);
	}
	
	public void addApplicationCategory(AssociatedApplicationCategory applicationCategory) throws Exception{
		if(applicationCategory == null) {
			throw new IllegalArgumentException("The_applicationCategory_may_not_be_set_to_null");
		}
		this.getAssociatedApplicationCategories().add(applicationCategory);
	}
	
	public void removeApplicationCategory(AssociatedApplicationCategory applicationCategory) throws Exception{
		if(applicationCategory == null) {
			throw new IllegalArgumentException("The_applicationCategory_may_not_be_set_to_null");
		}
		this.getAssociatedApplicationCategories().remove(applicationCategory);
	}
	
	public void addFinancialAssetCategory(AssociatedFinancialAssetCategory financialAssetCategory) throws Exception{
		if(financialAssetCategory == null) {
			throw new IllegalArgumentException("The_financialAssetCategory_may_not_be_set_to_null");
		}
		this.getAssociatedFinancialAssetCategories().add(financialAssetCategory);
	}
	
	public void removeFinancialAssetCategory(AssociatedFinancialAssetCategory financialAssetCategory) throws Exception{
		if(financialAssetCategory == null) {
			throw new IllegalArgumentException("The_financialAssetCategory_may_not_be_set_to_null");
		}
		this.getAssociatedFinancialAssetCategories().remove(financialAssetCategory);
	}
	
	public void addGoal(Goal goal) throws Exception{
		if(goal == null) {
			throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
		}
		this.getGoals().add(goal);
	}
	
	public void replaceGoal(Goal previous, Goal post) throws Exception{
		if(previous == null || post == null) {
			throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
		}
		this.getGoals().set(this.getGoals().indexOf(previous) ,post);
	}
	
	
	
	public void removeGoal(Goal goal) throws Exception{
		if(goal == null) {
			throw new IllegalArgumentException("The_goal_may_not_be_set_to_null");
		}
		this.getGoals().remove(goal);
	}
	
	public void addAchievement(Achievement achievement) throws Exception{
		if(achievement == null) {
			throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
		}
		this.getAchievements().add(achievement);
	}
	
	public void replaceAchievement(Achievement previous, Achievement post) throws Exception{
		if(previous == null || post == null) {
			throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
		}
		this.getAchievements().set(this.getAchievements().indexOf(previous) ,post);
	}
	
	public void removeAchievement(Achievement achievement) throws Exception{
		if(achievement == null) {
			throw new IllegalArgumentException("The_achievement_may_not_be_set_to_null");
		}
		this.getAchievements().remove(achievement);
	}
	
	
	

}
