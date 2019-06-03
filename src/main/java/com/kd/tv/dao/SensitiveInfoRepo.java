package com.kd.tv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kd.tv.entity.SensitiveInfoEntity;

@Repository
public interface SensitiveInfoRepo extends JpaRepository<SensitiveInfoEntity, Integer> {
	
	@Query(value = "SELECT sid,transfer_uid,info_key,info_value,created_date FROM sensitive_information WHERE transfer_uid=(:transferId) AND info_key=(:info_key)", nativeQuery = true)
	SensitiveInfoEntity findByTranferIdAndKey(@Param("transferId") String transferId,@Param("info_key") String info_key);
}
