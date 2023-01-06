package com.culnou.mumu.company.domain.model.member;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.culnou.mumu.company.domain.model.common.Utility;
import com.culnou.mumu.compnay.controller.MemberDto;
import com.culnou.mumu.compnay.controller.MessageDto;
import com.culnou.mumu.compnay.controller.RoleDto;





@Service
@Transactional
public class MarketMemberAdapter {
	
    //RestTemplateを使用する。以下、参照。
	//https://b1san-blog.com/post/spring/spring-rest-template/
	List<MemberDto> findAssignableMembers(List<RoleDto> roles, String industryId) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/members/roles/industry/{industryId}/";
		
		//リクエストボディを作成。
		//Jsonに変換してheaderをつける方法
		/*
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(roles); 
		HttpEntity<String> entity = new HttpEntity<>(json, headers);
		*/
		//以下のようにJSONに変換せず、オブジェクトのままでもOK。
	    HttpEntity<List<RoleDto>> entity = new HttpEntity<>(roles);
		
		//リクエストパラメータの作成
		Map<String, String> params = new HashMap<>();
	      params.put("industryId", industryId);
	      
	      
	    //GETでリクエストボディをつけるとリクエストエラーになるのでPOSTにする。
		ResponseEntity<List<MemberDto>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<List<MemberDto>>() {}, params);
		List<MemberDto> members = responseEntity.getBody();
		return members;
	     
	}
	
	List<RoleDto> findRoles(){
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://160.16.225.15:1000/roles";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, String> params = new HashMap<>();
	      params.put("industryId", "78");
		ResponseEntity<List<RoleDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<RoleDto>>() {});
		List<RoleDto> roles = responseEntity.getBody();
		return roles;
	}
	
	public MessageDto assignMemberToDepartment(String memberId, String departmentId, String companyId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/assign/members/{memberId}/department/{departmentId}/company/{companyId}";
		//リクエストパラメータの作成
		Map<String, String> params = new HashMap<>();
	    params.put("memberId", memberId);
	    params.put("departmentId", departmentId);
	    params.put("companyId", companyId);
	    ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, null, MessageDto.class, params);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}
	
	public MessageDto assignMemberToProject(String memberId, String projectId, String companyId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/assign/members/{memberId}/project/{projectId}/company/{companyId}";
		//リクエストパラメータの作成
		Map<String, String> params = new HashMap<>();
	    params.put("memberId", memberId);
	    params.put("projectId", projectId);
	    params.put("companyId", companyId);
	    ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, null, MessageDto.class, params);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}
	
	public MessageDto assignMemberToProgram(String memberId, String programId, String companyId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/assign/members/{memberId}/program/{programId}/company/{companyId}";
		//リクエストパラメータの作成
		Map<String, String> params = new HashMap<>();
	    params.put("memberId", memberId);
	    params.put("programId", programId);
	    params.put("companyId", companyId);
	    ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, null, MessageDto.class, params);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}
	
	
	public MessageDto releaseMember(String memberId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = Utility.url + "/release/members/{memberId}";
		//リクエストパラメータの作成
		Map<String, String> params = new HashMap<>();
	    params.put("memberId", memberId);
	    ResponseEntity<MessageDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, null, MessageDto.class, params);
	    MessageDto message = responseEntity.getBody();
	    return message;
	}

}
