package edu.home.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "getFoodList", procedureName = "getAllFoodImage1")})
public class FoodList{
    @Id
    private Integer id;
    private String name;
    private Double price;
    private String image;

}
