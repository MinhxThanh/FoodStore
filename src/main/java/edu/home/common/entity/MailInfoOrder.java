package edu.home.common.entity;

import edu.home.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfoOrder {
    private String from = "mr.minhxthanh@gmail.com";
    private String to;
    private Order order;
    private String subject;

}