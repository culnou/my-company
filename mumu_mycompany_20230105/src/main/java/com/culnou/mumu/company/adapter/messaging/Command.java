package com.culnou.mumu.company.adapter.messaging;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Command {
	private CommandName commandName;
	private Map<String, String> message = new HashMap<>();

}
