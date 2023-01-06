package com.culnou.mumu.company.domain.model.knowledge.comment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;


import lombok.Data;
@Entity
@Table(name = "comments")
@XmlRootElement
@Data
public class Comment {
	
	@Id
	@Embedded
	@NotNull
	private CommentId commentId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "comment_target")
	@NotNull
	private CommentTarget commentTarget;
	
	@Column(name = "target_id")
	@NotNull
	@NotEmpty
	private String targetId;
	
	@Column(name = "comment_name")
	@NotNull
	@NotEmpty
	private String commentName;
	
	@Column(name = "comment_description")
	@NotNull
	@NotEmpty
	private String commentDescription;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
