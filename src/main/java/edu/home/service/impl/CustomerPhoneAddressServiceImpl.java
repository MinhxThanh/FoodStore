package edu.home.service.impl;

import edu.home.entity.CustomerPhoneAddress;
import edu.home.repository.CustomerPhoneAddressRepository;
import edu.home.service.CustomerPhoneAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerPhoneAddressServiceImpl implements CustomerPhoneAddressService {
    @Autowired
    private CustomerPhoneAddressRepository dao;

    @Override
    public List<CustomerPhoneAddress> findAllByCustomerEmail(String remoteUser) {
        return dao.findAllByCustomerEmail(remoteUser);
    }

    @Override
    public CustomerPhoneAddress findByCustomerEmail(String remoteUser) {
        return dao.findByCustomerEmail(remoteUser);
    }

    @Override
    public CustomerPhoneAddress findById(long id) {
        return dao.findById(id).get();
    }
}
