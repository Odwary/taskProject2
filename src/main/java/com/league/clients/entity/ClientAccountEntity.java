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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "client_account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAccountEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
