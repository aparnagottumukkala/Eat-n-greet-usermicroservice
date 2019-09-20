package com.nus.iss.eatngreet.user.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nus.iss.eatngreet.user.dao.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	Optional<RoleEntity> findByRole(String role);

}
