package com.culnou.mumu.company.domain.model;

import java.util.Comparator;

public class BusinessProcessTaskComparator implements Comparator<AssociatedTask> {
	
	@Override
	public int compare(AssociatedTask p1, AssociatedTask p2) {
		return p1.getTaskOrder() < p2.getTaskOrder() ? -1 : 1;
	}

}
