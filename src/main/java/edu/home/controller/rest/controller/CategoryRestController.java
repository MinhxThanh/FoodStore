package edu.home.controller.rest.controller;

import edu.home.common.entity.CategoryInfo;
import edu.home.entity.Category;
import edu.home.entity.User;
import edu.home.service.CategoryService;
import edu.home.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/category")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping(value = "getAll")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryInfo categoryInfo){
        try {
            Category category = new Category();

            category.setName(categoryInfo.getName());
            category.setDescription(categoryInfo.getDescription());
            category.setImageName(categoryInfo.getImage());
            category.setCreateDate(new Date());
            category.setDisplay(categoryInfo.getIsDisplay());
            category.setUser(userAccountService.findByUsernameOrEmail(categoryInfo.getCreateBy()));

            return ResponseEntity.ok(categoryService.create(category));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "update")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryInfo categoryInfo){
        try {
            Category oldCategory = categoryService.findById(categoryInfo.getId());
            Category category = new Category();

            category.setId(categoryInfo.getId());
            category.setName(categoryInfo.getName());
            category.setDescription(categoryInfo.getDescription());
            category.setDisplay(categoryInfo.getIsDisplay());
            category.setCreateDate(oldCategory.getCreateDate());
            if (categoryInfo.getImage() == null)
                category.setImageName(oldCategory.getImageName());
            else
                category.setImageName(categoryInfo.getImage());

            category.setUser(userAccountService.findByUsernameOrEmail(categoryInfo.getCreateBy()));
            return ResponseEntity.ok(categoryService.update(category));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.delete(id);
    }
    
//    Giàu
    @GetMapping(value = "/categories/getAllCategoriesByFoodID/{id}")
    public List<Category> getAllCategoriesByFoodID(@PathVariable("id") Long id) {
        return categoryService.getAllCategoriesByFoodID(id);
    }
    
}
