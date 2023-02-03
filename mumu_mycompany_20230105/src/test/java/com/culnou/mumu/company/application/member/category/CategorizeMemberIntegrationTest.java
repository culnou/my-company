package com.culnou.mumu.company.application.member.category;




import static org.junit.Assert.assertNotNull;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainId;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.BusinessUnit;
import com.culnou.mumu.company.domain.model.BusinessUnitId;
import com.culnou.mumu.company.domain.model.BusinessUnitRepository;
import com.culnou.mumu.company.domain.model.Company;
import com.culnou.mumu.company.domain.model.CompanyFactory;

import com.culnou.mumu.company.domain.model.CompanyRepository;
import com.culnou.mumu.company.domain.model.member.category.MemberCategory;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryId;
import com.culnou.mumu.company.domain.model.member.category.MemberCategoryRegistry;

import com.culnou.mumu.company.domain.model.member.type.AssociatedMemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberClass;
import com.culnou.mumu.company.domain.model.member.type.MemberType;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeId;
import com.culnou.mumu.company.domain.model.member.type.MemberTypeRegistry;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorizeMemberIntegrationTest {
	
	@Qualifier("companyJpaRepository")
	@Autowired
	private CompanyRepository companyRepository;
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	@Qualifier("businessUnitJpaRepository")
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private MemberTypeRegistry memberTypeRegistry;
	@Autowired
	private MemberCategoryRegistry memberCategoryRegistry;
	
	
	private Company company;
	private BusinessDomain businessDomain;
	private BusinessUnit businessUnit;
	private MemberType memberType;
	private MemberCategory memberCategory;

	@Before
	public void setUp() throws Exception {
		//会社（ドメイン名:company001.com）が登録されていこと
		company = CompanyFactory.createCompany("company001.com");
		company.setCompanyName("company001");
		company.setCompanyPassword("company001");
		company.setEaName("EA");
		company.setEaPassword("EA");
		companyRepository.save(company);
		
		//事業ドメイン（事業ドメイン名:business_domain001）が定義されていること
		BusinessDomainId businessDomainId = businessDomainRepository.nextIdentity();
		businessDomain = company.defineBusinessDomain(businessDomainId);
		businessDomain.setBusinessDomainName("business_domain001");
		businessDomainRepository.save(businessDomain);
		
		//事業単位（事業単位名:business_unit001）が定義されていること
		BusinessUnitId businessUnitId = businessUnitRepository.nextIdentity();
		businessUnit = businessDomain.defineBusinessUnit(businessUnitId);
		businessUnit.setBusinessDomainId(businessDomain.businessDomainId());
		businessUnit.setBusinessUnitName("business_unit001");
		businessUnitRepository.save(businessUnit);
		
		//メンバータイプ（メンバータイプ名:member_type001）が定義されていること
		MemberTypeId menberTypeId = memberTypeRegistry.nextIdentity();
		memberType = businessDomain.defineMemberType(menberTypeId, "member_type001", MemberClass.Collaborator);
		memberTypeRegistry.save(memberType);
		

	}

	@After
	public void tearDown() throws Exception {
		companyRepository.remove(company);
		businessDomainRepository.remove(businessDomain);
		businessUnitRepository.remove(businessUnit);
		memberTypeRegistry.remove(memberType);
		
	}
	
	@Test
	public void registerMemberCategoryTest() throws Exception{
		System.out.println("***** メンバーカテゴリの登録*****");
		
		//EAが「メンバーカテゴリ追加画面」でメンバータイプ（メンバータイプ名:member_type001）を選択し、メンバーカテゴリ名（メンバーカテゴリ名:member_category001）、メンバーカテゴリ概要（メンバーカテゴリ概要:member_category001_description）を入力し登録すると、システムは「メンバーカテゴリを登録しました」というメッセージを出力し、 「メンバーカテゴリ一覧画面」を表示する
		MemberCategoryId memberId = memberCategoryRegistry.nextIdentity();
		memberCategory = businessUnit.defineMemberCategory(memberId, "member_category001");
		AssociatedMemberType associatedMemberType = new AssociatedMemberType();
		associatedMemberType.setMemberTypeId(memberType.getMemberTypeId().getMemberTypeId());
		associatedMemberType.setMemberTypeName(memberType.getMemberTypeName());
		memberCategory.getAssociatedMemberTypes().add(associatedMemberType);
		memberCategory.setMemberCategoryDescription("member_category001_description");
		memberCategoryRegistry.save(memberCategory);
		
		//EAがメンバーカテゴリ情報を登録した場合、システムのメンバーカテゴリ情報が登録されていること
		MemberCategory category = memberCategoryRegistry.findMemberCategoryOfId(memberCategory.getMemberCategoryId().getMemberCategoryId());
		assertNotNull(category);
		memberCategoryRegistry.remove(memberCategory);
	}

	@Test
	public void updateMemberCategoryTest() {
		System.out.println("***** メンバーカテゴリの更新*****");
		//EAがメンバーカテゴリ情報を更新した場合、システムのメンバーカテゴリ情報が更新されていること
	}

}
