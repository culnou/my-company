package com.culnou.mumu.company.domain.model.knowledge.awareness;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.member.MemberId;

import lombok.Data;

@Entity
@Table(name = "awarenesses")
@XmlRootElement
@Data
public class Awareness {
	
	@Id
	@Embedded
	@NotNull
	private AwarenessId awarenessId;
	
	@Embedded
	@NotNull
	private CompanyId companyId;
	
	@Embedded
	@NotNull
	private MemberId memberId;
	
	@Column(name = "awareness_name")
	@NotNull
	@NotEmpty
	private String awarenessName;
	
	@Column(name = "awareness_description")
	private String awarenessDescription;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}
