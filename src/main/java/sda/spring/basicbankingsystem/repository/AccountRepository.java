package sda.spring.basicbankingsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.spring.basicbankingsystem.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
