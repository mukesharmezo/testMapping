package com.armezo.util.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.armezo.util.entity.UtilUser;

@Repository
public interface UtilUserRepository extends JpaRepository<UtilUser, Long> {

	Optional<UtilUser> findByUsername(String username);

}
