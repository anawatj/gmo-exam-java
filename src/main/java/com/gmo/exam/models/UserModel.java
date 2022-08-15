package com.gmo.exam.models;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
	private String id;
	private String userName;
	private String userGroupId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	
	public List<String> validate(){
		List<String> errors = new ArrayList<>();
		if(userName==null || userName.equals("")) {
			errors.add("user name is required");
		}
		if(userGroupId==null || userGroupId.equals("")) {
			errors.add("user group id is required");
		}
		return errors;
	}
}
