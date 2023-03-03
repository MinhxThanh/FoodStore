package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class CategoryInfo {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String createBy;
    private Date createDate;
    private Boolean isDisplay;
}
