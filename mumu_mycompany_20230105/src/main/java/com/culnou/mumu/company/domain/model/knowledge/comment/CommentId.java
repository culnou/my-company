package com.culnou.mumu.company.domain.model.knowledge.comment;

import java.io.Serializable;



import lombok.Getter;
@Getter
public class CommentId implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String commentId;
	
	//JPAのEntityで値オブジェクトを使用するためにはデフォルトのコンストラクターが必要。
	protected CommentId() {}
	
	public CommentId(String commentId) {
		this.setCommentId(commentId);
	}
	
	//ドメイン以外から設定できないようにする。
	protected void setCommentId(String commentId) {
		//セッターで一貫性制約を保持する。
		if(commentId == null) {
			throw new IllegalArgumentException("The_commentId_may_not_be_set_to_null");
		}
		if(commentId.isEmpty()) {
			throw new IllegalArgumentException("Must_provide_a_commentId");
		}

		this.commentId = commentId;
	}
	
	
	
	@Override
	public CommentId clone() {
		try {
			super.clone();
		}catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
		return new CommentId(this.getCommentId());
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equality = false;
		if(object != null && this.getClass() == object.getClass()) {
			CommentId commentId = (CommentId)object;
			//必ず参照の比較（＝＝）ではなく内容の比較（equals）を行う。
			if(commentId.getCommentId().equals(this.getCommentId())){
				equality = true;
			}
		}
		return equality;
	}

}
