package edu.home.controller.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.DiscountDto;
import edu.home.entity.Discount;
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

	@GetMapping("findAllByUserEmail/{email}")
	public ResponseEntity<?> findAllByUserEmail(@PathVariable("email") String email){
		try {
			return ResponseEntity.ok(discountService.findAllByUserEmail(email));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
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
		return discountService.findByName(name);
	}
	@GetMapping("/findByCreateDate/{createDate}")
	public List<Discount> findByCreateDate(@PathVariable("createDate") Date createDate) {
		return discountService.findByCreateDate(createDate);
	}
	@GetMapping("/findByStartDate/{startDate}")
	public List<Discount> findByStartDate(@PathVariable("startDate") Date startDate) {
		return discountService.findByStartDate(startDate);
	}
	@GetMapping("/findByEndDate/{endDate}")
	public List<Discount> findByEndDate(@PathVariable("endDate") Date endDate) {
		return discountService.findByEndDate(endDate);
	}
	@GetMapping("/findByName/{name}")
	public List<Discount> findByName(@PathVariable("name") String name) {
		return discountService.findByName(name);
	}
	@GetMapping("/findAllFoodDiscount/{discountName}")
	public List<Object> FindAllFoodDiscount(@PathVariable("discountName") String discountName){
		return discountService.FindAllFoodDiscount(discountName);
	}
	@PostMapping("")
	public Discount create(@RequestBody DiscountDto discountDto) {
		Discount discount = new Discount();
		discount.setCreateDate(discountDto.getCreateDate());
		discount.setEndDate(discountDto.getEndDate());
		discount.setStartDate(discountDto.getStartDate());
		discount.setDisplay(discountDto.isDisplay());
		discount.setFixed(discountDto.isFixed());
		discount.setName(discountDto.getName());
		discount.setPercentDiscount(discountDto.getPercentDiscount());
		discount.setFood(foodService.findById(discountDto.getFood()));
		discount.setUser(userService.findByUsernameOrEmail(discountDto.getCreateBy()));
		
		return discountService.save(discount);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(
//			@RequestBody Discount discount
			@RequestBody DiscountDto discountDto
			, @PathVariable("id") Integer id) {
		
		try {
			Discount discount = new Discount();
			discount.setId(discountDto.getId());
			discount.setPercentDiscount(discountDto.getPercentDiscount());
			discount.setCreateDate(discountDto.getCreateDate());
			discount.setEndDate(discountDto.getEndDate());
			discount.setDisplay(discountDto.isDisplay());
			discount.setFixed(discountDto.isFixed());
			discount.setName(discountDto.getName());
			discount.setStartDate(discountDto.getStartDate());
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
	@DeleteMapping("/delete/{id}")
	public void deleteByI(@PathVariable("id") Long id) {
		discountService.deleteById(id);
	}

	@DeleteMapping("/delete/{name}/{foodId}")
	public void deleteByNameAndFoodId(@PathVariable("name") String name,@PathVariable("foodId") Long foodId) {
		discountService.deleteByNameAndFoodId(name,foodId);
	}
	
}
