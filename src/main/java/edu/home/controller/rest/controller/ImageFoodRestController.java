package edu.home.controller.rest.controller;

import edu.home.entity.ImageFood;
import edu.home.service.ImageFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.ImageFoodDto;
import edu.home.service.FoodService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/imageFood")
public class ImageFoodRestController {
    @Autowired
    private ImageFoodService imageFoodService;
	@Autowired
	private FoodService foodService;

    @GetMapping(value = "/searchByFoodId/{id}")
    public ResponseEntity<?> searchByFoodId(@PathVariable("id") Long foodId){
        try {
            List<ImageFood> imageFoods = imageFoodService.searchByFoodId(foodId);
            return ResponseEntity.ok(imageFoods.stream().findFirst().get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
	
//	Giàu
	@PostMapping("/add")
	public ImageFood create(@RequestBody ImageFoodDto imageFoodDto) {
		ImageFood imageFood = new ImageFood();
		try {
			imageFood.setImageName(imageFoodDto.getImageName());
			imageFood.setFood(foodService.findById(imageFoodDto.getFoodId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return imageFoodService.save(imageFood);
	}
//	Giàu
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		imageFoodService.delete(id);
	}
	
}
