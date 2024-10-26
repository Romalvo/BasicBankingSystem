package sda.spring.basicbankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import sda.spring.basicbankingsystem.enums.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String iban;

    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.OPEN;


    @ManyToOne
    @JoinColumn(name= "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "senderAccount")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "recipientAccount")
    private List<Transaction> incomingTransactions;

    private LocalDateTime createdAt;
}
