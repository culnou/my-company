package com.culnou.mumu.company.infrastructure.persistence;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.Address;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.DomainName;
import com.culnou.mumu.company.domain.model.IndustryGroup;
import com.culnou.mumu.company.domain.model.common.Email;

import lombok.Data;

@Entity
@Table(name = "companies")
@XmlRootElement
@Data
public class CompanyEntity {
	
	//@Embeddすることで値オブジェクトをEntityで使用することができる。
	//主キーの場合は@Idをつける。以下参照。
    //https://qiita.com/shotana/items/906d0596ce152fe3c6c1	
	@Id
	@Embedded
	private CompanyId companyId;
	@Embedded
	private DomainName domainName;
	@Embedded
	private Address address;
	@Embedded
	private Email email;
	@Embedded
	private IndustryGroup industryGroup;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "purpose")
	private String purpose;
	@Column(name = "company_password")
	private String companyPassword;
	@Column(name = "ea_name")
	private String eaName;
	@Column(name = "ea_password")
	private String eaPassword;
	
	

}
