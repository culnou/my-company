package com.culnou.mumu.compnay.controller;

import com.culnou.mumu.company.domain.model.Achievement;

import lombok.Data;

@Data
public class ReplaceAchievement {
	
	private Achievement previous;
	private Achievement post;

}
