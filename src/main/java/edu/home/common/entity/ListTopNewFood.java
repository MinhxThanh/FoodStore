package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigInteger;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class ListTopNewFood {
    @Id
    private BigInteger id;
    private String name;
    private Double price;
    private String image;
}
