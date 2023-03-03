package edu.home.controller.rest.controller;

import edu.home.entity.ImageFood;
import edu.home.service.ImageFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/imageFood")
public class ImageFoodRestController {
    @Autowired
    private ImageFoodService imageFoodService;

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
}
