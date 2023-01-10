package edu.home.repository;

import edu.home.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Customer c set c.password = ?1 where c.email = ?2")
    void updatePasswordByEmail(String password, String email);
}