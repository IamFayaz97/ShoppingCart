package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
