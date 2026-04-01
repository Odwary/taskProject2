package com.league.clients;

import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "clients")
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
    OffsetDateTime createDttm;
    @NotNull
    @Column(name = "modify_dttm", nullable = false)
    OffsetDateTime modifyDttm;

    public ClientEntity() {
    }

    ;

    public ClientEntity(Long id, String fullName, ClientGender gender, ClientStatus status, OffsetDateTime createDttm, OffsetDateTime modifyDttm) {
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

    public OffsetDateTime getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(OffsetDateTime createDttm) {
        this.createDttm = createDttm;
    }

    public OffsetDateTime getModifyDttm() {
        return modifyDttm;
    }

    public void setModifyDttm(OffsetDateTime modifyDttm) {
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
