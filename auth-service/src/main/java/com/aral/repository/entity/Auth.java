package com.aral.repository.entity;

import com.aral.repository.enums.ERole;
import com.aral.repository.enums.EState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Auth")
@Entity
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String email;
    String activationCode;
    Long createdate;
    Long updatedate;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    EState state = EState.PENDING;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    ERole role = ERole.USER;
}
