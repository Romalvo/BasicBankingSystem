package sda.spring.basicbankingsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private String timezone;

    @OneToMany(mappedBy = "branch")
    private List<Account> accounts;

    private LocalDateTime createdAt;
}
