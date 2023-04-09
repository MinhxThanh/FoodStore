package edu.home.service;

import edu.home.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();

    Review create(Review blog);

    Review update(Review blog);

    void delete(Long id);

    Review findById(Long id);
}
