package edu.home.repository;

import edu.home.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByCustomerEmailAndFoodId(String customerEmail, Long foodId);

    @Transactional
    @Modifying
    @Query("update Cart c set c.quantity = ?1 where c.customer.email = ?2 and c.food.id = ?3")
    void updateCartQuantityByCustomerEmailAndFoodId(Integer quantity, String customerEmail, Long foodId);

    List<Cart> findAllByCustomerEmail(String remoteUser);

    @Transactional
    @Modifying
    void deleteByCustomerEmailAndFoodId(String remoteUser, Long foodId);
}