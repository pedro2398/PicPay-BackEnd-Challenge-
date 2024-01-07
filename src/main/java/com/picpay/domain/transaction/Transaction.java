package com.picpay.domain.transaction;

import com.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SQ_TRANSACTION", allocationSize = 0)
@Table(name = "TB_TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRANSACTION")
    @Column(name = "ID_TRANSACTION")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_SENDER",
            referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_ID_SENDER")
    )
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_RECIVER",
            referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_ID_RECIVER")
    )
    private User reciver;

    @Column(name = "VALUE_TRANSACTION")
    private BigDecimal value;
}
