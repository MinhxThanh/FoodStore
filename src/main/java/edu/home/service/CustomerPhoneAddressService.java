package edu.home.service;

import edu.home.entity.CustomerPhoneAddress;

import java.util.List;

public interface CustomerPhoneAddressService {
    List<CustomerPhoneAddress> findAllByCustomerEmail(String remoteUser);

    CustomerPhoneAddress findByCustomerEmail(String remoteUser);

    CustomerPhoneAddress findById(long id);

    CustomerPhoneAddress create(CustomerPhoneAddress CustomerPhoneAddress);
    
    CustomerPhoneAddress update(CustomerPhoneAddress CustomerPhoneAddress);

	void deleteById(Long id);
}
