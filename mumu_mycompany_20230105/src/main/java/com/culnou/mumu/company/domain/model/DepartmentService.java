package com.culnou.mumu.company.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.application.category.ApplicationCategory;

import com.culnou.mumu.company.domain.model.application.category.ApplicationCategoryRepository;
import com.culnou.mumu.company.domain.model.application.category.AssociatedApplicationCategory;
import com.culnou.mumu.company.domain.model.application.type.ApplicationType;

import com.culnou.mumu.company.domain.model.application.type.ApplicationTypeRepository;
import com.culnou.mumu.compnay.controller.ApplicationCategoryDto;

@Service
@Transactional
public class DepartmentService {
	
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("applicationTypeJpaRepository")
	@Autowired
	private ApplicationTypeRepository applicationTypeRepository;
	@Qualifier("applicationCategoryJpaRepository")
	@Autowired
	private ApplicationCategoryRepository applicationCategoryRepository;
	@Qualifier("departmentJpaRepository")
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public boolean isUsed(DepartmentId departmentId) throws Exception{
		boolean result = false;
		List<Action> actions = actionRepository.actionsOfDepartment(departmentId);
		if(actions.size() > 0) {
			result = true;
		}
		return result;
	}
	
	public boolean goalIsUsed(DepartmentId departmentId, Indicator indicator) throws Exception{
		boolean result = false;
		Department department = departmentRepository.departmentOfId(departmentId);
		List<Achievement> achievements = department.getAchievements();
		for(Achievement achievement : achievements) {
			if(achievement.getGoal().getIndicator().equals(indicator)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	public List<ApplicationCategoryDto> findAssignableApplicationsOfDepartment(String departmentId) throws Exception{
		
		//部門のジョブの取得
		Department department = departmentRepository.departmentOfId(new DepartmentId(departmentId));
		//ジョブに対するアプリケーションタイプの取得
		List<ApplicationType> applicationTypes = applicationTypeRepository.findApplicationTypesOfJob(department.getJobId().jobId());
		if(applicationTypes == null) {
			throw new IllegalArgumentException("The_applicationType_may_not_be_set_to_null");
		}
		if(applicationTypes.size() == 0) {
			throw new IllegalArgumentException("The_applicationType_may_not_be_set_to_null");
		}
		ApplicationType applicationType = applicationTypes.get(0);
		//アプリケーションタイプに対するアプリケーションカテゴリの取得
		List<ApplicationCategory> applicationCategories = applicationCategoryRepository.applicationCategoriesOfApplicationType(applicationType.getApplicationTypeId().getApplicationTypeId());
		List<ApplicationCategoryDto> dtos = new ArrayList<>();
		for(ApplicationCategory app : applicationCategories) {
			boolean check = true;
			//すでに部門に割当てられたアプリケーションカテゴリは除く。
			for(AssociatedApplicationCategory cat : department.getAssociatedApplicationCategories()) {
				if(cat.getApplicationCategoryId().equals(app.getApplicationCategoryId().getApplicationCategoryId())) {
					check = false;
					break;
				}
			}
			if(check) {
				ApplicationCategoryDto dto = new ApplicationCategoryDto();
				dto.setApplicationCategoryId(app.getApplicationCategoryId().getApplicationCategoryId());
				dto.setApplicationCategoryName(app.getApplicationCategoryName());
				if(app.getApplicationCategoryDescription() != null) {
					dto.setApplicationCategoryDescription(app.getApplicationCategoryDescription());
				}
				if(app.getBusinessUnitId() != null) {
					dto.setBusinessUnitId(app.getBusinessUnitId().businessUnitId());
				}
				if(app.getCompanyId() != null) {
					dto.setCompanyId(app.getCompanyId().id());
				}
				if(app.getBusinessState() != null) {
					dto.setBusinessState(app.getBusinessState());
				}
				if(app.getStartDate() != null) {
					dto.setStartDate(app.getStartDate());
				}
				if(app.getEndDate() != null) {
					dto.setEndDate(app.getEndDate());
				}
				app.setAchievements(app.getAchievements());
				app.setGoals(app.getGoals());
				app.setAssociatedApplicationTypes(app.getAssociatedApplicationTypes());
				dtos.add(dto);
			}
			
		}
		return dtos;
	}
	

}
