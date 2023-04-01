package edu.home.controller.rest.controller;

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

import edu.home.common.entity.FoodDto;
import edu.home.common.entity.ProcedureFoods;
import edu.home.entity.Food;
import edu.home.entity.User;
import edu.home.service.CategoryFoodService;
import edu.home.service.FoodService;
import edu.home.service.ImageFoodService;
import edu.home.service.UserAccountService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/food")
public class FoodRestController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ImageFoodService imageFoodService;
    @Autowired
    private CategoryFoodService categoryFoodService;

    @GetMapping(value = "searchById/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(foodService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "getListFood")
    public ResponseEntity<?> getListFood(){
        try{
            return ResponseEntity.ok(foodService.getListFood());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

//    Giau
//    @GetMapping("")
//	public List<ProcedureFoods> getFoods(){
//			return foodService.procedureFoods();
//	}
    //Giau
    @GetMapping("/findAll")
    public List<Food> getAll(){
    	return foodService.findAll();
    }

//    Giàu
//    @PostMapping(value = "")
//    public ResponseEntity<?> create(@RequestBody FoodDto foodDto){
//    	System.out.println("QUANTILT: "+foodDto.getQuantityLimit());
//    	try {
//			Food food = new Food();
//			java.util.Date date = new java.util.Date();
//			food.setCreateDate(date);
//			food.setDisplay(foodDto.isDisplay());
//			food.setName(foodDto.getName());
//			food.setPrice(foodDto.getPrice());
//			food.setQuantityLimit(foodDto.getQuantityLimit());
//			food.setQuantitySell(foodDto.getQuantitySell());
//			food.setViewCount(0);
//			food.setDescription(foodDto.getDescription());
//			food.setUser(userAccountService.findByUsernameOrEmail(foodDto.getCreateBy()));
//			Food food1 = foodService.create(food);
//			
//			System.out.println("ID Food: "+food.getId());
//			
//			return ResponseEntity.ok(food1);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.noContent().build();
//		}
//    }
    @PostMapping("/create")
    public Food create(@RequestBody FoodDto foodDto) {
    	Food food = new Food();
    	try {
    		
    		food.setName(foodDto.getName());
    		food.setPrice(foodDto.getPrice());
    		food.setQuantityLimit(foodDto.getQuantityLimit());
    		food.setQuantitySell(foodDto.getQuantitySell());
    		food.setViewCount(0);
    		food.setDescription(foodDto.getDescription());
    		food.setCreateDate(foodDto.getCreateDate());
    		food.setDisplay(foodDto.isDisplay());
    		
    		food.setUser(userAccountService.findByUsernameOrEmail(foodDto.getCreateBy()));
    		
    	}catch(Exception e) {
			e.printStackTrace();
    	}
    	return foodService.create(food);
    }
//    Giàu
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateFood(@RequestBody FoodDto foodDto,@PathVariable("id") Integer id){
        try {
            Food oldFood = foodService.findById(foodDto.getId());
            Food food = new Food();
            food.setId(foodDto.getId());
//            java.util.Date date = new java.util.Date();
    		food.setCreateDate(foodDto.getCreateDate());
    		food.setDisplay(foodDto.isDisplay());
    		food.setName(foodDto.getName());
    		food.setPrice(foodDto.getPrice());
    		food.setQuantityLimit(foodDto.getQuantityLimit());
    		food.setQuantitySell(foodDto.getQuantitySell());
    		food.setViewCount(0);
    		food.setDescription(foodDto.getDescription());
    		System.out.println("Name: "+foodDto.getName());

            food.setUser(userAccountService.findByUsernameOrEmail(foodDto.getCreateBy()));
            return ResponseEntity.ok(foodService.update(food));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }
//    Giàu
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id){
         foodService.delete(id);
    }
//    Giàu
    @PutMapping("/display/{display}/{id}")
    public void updateIsDisplayById(@PathVariable("display") Boolean display, @PathVariable("id") Long id) {
    	foodService.updateIsDisplayById(display, id);
    }
}
