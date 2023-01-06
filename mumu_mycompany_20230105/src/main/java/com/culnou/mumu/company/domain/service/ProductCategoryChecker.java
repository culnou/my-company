package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.ProductCategoryId;

import com.culnou.mumu.company.domain.model.ProductFunction;
import com.culnou.mumu.company.domain.model.ProductFunctionRepository;
import com.culnou.mumu.company.domain.model.program.ProgramService;
import com.culnou.mumu.company.domain.model.project.ProjectService;
import com.culnou.mumu.compnay.controller.ProgramDto;
import com.culnou.mumu.compnay.controller.ProjectDto;

@Service
@Transactional
public class ProductCategoryChecker {
	
	@Autowired
	ProjectService projectService;
	@Autowired
	ProgramService programService;
	@Qualifier("productFunctionJpaRepository")
	@Autowired
	private ProductFunctionRepository productFunctionRepository;
	
	public String avarable(String productCategoryId) throws Exception{
		String message = "OK";
		//プロジェクトやプログラムに割当てられている場合削除できない。
		List<ProjectDto> projects = projectService.findProjectsOfProductCategory(productCategoryId);
		if(projects.size() > 0) {
			message = "already_assigned_to_projects";
		}
		List<ProgramDto> programs = programService.findProgramsOfProductCategory(productCategoryId);
		if(programs.size() > 0) {
			message = "already_assigned_to_programs";
		}
		List<ProductFunction> productFunctions = productFunctionRepository.productFunctionsOfProductCategory(new ProductCategoryId(productCategoryId));
		if(productFunctions.size() > 0) {
			message = "already_assigned_to_productFunction";
		}
		return message;
	}

}
