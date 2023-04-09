package edu.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.entity.Report;
import edu.home.entity.ReportMonth;
import edu.home.repository.OrderDetailRepository;

@Service
public class ReportService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

//    public List<Report> getInventory(String date) {
//        return orderDetailRepository.getInventory(date);
//    }
//
    public List<Report> getInventoryByParentCategory() {
        return orderDetailRepository.getInventoryByParentCategory();
    }
//
//    public List<Report> getInventoryByCategory(Integer id, String date) {
//        return orderDetailRepository.getInventoryByCategory(id, date);
//    }
//
    public List<Report> getInventoryByCustomer() {
        return orderDetailRepository.getInventoryByCustomer();
    }
//
//    public List<ReportMonth> getInventoryBrandByMonth(String name) {
//        return orderDetailRepository.getInventoryBrandByMonth(name);
//    }
//
//    
//    public List<ReportMonth> getRevenueAll() {
//        return orderDetailRepository.getRevenueAll();
//    }
//    
    public List<ReportMonth> getByMonth() {
        return orderDetailRepository.getByMonth();
    }

//    public List<ReportMonth> getInventoryBrand() {
//        return orderDetailRepository.getInventoryBrand();
//    }
//
//    public List<ReportMonth> getInventoryBrandByMonthAndByBrandName(String name, Integer month, Integer year) {
//        return orderDetailRepository.getInventoryBrandByMonthAndByBrandName(name, month, year);
//    }
//
    public List<Report> getInventoryProduct() {
        return orderDetailRepository.getInventoryProduct();
    }
//
    public List<Report> getInventoryProductByMonthAndByProductName(Long id, Integer month, Integer year) {
        return orderDetailRepository.getInventoryProductByMonthAndByProductName(id, month, year);
    }
    
    
    public List<Report> getInventoryCategoryByMonthAndByCategoryName(String name, Integer month, Integer year) {
        return orderDetailRepository.getInventoryCategoryByMonthAndByCategoryName(name, month, year);
    }
}
