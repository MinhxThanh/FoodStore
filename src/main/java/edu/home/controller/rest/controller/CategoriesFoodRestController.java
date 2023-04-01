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
import edu.home.service.CategoryService;
import edu.home.service.FoodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/categoryFood")
public class CategoriesFoodRestController {
    @Autowired
    private CategoryFoodService categoryFoodService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "")
    public List<CategoryFood> getAll(){
        return categoryFoodService.findAll();
    }

    @GetMapping(value = "/{id}")
    public List<CategoryFood> getByID(@PathVariable("id") Integer id){
        System.out.println("rest id: " + id);
        return categoryFoodService.findByIdFood(id);
    }
    
//  Giàu
	  @PostMapping("/{cid}/{fid}")
	  public CategoryFood addCategoryFood(@PathVariable("cid") Long cid, @PathVariable("fid") Long fid) {
	  	CategoryFood categoryFood  = new CategoryFood();
	  	try {
	  		categoryFood.setFood(foodService.findById(fid));
		  	categoryFood.setCategory(categoryService.findById(cid));
	  	}catch(Exception e) {
	  		e.printStackTrace();
	  	}
	  	
	  	return categoryFoodService.save(categoryFood);
	  }
	  
//	  Giàu
  @DeleteMapping(value = "/{cid}/{pid}")
    public Integer delete(@PathVariable("cid") Integer cid, @PathVariable("pid") Integer pid){
        return categoryFoodService.deleteCategoryFoodByCateIDAndFoodId(cid, pid);
    }
  
//  Giàu
  @PostMapping(value = "")
  public CategoryFood create(@RequestBody CategoryFood categoryFood){
      return categoryFoodService.save(categoryFood);
  }
//	 Giàu
  @GetMapping(value ="/{cid}/{pid}")
  public CategoryFood getCategorFoodByFoodIdAndCategoryId(
          @PathVariable("cid") Integer categoryId,
          @PathVariable("pid") Integer productId){
      return categoryFoodService.findCategoryFoodByFoodIdAndCategoryId(productId, categoryId);
  }
}
