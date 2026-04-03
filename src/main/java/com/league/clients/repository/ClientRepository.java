package com.league.clients.repository;

import com.league.clients.dto.UpdateClientDto;
import com.league.clients.entity.ClientAccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientAccountEntity, Long> {

    @Query("""
            select ce from ClientAccountEntity ce
            """)
    List<ClientAccountEntity> findAllClientsByFilter(
            Pageable pageable
    );

    @Transactional
    @Modifying
    @Query("""
            update ClientAccountEntity ce
            set
            ce.fullName = :#{#dto.fullName},
            ce.gender = :#{#dto.gender},
            ce.status = :#{#dto.status},
            ce.modifyDttm = :modifyDttm
            where ce.id = :id
            """)
    void updateClient(
            @Param("id") Long id,
            @Param("dto") UpdateClientDto dto,
            @Param("modifyDttm") Instant modifyDttm
    );
}
