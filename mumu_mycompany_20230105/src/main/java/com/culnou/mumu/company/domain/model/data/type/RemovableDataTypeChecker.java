package com.culnou.mumu.company.domain.model.data.type;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Transactional
//ドメインサービス
public class RemovableDataTypeChecker {
	
	@Qualifier("dataTypeJpaRepository")
	@Autowired
	private DataTypeRepository repository;
	
	public boolean removable(DataType dataType) throws Exception{
		boolean check = true;
		if(dataType == null) {
			throw new IllegalArgumentException("The_dataType_may_not_be_set_to_null");
		}
		List<DataType> children = repository.findChildrenOfParent(dataType.getDataTypeId().getDataTypeId());
		if(children.size() > 0) {
		  check = false;	
		}
		return check;
	}

}
