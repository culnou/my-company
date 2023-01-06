package com.culnou.mumu.compnay.controller;

import com.culnou.mumu.company.domain.model.Goal;

import lombok.Data;

@Data
public class ReplaceGoal {
	
	private Goal previous;
	private Goal post;

}
