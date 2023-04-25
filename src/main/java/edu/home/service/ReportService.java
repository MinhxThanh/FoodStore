package edu.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.common.entity.Report;
import edu.home.common.entity.ReportMonth2;
import edu.home.repository.OrderDetailRepository;

@Service
public class ReportService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<Report> getInventoryByParentCategory() {
        return orderDetailRepository.getInventoryByParentCategory();
    }

    public List<Report> getInventoryByCustomer() {
        return orderDetailRepository.getInventoryByCustomer();
    }
 
    public List<ReportMonth2> getByMonth() {
        return orderDetailRepository.getByMonth();
    }

    public List<Report> getInventoryProduct() {
        return orderDetailRepository.getInventoryProduct();
    }

    public List<Report> getInventoryProductByMonthAndByProductName(Long id, Integer month, Integer year) {
        return orderDetailRepository.getInventoryProductByMonthAndByProductName(id, month, year);
    }
    
    public List<Report> getInventoryCategoryByMonthAndByCategoryName(String name, Integer month, Integer year) {
        return orderDetailRepository.getInventoryCategoryByMonthAndByCategoryName(name, month, year);
    }
}
