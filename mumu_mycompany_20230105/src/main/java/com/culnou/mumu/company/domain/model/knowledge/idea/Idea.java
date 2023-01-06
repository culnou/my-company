package com.culnou.mumu.company.domain.model.knowledge.idea;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.knowledge.idea.Idea;
import com.culnou.mumu.company.domain.model.member.MemberId;

import lombok.Data;

@Entity
@Table(name = "ideas")
@XmlRootElement
@Data
public class Idea {
	
	@Id
	@Embedded
	@NotNull
	private IdeaId ideaId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "idea_target")
	@NotNull
	private IdeaTarget ideaTarget;
	
	@Embedded
	@NotNull
	private MemberId memberId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "target_id")
	@NotNull
	@NotEmpty
	private String targetId;
	
	@Column(name = "idea_name")
	@NotNull
	@NotEmpty
	private String ideaName;
	
	@Column(name = "idea_description")
	@NotNull
	@NotEmpty
	private String ideaDescription;
	
	
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Transient
	private String averageScore;

}
