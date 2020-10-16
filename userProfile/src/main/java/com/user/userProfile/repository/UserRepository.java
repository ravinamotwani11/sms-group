package com.user.userProfile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.user.userProfile.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	 public List<User> findAll();
	 public User findById(long id);
	 public User deleteById(long id);

}