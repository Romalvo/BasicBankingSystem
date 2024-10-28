package sda.spring.basicbankingsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import sda.spring.basicbankingsystem.enums.TransactionsStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    private String senderAccountIBAN;

    @ManyToOne
    @JoinColumn(name = "recipient_account_id")
    private Account recipientAccount;

    private String recipientAccountIBAN;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionsStatus status;

    private LocalDateTime createdAt;

}
