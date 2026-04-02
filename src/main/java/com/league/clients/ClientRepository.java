package com.league.clients;

import com.league.clients.entity.ClientEntity;
import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<ClientEntity, Long> {

    @Query("""
            select ce from ClientEntity ce
            where(:id is NULL or ce.id = :id) and
            (:fullName is NULL or ce.fullName = :fullName) and
            (:gender is NULL or ce.gender = :gender) and
            (:status is NULL or ce.status = :status)
            """)
    List<ClientEntity> findAllClientsByFilter(
            @Param("id") Long id,
            @Param("fullName") String fullName,
            @Param("gender") ClientGender gender,
            @Param("status") ClientStatus status,
            Pageable pageable
    );

    @Modifying
    @Transactional
    @Query("""
            update ClientEntity ce
            set
            ce.fullName = :fullName,
            ce.gender = :gender,
            ce.status = :status,
            ce.createDttm = :createDttm,
            ce.modifyDttm = :modifyDttm
            where ce.id = :id
            """)
    void updateClient(
            @Param("id") Long id,
            @Param("fullName") String fullName,
            @Param("gender") ClientGender gender,
            @Param("status") ClientStatus status,
            @Param("modifyDttm") OffsetDateTime modifyDttm,
            @Param("createDttm") OffsetDateTime createDttm
    );
}
