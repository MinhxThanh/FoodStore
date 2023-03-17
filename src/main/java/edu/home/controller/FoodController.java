package edu.home.controller;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.home.common.entity.FoodDto;
import edu.home.common.entity.ProcedureFoods;
import edu.home.entity.Category;
import edu.home.entity.Food;
import edu.home.entity.ImageFood;
import edu.home.repository.CategoryFoodRepository;
import edu.home.repository.CategoryRepository;
import edu.home.repository.FoodRepository;
import edu.home.repository.ImageFoodRepository;
import edu.home.repository.UserRepository;
import edu.home.service.FoodService;
import edu.home.service.SecurityUtils;
import edu.home.store.FileUploadUtil;

@Controller
@RequestMapping("/admin")
public class FoodController {
	@Autowired
	FoodService foodService;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	FoodRepository foodRepository;
	@Autowired
	ImageFoodRepository imageFoodRepository;
	@Autowired
	CategoryRepository categoRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryFoodRepository categoryFoodRepository;
	
	@RequestMapping("/foods")
	public String getFoods(Model model) {
		
		List<Category> category = categoRepository.findAll();
        model.addAttribute("category", category);
        System.out.println(category);
        List<ProcedureFoods> ls = foodService.procedureFoods();
		model.addAttribute("ls", ls);
		
		return "/product/foods";
	}
	@GetMapping("/add_food")
    public String addFood(Model model) {
		List<Category> category = categoryRepository.findAll();
		model.addAttribute("food",new ProcedureFoods());

        return "/product/addFood";
    }
 	// Sau khi update tai khoan thanh cong thi hien trang chua cac tai khoan
 	@PostMapping("updateFood")
 	public String update(ModelMap model, Food food) {
 		foodRepository.save(food);
 		return "redirect:/admin/foods";
 	}
}
