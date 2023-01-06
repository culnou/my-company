package com.culnou.mumu.company.infrastructure.persistence.data.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.data.category.DataCategoryId;
import com.culnou.mumu.company.domain.model.data.instance.Data;
import com.culnou.mumu.company.domain.model.data.instance.DataId;
import com.culnou.mumu.company.domain.model.data.instance.DataRepository;
@Service("dataJpaRepository")
@Transactional
public class DataJpaRepository implements DataRepository {
	@Autowired
	private DataJpaDataRepository repository;

	@Override
	public void save(Data data) throws Exception {
		// TODO Auto-generated method stub
		repository.save(data);

	}

	@Override
	public void remove(Data data) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(data);

	}

	@Override
	public Data dataOfId(DataId dataId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findByDataId(dataId);
	}

	@Override
	public List<Data> dataOfCompany(CompanyId companyId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataOfCompany(companyId);
	}

	@Override
	public List<Data> dataOfDataCategory(DataCategoryId dataCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataOfDataCategory(dataCategoryId);
	}

	@Override
	public List<Data> dataOfWork(WorkId workId) throws Exception {
		// TODO Auto-generated method stub
		return repository.findDataOfWork(workId);
	}

}
