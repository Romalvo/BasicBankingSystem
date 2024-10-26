package sda.spring.basicbankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.spring.basicbankingsystem.entity.Branch;


@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}
