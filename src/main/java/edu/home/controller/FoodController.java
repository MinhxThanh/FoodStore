package edu.home.controller;

import java.util.List;
import java.util.Optional;

import edu.home.common.entity.ListFood;
import edu.home.entity.User;
import edu.home.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.home.common.entity.FoodDetail;
import edu.home.entity.Review;

@Controller
@RequestMapping(value = "food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private ImageFoodService imageFoodService;
    @Autowired
    private CategoryService categoryService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private CouponService couponService;

    @RequestMapping(value = "detail/{id}")
    public String productDetailTest(Model model, @PathVariable("id") Long food_id){
        FoodDetail foodDetail = foodService.getInfoDetailByFoodId(food_id);
		Long view = foodDetail.getViewCount().longValue() + 1;
		foodService.updateViewCountById(view, food_id);

		List<Review> reviews = reviewService.findAllByFoodId(food_id);
		Integer totalRate = reviews.size();
		Double totalValueRate = reviews.stream().mapToDouble(item -> item.getRating()).sum();
		Integer total = commentService.findAllByFoodId(food_id).size();
		model.addAttribute("totalComment", total);
		model.addAttribute("totalRate", totalRate);
		model.addAttribute("totalValueRate", totalValueRate);
        model.addAttribute("categories", categoryService.findAllByFoodId(food_id));
        model.addAttribute("relatedFoods", foodService.getListFoodByCategoryId(foodDetail.getCategoryId()));
        model.addAttribute("imageFoods", imageFoodService.searchByFoodId(foodDetail.getId().longValue()));
        model.addAttribute("item", foodDetail);
        model.addAttribute("pageTitle", "Menu");
        return "product/detail";
        
    }
    
    @RequestMapping(value = "shop")
   	public String getAllShop(Model model){
    	model.addAttribute("items", userAccountService.getAllUserHaveFood());
		model.addAttribute("listSale", foodService.getListSaleFood());
		model.addAttribute("listTop", foodService.getListTopNewFood());
		model.addAttribute("pageTitle", "Menu");
   		return "product/list";
   	}

	@RequestMapping(value = "shop/{nameShop}")
	public String getDetailShop(Model model, @PathVariable("nameShop") Optional<String> nameShop){
//		null ==> no problem!!
//		at edu.home.controller.FoodController.getDetailShop(FoodController.java:70)
		User user = userAccountService.findByUsernameOrEmail(nameShop.orElse("Admin"));
		List<ListFood> list = foodService.getListFoodByUserId(user.getId());

		model.addAttribute("coupons", couponService.findAll());
		model.addAttribute("shop", user);
		model.addAttribute("items", list);
		model.addAttribute("pageTitle", "Menu");
		return "product/detailShop";
	}
}
