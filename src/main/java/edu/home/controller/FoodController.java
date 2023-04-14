package edu.home.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.home.entity.Review;
import edu.home.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.home.common.entity.FoodDetail;
import edu.home.entity.Food;
import edu.home.store.Display;

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

    @RequestMapping(value = "detail/{id}")
    public String productDetailTest(Model model, @PathVariable("id") Long food_id){
        FoodDetail foodDetail = foodService.getInfoDetailByFoodId(food_id);
		Long view = foodDetail.getViewCount().longValue() + 1;
		foodService.updateViewCountById(view, food_id);

		List<Review> reviews = reviewService.findAllByFoodId(food_id);
		Integer totalRate = reviews.size();
		Double totalValueRate = reviews.stream().mapToDouble(item -> item.getRating()).sum();

		model.addAttribute("totalComment", commentService.findAllByFoodId(food_id).size());
		model.addAttribute("totalRate", totalRate);
		model.addAttribute("totalValueRate", totalValueRate);
        model.addAttribute("categories", categoryService.findAllByFoodId(food_id));
        model.addAttribute("relatedFoods", foodService.getListFoodByCategoryId(foodDetail.getCategoryId()));
        model.addAttribute("imageFoods", imageFoodService.searchByFoodId(foodDetail.getId().longValue()));
        model.addAttribute("item", foodDetail);
        model.addAttribute("pageTitle", "Menu");
        return "product/detail";
        
    }
    @RequestMapping({ "all", "{category}" })
   	public String list(Model model, @PathVariable(value = "category") Optional<String> cid,
   			@RequestParam(value = "keyword") Optional<String> keyword,
   			@RequestParam(value = "price_min") Optional<Double> price_min,
   			@RequestParam(value = "price_max") Optional<Double> price_max,
   			@RequestParam(value = "quantity") Optional<Integer> quantity,
   			@RequestParam(value = "view") Optional<Integer> view,
   			@RequestParam(value = "create_date") Optional<Long> create_date,
   			@RequestParam(value = "create_by") Optional<Long> create_by,
   			@RequestParam(value = "is_display") Optional<Boolean> is_display,
   			@RequestParam(value = "page") Optional<Integer> page,
   			@RequestParam(value = "size") Optional<Integer> size,
   			@RequestParam(value = "sortBy") Optional<String> sort) {
   	
   		boolean up = sort.orElse("").contains("Up");
   		Sort sortOption = Sort.by(up ? Direction.ASC : Direction.DESC
   				, sort.orElse("name").replace("Down","").replace("Up", ""));
   		Optional<Long> cate_id = Optional.ofNullable(null);
   		if(cid.isPresent()) cate_id = Optional.ofNullable(categoryService.getByName(cid.get()).getId());
   		
   		Page<Food> list =  foodService.getByFilter(
   				keyword.orElse("")
   				,price_min
   				,price_max
   				,quantity
   				,view
   				,create_date
   				,Optional.of(Display.SHOW)
   				,cate_id
   				,PageRequest.of(page.isPresent() ? page.get()-1 : 0, size.orElse(9),sortOption));
   		
   		model.addAttribute("items", list);
   		model.addAttribute("cid", cid.orElse(""));
   		model.addAttribute("price_min", price_min.orElse(-1.0));
   		model.addAttribute("price_max", price_max.orElse(-1.0));
   		model.addAttribute("size", size.orElse(9));
   		model.addAttribute("sortBy",sort.orElse("idDown"));
           model.addAttribute("listSale", foodService.getListSaleFood());
           model.addAttribute("listTop", foodService.getListTopNewFood());
           model.addAttribute("pageTitle", "Menu");
   		return "product/list";
   	}
       
       @ModelAttribute("sortList")
   	public Map<String, String> get() {
   		Map<String, String> map = new LinkedHashMap<>();
   		map.put("Date up", "idUp");
   		map.put("Date down", "idDown");
   		map.put("Price up", "priceUp");
   		map.put("Price down", "priceDown");
   		map.put("A-Z", "nameUp");
   		map.put("Z-A", "nameDown");
   		return map;
   	}
}
