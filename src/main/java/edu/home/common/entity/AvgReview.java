package edu.home.common.entity;

import edu.home.entity.ImageFood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

import edu.home.entity.Food;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvgReview {
    @Id
    private Long amount;
    private Double average;
    private Food food;
}
