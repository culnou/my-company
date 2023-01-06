package com.culnou.mumu.company.infrastructure.persistence;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.AssociatedCustomerType;
import com.culnou.mumu.company.domain.model.AssociatedProductType;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.Indicator;
import com.culnou.mumu.company.domain.model.Industry;
import com.culnou.mumu.company.domain.model.IndustryGroup;
import com.culnou.mumu.company.domain.model.IndustrySubGroup;
import com.culnou.mumu.company.domain.model.ProductTypeId;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.AssociatedUrl;

import lombok.Data;

@Entity
@Table(name = "business_domains")
@XmlRootElement
@Data
public class BusinessDomainEntity {
	//@Embeddすることで値オブジェクトをEntityで使用することができる。
	//主キーの場合は@Idをつける。以下参照。
    //https://qiita.com/shotana/items/906d0596ce152fe3c6c1	
	@Id
	@Embedded
	BusinessDomainId businessDomainId;
	@Embedded
	CompanyId companyId;
	@Embedded
	IndustryGroup industryGroup;
	@Embedded
	//@NotNull
	Industry industry;
	@Embedded
	IndustrySubGroup industrySubGroup;
	@Column(name = "business_domain_name")
	@NotNull
	private String businessDomainName;
	@Column(name = "enterprise")
	private boolean enterprise;
	@Column(name = "purpose")
	//@NotNull
	private String purpose;
	@Embedded
	private Url url;
	@Column(name = "business_model")
	private String businessModel;
	@Column(name = "business_domain_customer_Type")
	private String customerType;
	@Column(name = "business_domain_product_type")
	private String productType;
	@Embedded
	private CustomerTypeId customerTypeId;
	@Column(name = "customer_type_name")
	private String customerTypeName;
	@Embedded
	private ProductTypeId productTypeId;
	@Column(name = "product_type_name")
	private String productTypeName;
	@ElementCollection
	private List<Indicator> indicators = new ArrayList<>();
	@ElementCollection
	private List<AssociatedUrl> associatedUrls = new ArrayList<>();
	@ElementCollection
	private List<AssociatedProductType> associatedProductTypes = new ArrayList<>();
	@ElementCollection
	private List<AssociatedCustomerType> associatedCustomerTypes = new ArrayList<>();
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	
	

}
