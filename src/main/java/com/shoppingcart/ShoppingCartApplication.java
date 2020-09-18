package com.shoppingcart;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.shoppingcart.entity.Apparel;
import com.shoppingcart.entity.Book;
import com.shoppingcart.entity.User;
import com.shoppingcart.repository.ProductRepository;
import com.shoppingcart.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingCartApplication {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	        productRepository.save(new Book(10001, "Looney Toons", 100, "book", "Kids", "Jack Frost", "Toons Publications"));
	        productRepository.save(new Book(10002, "Spiderman Comics", 200, "book", "Kids", "Stanley", "Marvel"));
	        productRepository.save(new Book(10003, "Batman: Arkham Knight", 200, "book", "Kids", "Morgan", "DC"));
	        productRepository.save(new Book(10004, "Science of Nature", 400, "book", "Education", "B.R.Sharma", "Classic Publications"));
	        productRepository.save(new Book(10005, "Arithmetic Book", 500, "book", "Education", "A.K.Jain", "Jain Publications" ));
	        productRepository.save(new Book(10006, "Fashion Fiesta", 800, "book", "Magazine", "Pooja Hegde", "Fashion Week Publications"));
	        productRepository.save(new Book(10007, "My Nifty", 1000, "book", "Magazine", "A.Nadiwal", "Stocks and time publications"));
	     
	        productRepository.save(new Apparel(20001, "Denim Jacket", 1000, "apparel", "Clothes", "Levi's", "Denim"));
	        productRepository.save(new Apparel(20002, "Bomber Jacket", 1600, "apparel", "Clothes", "UCB", "Bomber"));
	        productRepository.save(new Apparel(20003, "Skeleton watch A021", 9000, "apparel", "Watches", "Casio", "Skeleton"));
	        productRepository.save(new Apparel(20004, "Digital watch A1123", 4000, "apparel", "Watches", "Fastrack", "Digital"));
	        productRepository.save(new Apparel(20005, "Reebok shoes Rx112", 1600, "apparel", "Footwear", "Reebok", "Sports"));
	        
	        userRepository.save(new User("Fayaz"));
	        userRepository.save(new User("Jack"));
	        userRepository.save(new User("Raymond"));
	      };
	   }

}
