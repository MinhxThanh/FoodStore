package edu.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportMonth {
    @Id
    Integer month;
    Integer year;
    Double sum;
    Long count;
    String name;

}
