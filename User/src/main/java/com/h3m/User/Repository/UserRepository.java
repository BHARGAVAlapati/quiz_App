package com.h3m.User.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.h3m.User.Entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	
	@Query("select u from Users u where u.userName=:userName")
	Optional<Users> findByUser(String userName);

}
