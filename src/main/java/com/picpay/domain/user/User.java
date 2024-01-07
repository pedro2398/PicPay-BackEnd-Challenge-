package com.picpay.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SQ_USER", allocationSize = 0)
@Table(name = "TB_USER", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_USER", columnNames = "EMAIL_USER"),
        @UniqueConstraint(name = "UK_USER_DOC", columnNames = "DOC_USER")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USER")
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "NM_USER")
    private String firstName;

    @Column(name = "LAST_NM_USER")
    private String lastName;

    @Column(name = "EMAIL_USER")
    private String email;

    @Column(name = "PWD_USER")
    private String password;

    @Column(name = "DOC_USER")
    private String document;

    @Column(name = "BALANCE_USER")
    private BigDecimal balance;

    @Column(name = "TYPE_USER")
    @Enumerated(EnumType.STRING)
    private UserType userType;
}



