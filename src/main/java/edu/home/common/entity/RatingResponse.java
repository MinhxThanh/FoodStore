package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RatingResponse {
    private BigDecimal excellentPer;
    private BigDecimal goodPer;
    private BigDecimal averagePer;
    private BigDecimal poorPer;
    private BigDecimal terriblePer;
    private Integer excellent;
    private Integer good;
    private Integer average;
    private Integer poor;
    private Integer terrible;
    private BigDecimal total;
}
