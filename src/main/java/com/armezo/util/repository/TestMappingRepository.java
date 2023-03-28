package com.armezo.util.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.armezo.util.entity.TestMapping;

@Repository
public interface TestMappingRepository extends JpaRepository<TestMapping, Long>{

	@Query("SELECT t FROM TestMapping t WHERE t.clientId =:clientId AND t.recruiterId =:recruiterId AND t.jobProfileId =:jobProfile")
	Optional<TestMapping> getTestMappingDataByClientIdRecIdJobProfile(String clientId, String recruiterId,
			String jobProfile);

}
