package com.culnou.mumu.company.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.PlaceApplicationService;
import com.culnou.mumu.company.domain.model.program.ProgramService;
import com.culnou.mumu.company.domain.model.project.ProjectService;
import com.culnou.mumu.compnay.controller.PlaceDto;
import com.culnou.mumu.compnay.controller.ProgramDto;
import com.culnou.mumu.compnay.controller.ProjectDto;

@Service
@Transactional
public class PlaceCategoryChecker {
	@Autowired
	ProjectService projectService;
	@Autowired
	ProgramService programService;
	@Autowired
	private PlaceApplicationService placeService;
	
	public String avarable(String placeCategoryId) throws Exception{
		String message = "OK";
		//プロジェクトやプログラムに割当てられている場合削除できない。
		List<ProjectDto> projects = projectService.findProjectsOfPlaceCategory(placeCategoryId);
		if(projects.size() > 0) {
			message = "already_assigned_to_projects";
		}
		List<ProgramDto> programs = programService.findProgramsOfPlaceCategory(placeCategoryId);
		if(programs.size() > 0) {
			message = "already_assigned_to_programs";
		}
		List<PlaceDto> places = placeService.findPlacesOfPlaceCategory(placeCategoryId);
		if(places.size() > 0) {
			message = "already_assigned_to_place";
		}
		return message;
	}

}
