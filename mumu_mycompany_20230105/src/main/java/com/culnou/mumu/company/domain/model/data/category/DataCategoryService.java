package com.culnou.mumu.company.domain.model.data.category;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.Action;

import com.culnou.mumu.company.domain.model.ActionRepository;

import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskRepository;
import com.culnou.mumu.company.domain.model.activity.work.Work;
import com.culnou.mumu.company.domain.model.activity.work.WorkId;
import com.culnou.mumu.company.domain.model.activity.work.WorkRepository;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;
import com.culnou.mumu.company.domain.model.data.type.DataAccessType;
import com.culnou.mumu.company.domain.model.data.type.DataClass;
import com.culnou.mumu.company.domain.model.data.type.DataType;
import com.culnou.mumu.company.domain.model.data.type.DataTypeId;
import com.culnou.mumu.company.domain.model.data.type.DataTypeRepository;


@Service
@Transactional
public class DataCategoryService {
	@Qualifier("workJpaRepository")
	@Autowired
	private WorkRepository workRepository;
	@Qualifier("actionJpaRepository")
	@Autowired
	private ActionRepository actionRepository;
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskRepository;
	@Qualifier("dataTypeJpaRepository")
	@Autowired
	private DataTypeRepository dataTypeRepository;
	@Qualifier("dataCategoryJpaRepository")
	@Autowired
	private DataCategoryRepository dataCategoryRepository;
	
	/*
	 * ワークの成果物（アウトプット）候補となるデータカテゴリを探す。
	 */
	public List<DataCategory> findDeliverables(String workId) throws Exception{
		List<DataCategory> deliverables = new ArrayList<>();
		//ワークに対するアクションを探す。
		Work work = workRepository.findWorkOfId(new WorkId(workId));
		if(work == null) {
			throw new IllegalArgumentException("The_work_dose_not_exist");
		}
		Action action = actionRepository.actionOfId(work.getActionId());
		//アクションに対するタスクを探す。
		Task task = taskRepository.TaskOfId(action.getTaskId());
		//タスクに対するマネジメントデータのデータタイプで、アクセス方法がCreateのものを探す。
		List<AssociatedDataType> associatedDataTypes = task.getAssociatedDataTypes();
		for(AssociatedDataType associatedDataType : associatedDataTypes) {
			DataType dataType = dataTypeRepository.findDataTypeOfId(new DataTypeId(associatedDataType.getDataTypeId()));
			if(associatedDataType.getDataAccessType().equals(DataAccessType.Create)) {
				if(dataType.getDataClass().equals(DataClass.ManagementData)) {
					//データタイプに対するデータカテゴリを探す。
					List<DataCategory> dataCategories = dataCategoryRepository.dataCategoriesOfDataType(dataType.getDataTypeId().getDataTypeId());
					for(DataCategory dataCategory : dataCategories) {
						//有効なデータカテゴリのみ対象にする。
						if(dataCategory.getBusinessState().equals(BusinessState.Executing)) {
							deliverables.add(dataCategory);
						}
					}
				}
			}
		}
		
		return deliverables;
	}
	
	/*
	 * ワークのインプットとなるデータカテゴリを探す。
	 */
	public List<DataCategory> findInputData(String workId) throws Exception{
		List<DataCategory> deliverables = new ArrayList<>();
		//ワークに対するアクションを探す。
		Work work = workRepository.findWorkOfId(new WorkId(workId));
		Action action = actionRepository.actionOfId(work.getActionId());
		//アクションに対するタスクを探す。
		Task task = taskRepository.TaskOfId(action.getTaskId());
		//タスクに対するマネジメントデータのデータタイプで、アクセス方法がReadのものを探す。
		List<AssociatedDataType> associatedDataTypes = task.getAssociatedDataTypes();
		for(AssociatedDataType associatedDataType : associatedDataTypes) {
			DataType dataType = dataTypeRepository.findDataTypeOfId(new DataTypeId(associatedDataType.getDataTypeId()));
			if(associatedDataType.getDataAccessType().equals(DataAccessType.Read)) {
				if(dataType.getDataClass().equals(DataClass.ManagementData)) {
					//データタイプに対するデータカテゴリを探す。
					List<DataCategory> dataCategories = dataCategoryRepository.dataCategoriesOfDataType(dataType.getDataTypeId().getDataTypeId());
					for(DataCategory dataCategory : dataCategories) {
						//有効なデータカテゴリのみ対象にする。
						if(dataCategory.getBusinessState().equals(BusinessState.Executing)) {
							deliverables.add(dataCategory);
						}
					}
				}
			}
		}
		
		return deliverables;
	}

}
