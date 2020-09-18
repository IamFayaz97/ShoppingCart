package com.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingcart.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	Product findByProdName(String prodName);
	List<Product> findByCategory(String category);

}
