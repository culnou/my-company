package com.culnou.mumu.compnay.controller;

import lombok.Data;

@Data
public class MessageDto {
	
	private String result;
	private String errorCode;
	private String errorMessage;
	private String returnValue;
	private String loginAddress;

}
