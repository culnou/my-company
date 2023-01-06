package com.culnou.mumu.company.domain.service;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.culnou.mumu.company.domain.model.application.instance.Application;
import com.culnou.mumu.company.domain.model.application.instance.ApplicationRegistry;


@Service
@Transactional
public class ItChecker {
	/*
	@Autowired
	private ItApplicationService itService;
	@Autowired
	private PlaceApplicationService placeService;
	*/
	@Autowired
	private ApplicationRegistry applicationRegistry;
	
	
	public String avarable(String itId) throws Exception{
		String message = "OK";
		
		/*
		ItDto it = itService.findItOfId(itId);
		if(it != null) {
			if(it.getPlaceId() != null && !it.getPlaceId().isEmpty()) {
				PlaceDto place = placeService.findPlaceOfId(it.getPlaceId());
				if(place != null) {
					message = "already_assigned_to_place";
				}
			}
			
		}
		*/
		
		List<Application> apps = applicationRegistry.findApplicationsOfIt(itId);
		if(apps.size() > 0) {
			message = "already_assigned_to_application";
		}
		
		return message;
	}

}
