package com.culnou.mumu.company.domain.model.application.function;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.application.DataCategoryApplicationService;
import com.culnou.mumu.company.domain.model.Task;
import com.culnou.mumu.company.domain.model.TaskId;
import com.culnou.mumu.company.domain.model.TaskRepository;

import com.culnou.mumu.company.domain.model.application.task.ApplicationTaskService;
import com.culnou.mumu.company.domain.model.data.type.AssociatedDataType;
import com.culnou.mumu.compnay.controller.ApplicationTaskDto;
import com.culnou.mumu.compnay.controller.DataCategoryDto;

@Service
@Transactional
public class ApplicationFunctionService {
	@Autowired
	private ApplicationFunctionRegistry registry;
	@Autowired
	private ApplicationTaskService applicationTaskService;
	@Qualifier("taskJpaRepository")
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private DataCategoryApplicationService dataCategoryService;
	
	//アプリケーション機能がアクセスすべきデータとアクセス方法を取得する
	public List<ApplicationData> findApplicationData(String applicationFunctionId) throws Exception{
		List<ApplicationData> data = new ArrayList<>();
		ApplicationFunction applicationFunction = registry.applicationFunctionOfId(new ApplicationFunctionId(applicationFunctionId));
		ApplicationTaskDto applicationTask = applicationTaskService.findApplicationTasksOfId(applicationFunction.getApplicationTaskId().getApplicationTaskId());
		Task task = taskRepository.TaskOfId(new TaskId(applicationTask.getTaskId()));
		List<AssociatedDataType> dataTypes = task.getAssociatedDataTypes();
		for(AssociatedDataType dataType : dataTypes) {
			List<DataCategoryDto> dtos = dataCategoryService.findDataCategoriesOfDataType(dataType.getDataTypeId());
			//データカテゴリごとの処理
			for(DataCategoryDto dto : dtos) {
				boolean check = true;
				//データカテゴリの重複を除く。
				//同じデータカテゴリであってもデータアクセス方法（CRUD）が異なる場合があるので以下のロジックは削除。
				/*
				for(ApplicationData datus : data) {
					if(datus.getDataCategory().getDataCategoryId().equals(dto.getDataCategoryId())) {
						check = false;
						break;
					}
				}
				*/
				if(check) {
					ApplicationData appData = new ApplicationData();
					//データカテゴリごとにデータアクセス方法をセット
					appData.setDataAccessType(dataType.getDataAccessType());
					//データカテゴリのセット
					appData.setDataCategory(dto);
					//データの追加
					data.add(appData);
				}
			}
		}
		return data;
	}

}
