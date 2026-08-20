package com.jewellery.backend.repository;

import com.jewellery.backend.entity.CartItem; // check this is 'entity' not 'model'
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c WHERE c.user.email = :email")
    List<CartItem> findByUserEmail(@Param("email") String email);
}