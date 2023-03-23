package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class CartItem {
    private Integer id;
    private String name;
    private Integer quantity;
    private double price;
}
