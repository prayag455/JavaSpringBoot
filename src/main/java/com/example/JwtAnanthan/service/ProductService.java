package com.example.JwtAnanthan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JwtAnanthan.model.Person;
import com.example.JwtAnanthan.model.PersonChoice;
import com.example.JwtAnanthan.model.Product;
import com.example.JwtAnanthan.repository.PersonChoiceRepository;
import com.example.JwtAnanthan.repository.PersonRepository;
import com.example.JwtAnanthan.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonChoiceRepository personChoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JwtService jwtUtil;
    
	public String removeProductFromCart(Long productId, String token) {
		String username = jwtUtil.extractUserName(token);
		
		Person person = personRepository.findByName(username)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	        // Step 2: Find Cart ID from PersonChoice
	    PersonChoice choice = personChoiceRepository.findByPerson(person)
	            .orElseThrow(() -> new RuntimeException("Cart not found for user"));

	    Long cartId = choice.getCartId();

	        // Step 3: Find product by productId and cartId
	    Product product = productRepository.findByIdAndCartId(productId, cartId)
	            .orElseThrow(() -> new RuntimeException("Product not found in your cart"));

	        // Step 4: Delete product
	        productRepository.delete(product);
	        
		return "deleted";
	}

}
