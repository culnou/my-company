package com.culnou.mumu.company.infrastructure.persistence.data.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.data.item.DataItem;
import com.culnou.mumu.company.domain.model.data.item.DataItemId;
import com.culnou.mumu.company.domain.model.data.item.DataItemRepository;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;

@Service("dataItemJpaRepository")
@Transactional
public class DataItemJpaRepository implements DataItemRepository {
	
	@Autowired
	private DataItemJpaDataRepository repository;

	@Override
	public DataItem findDataItemOfId(DataItemId dataItemId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findBydataItemId(dataItemId);
	}

	@Override
	public List<DataItem> findDataItemsOfCompany(String companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataItemsOfCompany(new CompanyId(companyId));
	}

	@Override
	public List<DataItem> findDataItemsOfDataType(String dataTypeId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataItemsOfDataType(new DataTypeId(dataTypeId));
	}

	@Override
	public void save(DataItem dataItem) throws Exception {
		// TODO Auto-generated method stub
		repository.save(dataItem);

	}

	@Override
	public void remove(DataItem dataItem) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(dataItem);

	}

}
