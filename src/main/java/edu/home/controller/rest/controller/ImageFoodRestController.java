package edu.home.controller.rest.controller;

import edu.home.entity.ImageFood;
import edu.home.service.ImageFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
package edu.home.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.ImageFoodDto;
import edu.home.entity.Food;
import edu.home.entity.ImageFood;
import edu.home.service.FoodService;
import edu.home.service.ImageFoodService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/imageFood")
public class ImageFoodRestController {
    @Autowired
    private ImageFoodService imageFoodService;
	@Autowired
	FoodService foodService;

//    @GetMapping(value = "searchByFoodId/{id}")
//    public ImageFood searchByFoodId(@PathVariable("id") Long foodId) {
//        System.out.println("this 1");
//        List<ImageFood> imageFoods = imageFoodService.searchByFoodId(foodId);
//        System.out.println("this image rest");
//        System.out.println("image food" + imageFoods.stream().findFirst().get().getImageName());
//        return imageFoods.stream().findFirst().get();
//    }

    @GetMapping(value = "searchByFoodId/{id}")
    public ResponseEntity<?> searchByFoodId(@PathVariable("id") Long foodId){
        try {
            List<ImageFood> imageFoods = imageFoodService.searchByFoodId(foodId);
            return ResponseEntity.ok(imageFoods.stream().findFirst().get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/{id}")
	public List<ImageFood> findByFoodId(@PathVariable("id") Long id){
		return imageFoodService.findByFoodId(id);
		
	}
	
	@PostMapping("/add")
	public ImageFood create(@RequestBody ImageFoodDto imageFoodDto) {
		ImageFood imageFood = new ImageFood();
		imageFood.setImageName(imageFoodDto.getImageName());
		imageFood.setFood(foodService.findById(imageFoodDto.getFoodId()));
		System.out.println("Food Image: ne "+ imageFoodDto.getFoodId());
		
		return imageFoodService.save(imageFood);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		imageFoodService.delete(id);
	}
}
