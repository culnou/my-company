package com.culnou.mumu.company.domain.model.knowledge.awareness;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culnou.mumu.company.domain.model.CompanyId;
import com.culnou.mumu.company.domain.model.common.CompanyAdapter;
import com.culnou.mumu.company.domain.model.common.MemberAdapter;
import com.culnou.mumu.company.domain.model.member.Member;
import com.culnou.mumu.company.domain.model.member.MemberId;
import com.culnou.mumu.compnay.controller.AwarenessDto;
import com.culnou.mumu.compnay.controller.CompanyDto;
import com.culnou.mumu.compnay.controller.MessageDto;
@Service
@Transactional
public class AwarenessService {
	
	@Autowired
	private AwarenessRegistry registry;
	@Autowired
	private CompanyAdapter companyAdapter;
	@Autowired
	private MemberAdapter memberAdapter;
	
	/*
	 * メンバーの気づきを探す
	 */
	public List<AwarenessDto> findAwarenessesOfMember(String memberId) throws Exception{
		Member member = memberAdapter.findMemberOfMemberId(new MemberId(memberId));
		if(member == null) {
			throw new Exception("The_member_could_not_be_found");
		}
		return this.convertAwarenesses(registry.findAwarenessesOfMember(new MemberId(memberId)));
	}
	
	/*
	 * メンバーの気づきを登録する
	 */
	public MessageDto registerAwareness(AwarenessDto awareness) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(awareness == null) {
				throw new Exception("The_awareness_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			CompanyDto company = companyAdapter.findCompayOfId(awareness.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			Member member = memberAdapter.findMemberOfMemberId(new MemberId(awareness.getMemberId()));
			if(member == null) {
				throw new Exception("The_member_could_not_be_found");
			}
			//ビジネスロジック
			Awareness entity = this.convertAwarenessDto(awareness);
			entity.setAwarenessId(registry.nextIdentity());
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
	 * メンバーの気づきを更新する
	 */
	public MessageDto updateAwareness(AwarenessDto awareness) {
		MessageDto message = new MessageDto();
		try {
			//事前条件
	        //コントローラーからの入力値の妥当性を保証する
			if(awareness == null) {
				throw new Exception("The_awareness_may_not_be_set_to_null");
			}
			//識別子に対する実体の存在を保証する（識別子オブジェクトにラップする）
			Awareness result = registry.findAwarenessOfId(awareness.getAwarenessId());
			if(result == null) {
				throw new Exception("The_awareness_could_not_be_found");
			}
			CompanyDto company = companyAdapter.findCompayOfId(awareness.getCompanyId());
			if(company == null) {
				throw new Exception("The_company_may_not_exist");
			}
			Member member = memberAdapter.findMemberOfMemberId(new MemberId(awareness.getMemberId()));
			if(member == null) {
				throw new Exception("The_member_could_not_be_found");
			}
			//更新のビジネスロジック
			//必要な属性だけ更新する
			Awareness entity = this.convertAwarenessDto(awareness);
			entity.setAwarenessId(result.getAwarenessId());
			entity.setCompanyId(new CompanyId(company.getCompanyId()));
			entity.setMemberId(member.getMemberId());
			entity.setCreatedAt(result.getCreatedAt());			
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
	 * メンバーの気づきを削除する
	 */
	public MessageDto removeAwareness(String awarenessId) {
		MessageDto message = new MessageDto();
		try {
			Awareness awareness = registry.findAwarenessOfId(awarenessId);
			if(awareness == null) {
				throw new Exception("The_awareness_could_not_be_found");
			}
			registry.remove(awareness);
			message.setResult("OK");
		}catch(Exception ex) {
			message.setResult("NG");
			message.setErrorMessage(ex.getMessage());
			return message;
		}
		return message;
	}
	
	private AwarenessDto convertAwareness(Awareness entity) {
		AwarenessDto dto = new AwarenessDto();
		dto.setAwarenessId(entity.getAwarenessId().getAwarenessId());
		dto.setCompanyId(entity.getCompanyId().id());
		dto.setMemberId(entity.getMemberId().getMemberId());
		dto.setAwarenessName(entity.getAwarenessName());
		dto.setAwarenessDescription(entity.getAwarenessDescription());
		dto.setUrl(entity.getUrl());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}
	
	private List<AwarenessDto> convertAwarenesses(List<Awareness> entities){
		List<AwarenessDto> dtos = new ArrayList<>();
		for(Awareness entity : entities) {
			dtos.add(this.convertAwareness(entity));
		}
		return dtos;
	}
	
	private Awareness convertAwarenessDto(AwarenessDto dto) {
		Awareness entity = new Awareness();
		if(dto.getAwarenessId() != null && !dto.getAwarenessId().isEmpty()) {
			entity.setAwarenessId(new AwarenessId(dto.getAwarenessId()));
		}
		entity.setAwarenessName(dto.getAwarenessName());
		entity.setAwarenessDescription(dto.getAwarenessDescription());
		entity.setMemberId(new MemberId(dto.getMemberId()));
		entity.setCompanyId(new CompanyId(dto.getCompanyId()));
		entity.setUrl(dto.getUrl());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		return entity;
	}

}
