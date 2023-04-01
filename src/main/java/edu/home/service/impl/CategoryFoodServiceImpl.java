package edu.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.common.entity.FoodByCategoryId;
import edu.home.entity.CategoryFood;
import edu.home.repository.CategoryFoodRepository;
import edu.home.service.CategoryFoodService;

@Service
public class CategoryFoodServiceImpl implements CategoryFoodService{
	@Autowired
	CategoryFoodRepository dao;

	@Override
	public List<FoodByCategoryId> findByCategoryId(Long id) {
		return dao.findByCategoryId(id);
	}

    @Override
    public List<CategoryFood> findAll() {
        return dao.findAll();
    }

    @Override
    public List<CategoryFood> findByIdFood(long id) {
        return dao.findByFoodAndId(id);
    }

    @Override
    public Integer deleteCategoryFoodByCateIDAndFoodId(long cid, long pid) {
        return dao.deleteCategoryFoodByCategory_IdAndFood_Id(cid, pid);
    }
    @Override
	public List<CategoryFood> findByFoodId(Long id) {
		return dao.findByFoodId(id);
	}
    
//    Giàu
    @Override
    public CategoryFood save(CategoryFood categoryFood) {
        return dao.save(categoryFood);
    }
//    Giàu
    @Override
    public CategoryFood findCategoryFoodByFoodIdAndCategoryId(Integer productId, Integer categoryId) {
        return dao.findByFoodIdAndCategoryId(productId, categoryId);
    }
}
