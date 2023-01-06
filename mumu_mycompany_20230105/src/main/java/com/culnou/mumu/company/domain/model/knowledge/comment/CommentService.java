package com.culnou.mumu.company.domain.model.knowledge.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;

import com.culnou.mumu.compnay.controller.CommentDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;


@Service
@Transactional
public class CommentService {
	
	@Autowired
	private CommentRegistry registry;
	@Autowired
	private CompanyAdapter companyAdapter;
	
	/*
	 * ターゲットのコメントを探す
	 */
	public List<CommentDto> findCommentsOfTarget(String targetId) throws Exception{
		if(targetId == null) {
			throw new Exception("The_targetId_may_not_be_set_to_null");
		}
		if(targetId.isEmpty()) {
			throw new Exception("Must_provide_a_targetId");
		}
		return this.convertComments(registry.findCommentsOfTarget(targetId));
	}
	
	/*
	 * コメントを登録する
	 */
	public MessageDto registerComment(CommentDto comment) {
		
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(comment == null) {
				throw new Exception("The_comment_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(comment.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			//ビジネスロジック
			Comment entity = this.convertCommentDto(comment);
			entity.setCommentId(registry.nextIdentity());
			entity.setCreatedAt(new Date());
			
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	/*
	 * コメントを更新する
	 */
	public MessageDto updateComment(CommentDto comment) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(comment == null) {
				throw new Exception("The_comment_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Comment result = registry.findCommentOfId(comment.getCommentId());
			if(result == null) {
				throw new Exception("The_comment_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(comment.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			
			//更新のビジネスロジック
			//必要な属性だけ更新する
			Comment entity = this.convertCommentDto(comment);
			entity.setCommentId(result.getCommentId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
			entity.setTargetId(comment.getTargetId());
			entity.setCreatedAt(comment.getCreatedAt());			
			entity.setUpdatedAt(new Date());
			registry.save(entity);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	/*
	 * コメントを削除する
	 */
	public MessageDto removeComment(String commentId) {
		MessageDto message = new MessageDto();
		try {
			Comment comment = registry.findCommentOfId(commentId);
			if(comment == null) {
				throw new Exception("The_comment_could_not_be_found");
			}
			registry.remove(comment);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}

	
	
	private CommentDto convertComment(Comment entity) {
		CommentDto dto = new CommentDto();
		dto.setCommentId(entity.getCommentId().getCommentId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setCommentTarget(entity.getCommentTarget());
		dto.setTargetId(entity.getTargetId());
		dto.setCommentName(entity.getCommentName());
		dto.setCommentDescription(entity.getCommentDescription());
		dto.setUrl(entity.getUrl());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<CommentDto> convertComments(List<Comment> entities){
		List<CommentDto> dtos = new ArrayList<>();
		for(Comment entity : entities) {
			dtos.add(this.convertComment(entity));
		}
		return dtos;
	}
	
	private Comment convertCommentDto(CommentDto dto) {
		Comment entity = new Comment();
		if(dto.getCommentId() != null && !dto.getCommentId().isEmpty()) {
			entity.setCommentId(new CommentId(dto.getCommentId()));
		}
		entity.setCommentName(dto.getCommentName());
		entity.setCommentDescription(dto.getCommentDescription());
		entity.setCommentTarget(dto.getCommentTarget());
		entity.setTargetId(dto.getTargetId());
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		entity.setUrl(dto.getUrl());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}

}
