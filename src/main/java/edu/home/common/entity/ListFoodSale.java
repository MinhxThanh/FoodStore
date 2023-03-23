package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
public class ListFoodSale {
    @Id
    private BigInteger id;
    private String name;
    private Double priceOld;
    private Double priceNew;
    private Double percentDiscount;
    private String image;
    private String category;
}
