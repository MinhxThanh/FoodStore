package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class FoodDetail {
    @Id
    private BigInteger id;
    private String name;
    private Double priceOld;
    private Double priceNew;
    private BigInteger quantitySell;
    private BigInteger inventory;
    private BigInteger viewCount;
    private String description;
    private String nameDiscount;
    private Integer numberDateToStart;
    private Integer numberDateToEnd;
    private String createBy;
    private String avatar;
    private BigInteger categoryId;
}
