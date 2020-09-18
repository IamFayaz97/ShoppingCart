package com.shoppingcart.entity;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@OneToOne(mappedBy="cart")
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	@Column
	private float cartPrice;
	
	@ElementCollection
    @CollectionTable(name = "product_quantity_mapping", 
      joinColumns = {@JoinColumn(name = "cartId")})
    @MapKeyJoinColumn(name = "product_id")
	@Column(name = "Quantity")
	private Map<Integer, Integer> productQuantityMap;

	public Map<Integer, Integer> getProductQuantityMap() {
		return productQuantityMap;
	}

	public void setProductQuantityMap(Map<Integer, Integer> productQuantityMap) {
		this.productQuantityMap = productQuantityMap;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public float getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(float cartPrice) {
		this.cartPrice = cartPrice;
	}

	@Override
	public String toString() {
		return "Cart [cartPrice=" + cartPrice + ", productQuantityMap="
				+ productQuantityMap + "]";
	}

}
