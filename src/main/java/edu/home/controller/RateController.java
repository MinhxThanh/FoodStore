package edu.home.controller;

import edu.home.entity.Review;
import edu.home.service.CustomerService;
import edu.home.service.FoodService;
import edu.home.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.Date;


@Controller
@RequestMapping(value = "rate")
public class RateController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping(value = "create")
    public String create(Model model, @RequestParam("usernameId") String email,
                         @RequestParam("productId") Long foodId,
                         @RequestParam("rating") Long rateValue) {
        if (email.isBlank())
            return "redirect:/product/list";

        Review review = reviewService.findByCustomerEmailAndFoodId(email, foodId);
        if (review != null) {
            reviewService.updateRatingByCustomerEmailAndFoodId(rateValue, new Date(), email, foodId);
        } else {
            Review review1 = new Review();
//            data enter
            review1.setRating(rateValue);
            review1.setCustomer(customerService.findByEmailKey(email));
            review1.setFood(foodService.findById(foodId));
//            default data
            review1.setCreateDate(new Date());
            review1.setUpdateDate(new Date());
            review1.setIsFavorite(BigInteger.valueOf(0));
            review1.setDisplay(true);
            reviewService.create(review1);
        }
        return "redirect:/food/detail/" + foodId;
    }
}
