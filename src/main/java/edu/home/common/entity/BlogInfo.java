package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfo {
	private Long id;
	private String content;
	private String createBy;
	private Date createDate;
	private Boolean isDisplay;
	private Long status;
	private String title;
	private Long viewCount;

}
