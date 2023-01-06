package com.culnou.mumu.company.domain.model.product.work;



import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


import lombok.Data;
@Entity
@Table(name = "product_works")
@XmlRootElement
@Data
public class ProductWork {
	@Id
	@Embedded
	@NotNull
	private ProductWorkId productWorkId;
	@Column(name = "company_id")
	@NotNull
	@NotEmpty
	private String companyId;
	@Column(name = "product_id")
	@NotNull
	@NotEmpty
	private String productId;
	@Column(name = "product_service_id")
	@NotNull
	@NotEmpty
	private String productServiceId;
	@Column(name = "work_id")
	@NotNull
	@NotEmpty
	private String workId;
	@Column(name = "work_name")
	@NotNull
	@NotEmpty
	private String workName;
	@Column(name = "work_description")
	private String workDescription;
	@Column(name = "url")
	private String url;
	@Column(name = "expended_time")
	private long expendedTime;

}
