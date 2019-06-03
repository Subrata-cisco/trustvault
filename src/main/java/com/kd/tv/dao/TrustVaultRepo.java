package com.kd.tv.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kd.tv.entity.TrustRequestEntity;


@Repository
public interface TrustVaultRepo extends JpaRepository<TrustRequestEntity, Integer> {
	
	
	@Query(value = "Update trustrequest set status=(:status)", nativeQuery = true)
	@Modifying
	@Transactional
	int updateTrustStatus(@Param("status") boolean status);

	
	@Query(value = "SELECT TPid,participant_uid,transfer_uid,status,status_code FROM trustrequest WHERE participant_uid=(:partId) AND transfer_uid=(:transferId)", nativeQuery = true)
	TrustRequestEntity findByParticipantAndTranferId(@Param("partId") String partId, @Param("transferId") String transferId);
}
