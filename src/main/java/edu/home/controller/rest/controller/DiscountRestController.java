package edu.home.controller.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.DiscountDto;
import edu.home.common.entity.FoodDto;
import edu.home.entity.Discount;
import edu.home.entity.Food;
import edu.home.repository.DiscountRepository;
import edu.home.service.DiscountService;
import edu.home.service.FoodService;
import edu.home.service.UserAccountService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/discount")

public class DiscountRestController {
//	Giàu
	
	@Autowired
	DiscountRepository dao;
	@Autowired
	DiscountService discountService;
	@Autowired 
	FoodService foodService;
	@Autowired 
	UserAccountService userService;
	
	@GetMapping("")
	public List<Discount> getAll(){
		return discountService.findAll();
	}
	@GetMapping("/findById/{id}")
	public Discount findById(@PathVariable("id") Long id) {
		return discountService.findById(id);
	}
	@GetMapping("/findByFoodId/{id}")
	public List<Discount> findByFoodId(@PathVariable("id") Long id) {
		return discountService.findByFoodId(id);
	}
	@GetMapping("/{name}")
	public List<Discount> findById(@PathVariable("name") String name) {
		return dao.findByName(name);
	}
	@PostMapping("")
	public Discount create(@RequestBody DiscountDto discountDto) {
		Discount discount = new Discount();
		java.util.Date date = new java.util.Date();
		discount.setCreateDate(date);
		discount.setEndDate(discountDto.getEndDate());
		discount.setStartDate(discountDto.getStartDate());
		discount.setDisplay(discountDto.isDisplay());
		discount.setFixed(discountDto.isFixed());
		discount.setName(discountDto.getName());
		discount.setPercentDiscount(discountDto.getPercentDiscount());
		discount.setFood(foodService.findById(discountDto.getFood()));
		discount.setUser(userService.findByUsernameOrEmail(discountDto.getCreateBy()));
		
		return dao.save(discount);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(
//			@RequestBody Discount discount
			@RequestBody DiscountDto discountDto
			, @PathVariable("id") Integer id) {
		
		try {
			Discount discount = new Discount();
			discount.setId(discountDto.getId());//
			discount.setPercentDiscount(discountDto.getPercentDiscount());//
			discount.setCreateDate(discountDto.getCreateDate());//
			discount.setEndDate(discountDto.getEndDate());//
			discount.setDisplay(discountDto.isDisplay());//
			discount.setFixed(discountDto.isFixed());//
			discount.setName(discountDto.getName());//
			discount.setStartDate(discountDto.getStartDate());//
			discount.setFood(foodService.findById(discountDto.getFood()));
			discount.setUser(userService.findByUsernameOrEmail(discountDto.getCreateBy()));
			
			return ResponseEntity.ok(discountService.update(discount));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
	@PutMapping("/updateFoodId/{foodId}/{discountId}")
	public void updateFoodId(@PathVariable("foodId") Long foodId, @PathVariable("discountId") Long discountId) {
		discountService.updateFoodIdById(foodId, discountId);
	}
	@PutMapping("/display/{display}/{id}")
	public void updateDiscount(@PathVariable("display") Boolean display, @PathVariable("id") Long id) {
		discountService.updateIsDisplayById(display, id);
	}
	@PutMapping("/fixed/{fixed}/{id}")
	public void updateFixed(@PathVariable("fixed") Boolean display, @PathVariable("id") Long id) {
		discountService.updateIsFixedById(display, id);
	}
	
}
