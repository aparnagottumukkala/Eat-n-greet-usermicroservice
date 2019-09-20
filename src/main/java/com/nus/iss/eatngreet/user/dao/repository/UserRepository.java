package com.nus.iss.eatngreet.user.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nus.iss.eatngreet.user.dao.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmailId(String emailId);
	Optional<UserEntity> findByPhoneNo(String phoneNo);
	
	@Query("SELECT u FROM UserEntity u WHERE u.emailId IN (:emailIds)")
    List<UserEntity> findByEmailIds(@Param("emailIds")List<String> emailIds);

}
