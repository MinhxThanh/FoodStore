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
public class CustomerInfo {
	private String email; 
	private String avatar; 
	private Date birthday; 
	private Date createDate; 
	private String firstName; 
	private String fullname; 
	private String lastName; 
	private Boolean gender; 
	private Boolean isDisplay; 
	private Long status; 
	private String password; 
	private String rememberToken; 


}
