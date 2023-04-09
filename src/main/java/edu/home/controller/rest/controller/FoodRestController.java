package edu.home.controller.rest.controller;

import edu.home.entity.Food;
import edu.home.entity.ImageFood;
import edu.home.service.FoodService;
import edu.home.service.ImageFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/food")
public class FoodRestController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private ImageFoodService imageFoodService;

    @GetMapping(value = "searchById/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(foodService.getInfoDetailByFoodId(id));
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
    //get tong san pham trong thong ke
    @GetMapping
    public List<Food> getAll(){
       return foodService.getAll();
    }

}
