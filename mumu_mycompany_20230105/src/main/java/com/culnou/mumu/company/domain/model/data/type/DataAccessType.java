package com.culnou.mumu.company.domain.model.data.type;
//何のジョブがどのデータにどのようにアクセスするかアクセスタイプを規定する。
public enum DataAccessType {
	
	Create,//データの生成
	Read,//データの参照
	Update,//データの更新
	Delete,//データの削除
	None

}
