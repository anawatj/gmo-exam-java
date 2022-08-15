package com.gmo.exam.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmo.exam.dto.UserGroupDto;
import com.gmo.exam.entities.User;
import com.gmo.exam.entities.UserGroup;
import com.gmo.exam.models.UserModel;
import com.gmo.exam.repositories.IUserGroupRepository;
import com.gmo.exam.repositories.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserGroupRepository userGroupRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	public Map<String,Object> getAllUserGroup() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			
			List<UserGroup> userGroups  = userGroupRepository.findAll();
			if(userGroups.size()==0) {
				map.put("status", HttpStatus.NOT_FOUND);
				map.put("error", "not found");
				return map;
			}
			List<UserGroupDto> dtos = userGroups.stream().map(userGroup->{
				UserGroupDto dto = new UserGroupDto();
				dto.setId(userGroup.getId());
				dto.setUserGroupName(userGroup.getUserGroupName());
				return dto;
			}).toList();
			map.put("status", HttpStatus.OK);
			map.put("data", dtos);
			return map;
		}catch(Exception ex) {
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("error", ex.getMessage());
			return map;
		}
		
		
	}
	@Transactional(rollbackFor = Exception.class)
	public Map<String,Object> register(UserModel model) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			
			List<String> errors = model.validate();
			if(errors.size()>0) {
				map.put("status",HttpStatus.BAD_REQUEST);
				map.put("error", String.join(",",errors));
				return map;
			}
			User user  =new User();
			user.setId(UUID.randomUUID().toString());
			user.setUserName(model.getUserName());
			Optional<UserGroup> userGroup = userGroupRepository.findById(model.getUserGroupId());
			if(userGroup.isEmpty()) {
				map.put("status", HttpStatus.BAD_REQUEST);
				map.put("error", "user group not found");
				return map;
			}
			user.setUserGroup(userGroup.get());
			user = userRepository.save(user);
			model.setId(user.getId());
			map.put("status", HttpStatus.CREATED);
			map.put("data", model);
			return map;
		}catch(Exception ex) {
			throw ex;
		}
	}
}
