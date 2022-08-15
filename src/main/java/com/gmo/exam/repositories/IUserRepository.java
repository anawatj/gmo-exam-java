package com.gmo.exam.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gmo.exam.entities.User;

public interface IUserRepository extends JpaRepository<User, String> {

	@Query(value="SELECT u FROM User u WHERE u.userName=?1")
	List<User> findByUserName(String userName);
}
