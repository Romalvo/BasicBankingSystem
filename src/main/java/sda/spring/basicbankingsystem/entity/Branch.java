package sda.spring.basicbankingsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private String timezone;

    @OneToMany(mappedBy = "branch")
    private List<Account> accounts;
}
