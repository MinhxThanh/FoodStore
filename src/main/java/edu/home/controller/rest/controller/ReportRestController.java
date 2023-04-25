package edu.home.controller.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.Report;
import edu.home.common.entity.ReportMonth2;
import edu.home.service.ReportService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reports")
public class ReportRestController {
//    @Autowired
//    OrderService orderService;
    @Autowired
    ReportService reportService;


    @GetMapping("/revenue/month")
    public List<ReportMonth2> getByMonth() {
        return reportService.getByMonth();
    }
    
    @GetMapping("/revenue/category")
    public List<Report> getInventoryByParentCategory() {
        return reportService.getInventoryByParentCategory();
    }
    
    @GetMapping("/revenue/customer")
    public List<Report> getInventoryByCustomer() {
        return reportService.getInventoryByCustomer();
    }
//

    @GetMapping("/revenue/category/{id}/month/{month}/year/{year}")
    public List<Report> getInventoryCategoryByMonthAndByCategoryName(@PathVariable("id") String name, @PathVariable("month") Integer month, @PathVariable("year") Integer year) {
        return reportService.getInventoryCategoryByMonthAndByCategoryName(name, month, year);
    }

    @GetMapping("/revenue/product")
    public List<Report> getByProduct() {
        return reportService.getInventoryProduct();
    }

    @GetMapping("/revenue/product/{id}/month/{month}/year/{year}")
    public List<Report> getInventoryProductByMonthAndByProductName(@PathVariable("id") Long id, @PathVariable("month") Integer month, @PathVariable("year") Integer year) {
        return reportService.getInventoryProductByMonthAndByProductName(id, month, year);
    }
}
