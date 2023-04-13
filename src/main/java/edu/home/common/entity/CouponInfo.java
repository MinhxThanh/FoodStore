package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

import edu.home.entity.User;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class CouponInfo {

    private Long id;
    private Double percentCoupon;
    private Double amountMoneyCoupon;//
    private BigInteger userUsed;
    
    private String code;//
    private Date createDate;//
	private String description;//
	private Date endDate ;//
	private Boolean isDisplay;//
	private Boolean isFixed ;//
	private String name ;//
	private Date startDate ;//
	private Long status;//
	private Long userLimit;//
//	private User user;
	private String createBy;//

}
