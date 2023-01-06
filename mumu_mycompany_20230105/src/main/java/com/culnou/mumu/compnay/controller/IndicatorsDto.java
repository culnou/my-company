package com.culnou.mumu.compnay.controller;

import com.culnou.mumu.company.domain.model.Indicator;

import lombok.Data;
@Data
public class IndicatorsDto {
	
	private Indicator previousIndicator = null;
	private Indicator postIndicator = null;

}
