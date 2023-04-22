package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class OrderDetail {
    private String productName;
    private String subtotal;
    private String shipping;
    private String tax;
    private String total;
}
