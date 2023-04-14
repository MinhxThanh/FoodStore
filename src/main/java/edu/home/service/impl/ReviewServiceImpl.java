package edu.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.entity.Review;
import edu.home.repository.ReviewRepository;
import edu.home.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository dao;

	@Override
	public List<Review> findAll() {
		return dao.findAll();
	}

	@Override
	public Review create(Review blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review update(Review blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public Review findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


}