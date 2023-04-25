package edu.home.controller;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.home.common.entity.FoodDetail;
import edu.home.common.entity.ListFood;
import edu.home.common.entity.ListFoodByCategory;
import edu.home.entity.Category;
import edu.home.entity.Review;
import edu.home.entity.User;
import edu.home.service.CategoryService;
import edu.home.service.CommentService;
import edu.home.service.CouponService;
import edu.home.service.FoodService;
import edu.home.service.ImageFoodService;
import edu.home.service.ReviewService;
import edu.home.service.SessionService;
import edu.home.service.UserAccountService;

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
	@Autowired
	private SessionService sessionService;

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
        model.addAttribute("pageTitle", foodDetail.getName());
        return "product/detail";
        
    }
    
    @RequestMapping(value = "shop")
   	public String getAllShop(Model model, Pageable pageable,
   			@RequestParam(value = "size") Optional<Integer> size,
   			@RequestParam("p") Optional<Integer> p,
   			@RequestParam("cityProvince") Optional<String> cityProvince){
    	String key = cityProvince.orElse("TP Hồ Chí Minh");
//    	String key = cityProvince.get();
    	sessionService.set("keywords", key);
		Sort sortOption = Sort.by("cityProvince").descending();
    	pageable = PageRequest.of(p.isPresent() ? p.get()-1 : 0, size.orElse(2), sortOption);

    	Page<User> page = userAccountService.findListFoodByUser(key, pageable);
		
    	model.addAttribute("items", page);
    	model.addAttribute("cityProvince", key);
    	model.addAttribute("size", size.orElse(2));
//    	model.addAttribute("items", userAccountService.getAllUserHaveFood(pageable));
		model.addAttribute("listSale", foodService.getListSaleFood());
		model.addAttribute("listTop", foodService.getListTopNewFood());
		model.addAttribute("pageTitle", "Menu");
   		return "product/list";
   	}

	@RequestMapping(value = "shop/{nameShop}")
	public String getDetailShop(Model model,
			@PathVariable("nameShop") Optional<String> nameShop){
//		null ==> no problem!!
//		at edu.home.controller.FoodController.getDetailShop(FoodController.java:70)
		User user = userAccountService.findByUsernameOrEmail(nameShop.orElse("Admin"));
		List<ListFood> list = foodService.getListFoodByUserId(user.getId());

//		model.addAttribute("coupons", couponService.findAllIsActive());
		model.addAttribute("shop", user);
		model.addAttribute("items", list);
		model.addAttribute("pageTitle", nameShop.get());
		return "product/detailShop";
	}
	
	@RequestMapping(value = "filter/{cid}/{cateName}")
	public String getFoodByCate(Model model
			,@PathVariable("cid") BigInteger cid
			, @PathVariable("cateName") String cateName) {
		List<ListFoodByCategory> listFoods = foodService.getListFoodByCategoryId(cid);
		model.addAttribute("listC",listFoods);
		model.addAttribute("listSale", foodService.getListSaleFood());
		model.addAttribute("listTop", foodService.getListTopNewFood());
		model.addAttribute("pageTitle", cateName);
		
		return "product/listByCate";
	}
	
	@ModelAttribute("searchList")
	public Map<String, String> get() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("TP Hồ Chí Minh", "TP Hồ Chí Minh");
		map.put("TP Hà Nội", "TP Hà Nội");
		map.put("TP Hải Phòng", "TP Hải Phòng");
		map.put("TP Đà Nẵng", "TP Đà Nẵng");
		map.put("TP Cần Thơ", "TP Cần Thơ");
		return map;
	}
}
