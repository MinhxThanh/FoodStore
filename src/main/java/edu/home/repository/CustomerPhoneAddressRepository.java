package edu.home.repository;

import edu.home.entity.CustomerPhoneAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerPhoneAddressRepository extends JpaRepository<CustomerPhoneAddress, Long> {
    @Query("select c from CustomerPhoneAddress c where c.customer.email = ?1")
    List<CustomerPhoneAddress> findAllByCustomerEmail(String remoteUser);

    @Query("select c from CustomerPhoneAddress c where c.customer.email = ?1 and c.isDefault = true ")
    CustomerPhoneAddress findByCustomerEmail(String remoteUser);
}