package edu.home.controller.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.entity.CategoryFood;
import edu.home.service.CategoryFoodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/categoryFood")
public class CategoriesFoodRestController {
    @Autowired
    private CategoryFoodService categoryFoodService;

    @GetMapping(value = "")
    public List<CategoryFood> getAll(){
        return categoryFoodService.findAll();
    }

    @GetMapping(value = "/{id}")
    public List<CategoryFood> getByID(@PathVariable("id") Integer id){
        System.out.println("rest id: " + id);
        return categoryFoodService.findByIdFood(id);
    }

    @GetMapping(value ="/{cid}/{pid}")
    public CategoryFood getCategorFoodByFoodIdAndCategoryId(
            @PathVariable("cid") Integer categoryId,
            @PathVariable("pid") Integer productId){
        return categoryFoodService.findCategoryFoodByFoodIdAndCategoryId(productId, categoryId);
    }

    @PostMapping(value = "")
    public CategoryFood create(@RequestBody CategoryFood categoryFood){
        return categoryFoodService.save(categoryFood);
    }

    @DeleteMapping(value = "/{cid}/{pid}")
    public Integer delete(@PathVariable("cid") Integer cid, @PathVariable("pid") Integer pid){
        System.out.println("delete categoryFood: " + cid + " and food: " + pid);
//        categoriesInProductService.deleteCategoryInProductByCateIDAndProductId(cid, pid);
        return categoryFoodService.deleteCategoryFoodByCateIDAndFoodId(cid, pid);
    }
}
