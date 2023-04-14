package edu.home.service;

import edu.home.entity.CustomerPhoneAddress;

import java.util.List;

public interface CustomerPhoneAddressService {
    List<CustomerPhoneAddress> findAllByCustomerEmail(String remoteUser);

    CustomerPhoneAddress findByCustomerEmail(String remoteUser);

    CustomerPhoneAddress findById(long id);

	CustomerPhoneAddress update(CustomerPhoneAddress customerPhoneAddress);

	void deleteById(Long id);

	CustomerPhoneAddress create(CustomerPhoneAddress customerPhoneAddress);
}
