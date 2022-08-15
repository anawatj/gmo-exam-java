package com.gmo.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmo.exam.entities.UserGroup;

@Repository
public interface IUserGroupRepository extends JpaRepository<UserGroup, String> {

}
