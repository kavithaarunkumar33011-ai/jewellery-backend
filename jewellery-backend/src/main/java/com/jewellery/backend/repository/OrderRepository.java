package com.jewellery.backend.repository;

import com.jewellery.backend.entity.Order; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.email = :email")
    List<Order> findByUserEmail(@Param("email") String email);
}