package edu.home.repository;

import edu.home.entity.CustomerPhoneAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPhoneAddressRepository extends JpaRepository<CustomerPhoneAddress, Long> {
}