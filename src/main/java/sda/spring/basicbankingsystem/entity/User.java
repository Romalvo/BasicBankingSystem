package sda.spring.basicbankingsystem.entity;


import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue
}
