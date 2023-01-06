package com.culnou.mumu.company.domain.model.application.function;





import com.culnou.mumu.company.domain.model.data.type.DataAccessType;
import com.culnou.mumu.compnay.controller.DataCategoryDto;

import lombok.Data;

@Data
public class ApplicationData {
	
	private DataAccessType dataAccessType;
	private DataCategoryDto dataCategory;
	

}
