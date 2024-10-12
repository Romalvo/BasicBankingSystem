package sda.spring.basicbankingsystem.entity;

import jakarta.persistence.*;

@Entity

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Enumerated(enum,)
    private  AccountStatus status = AccountStatus .OPEN;
}
