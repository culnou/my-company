package com.culnou.mumu.company.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.culnou.mumu.company.domain.model.Achievement;
import com.culnou.mumu.company.domain.model.Age;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.Country;
import com.culnou.mumu.company.domain.model.CustomerCategoryId;
import com.culnou.mumu.company.domain.model.CustomerTypeId;
import com.culnou.mumu.company.domain.model.Gender;
import com.culnou.mumu.company.domain.model.Goal;
import com.culnou.mumu.company.domain.model.Industry;
import com.culnou.mumu.company.domain.model.IndustryGroup;
import com.culnou.mumu.company.domain.model.IndustrySubGroup;
import com.culnou.mumu.company.domain.model.Size;
import com.culnou.mumu.company.domain.model.Url;
import com.culnou.mumu.company.domain.model.common.BusinessState;
import com.culnou.mumu.company.domain.model.common.Personality;

import lombok.Data;
@Entity
@Table(name = "customer_categories")
@XmlRootElement
@Data
public class CustomerCategoryEntity {
	@Id
	@Embedded
	private CustomerCategoryId customerCategoryId;
	@Column(name = "customer_category_name")
	private String customerCategoryName;
	
	@Embedded
	private CompanyId companyId;
	@Embedded
	private BusinessUnitId businessUnitId;
	@Embedded
	private CustomerTypeId customerTypeId;
	@Column(name = "customer_type_name")
	private String customerTypeName;
	@Column(name = "customer_category_description")
	private String customerCategoryDescription;
	@ElementCollection
	private List<Country> countries;
	@ElementCollection
	private List<Age> ages;
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	@Enumerated(EnumType.STRING)
	@Column(name = "personality")
	private Personality personality;
	@Embedded
	private IndustryGroup industryGroup;
	@Embedded
	private Industry industry;
	@Embedded
	private IndustrySubGroup industrySubGroup;
	@Enumerated(EnumType.STRING)
	@Column(name = "size")
	private Size size;
	@Embedded
	private Url url;
	@ElementCollection
	private List<Goal> goals = new ArrayList<>();
	@ElementCollection
	private List<Achievement> achievements = new ArrayList<>();
	@Column(name = "startdate")
	private Date startDate;
	
	@Column(name = "enddate")
	private Date endDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "business_state")
	private BusinessState businessState;

}
