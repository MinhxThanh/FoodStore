package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderDetailResponse {
    @Id
    private long id;
    private String name;
    private String image;
    private long quantity;
    private Double newPrice;

    private Double oldPrice;
}
