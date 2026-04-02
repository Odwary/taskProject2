package com.league.clients.entity;

import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "client_account")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @NotNull
    @Column(name = "full_name", nullable = false)
    String fullName;
    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    ClientGender gender;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    ClientStatus status;
    @NotNull
    @Column(name = "create_dttm", nullable = false)
    Instant createDttm;
    @NotNull
    @Column(name = "modify_dttm", nullable = false)
    Instant modifyDttm;

    public ClientEntity() {
    }

    ;

    public ClientEntity(Long id, String fullName, ClientGender gender, ClientStatus status, Instant createDttm, Instant modifyDttm) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.status = status;
        this.createDttm = createDttm;
        this.modifyDttm = modifyDttm;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ClientGender getGender() {
        return gender;
    }

    public void setGender(ClientGender gender) {
        this.gender = gender;
    }

    public Instant getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(Instant createDttm) {
        this.createDttm = createDttm;
    }

    public Instant getModifyDttm() {
        return modifyDttm;
    }

    public void setModifyDttm(Instant modifyDttm) {
        this.modifyDttm = modifyDttm;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && gender == that.gender && Objects.equals(createDttm, that.createDttm) && Objects.equals(modifyDttm, that.modifyDttm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, gender, createDttm, modifyDttm);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", createDttm=" + createDttm +
                ", modifyDttm=" + modifyDttm +
                '}';
    }
}
