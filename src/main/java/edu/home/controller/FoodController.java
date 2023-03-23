package edu.home.controller;

import edu.home.common.entity.FoodDetail;
import edu.home.service.CategoryService;
import edu.home.service.FoodService;
import edu.home.service.ImageFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private ImageFoodService imageFoodService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "detail/{id}")
    public String productDetailTest(Model model, @PathVariable("id") Long food_id){
        FoodDetail foodDetail = foodService.getInfoDetailByFoodId(food_id);

        model.addAttribute("categories", categoryService.findAllByFoodId(food_id));
        model.addAttribute("relatedFoods", foodService.getListFoodByCategoryId(foodDetail.getCategoryId()));
        model.addAttribute("imageFoods", imageFoodService.searchByFoodId(foodDetail.getId().longValue()));
        model.addAttribute("item", foodDetail);
        model.addAttribute("pageTitle", "Menu");
        return "product/detail";
    }
}
