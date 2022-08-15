package com.gmo.exam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_groups")
public class UserGroup {
		@Id
		@Column(name = "id",nullable = false,length = 255)
		private String id;
		@Column(name = "user_group_name",nullable = true,length = 1000)
		private String userGroupName;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserGroupName() {
			return userGroupName;
		}

		public void setUserGroupName(String userGroupName) {
			this.userGroupName = userGroupName;
		}

	
}
