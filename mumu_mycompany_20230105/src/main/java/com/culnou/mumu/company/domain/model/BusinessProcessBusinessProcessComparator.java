package com.culnou.mumu.company.domain.model;

import java.util.Comparator;

public class BusinessProcessBusinessProcessComparator implements Comparator<AssociatedBusinessProcess> {
	
	@Override
	public int compare(AssociatedBusinessProcess p1, AssociatedBusinessProcess p2) {
		return p1.getBusinessProcessOrder() < p2.getBusinessProcessOrder() ? -1 : 1;
	}

}
