package com.culnou.mumu.company.domain.model.business.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.BusinessDomain;
import com.culnou.mumu.company.domain.model.BusinessDomainRepository;
import com.culnou.mumu.company.domain.model.CompanyId;

/*
 * 事業ドメインが追加できるかチェックするドメインサービス
 */
@Service
@Transactional
public class AddBusinesssDomainChecker {
	
	@Qualifier("businessDomainJpaRepository")
	@Autowired
	private BusinessDomainRepository businessDomainRepository;
	
	public boolean addable(String companyId) throws Exception{
		boolean check = true;
		
		//企業ドメインが産業に割り当てられていない場合、 事業ドメインを追加させない。
		List<BusinessDomain> enterprises = businessDomainRepository.findEnterprises(true, new CompanyId(companyId));
		if(enterprises.size() > 0) {
			BusinessDomain enterpriseDomain = enterprises.get(0);
			if(enterpriseDomain.industry() == null) {
				check = false;
				throw new Exception("You_must_assign_enterprise_domain_to_industries_first");
			}
		}
		
		return check;
	}

}
