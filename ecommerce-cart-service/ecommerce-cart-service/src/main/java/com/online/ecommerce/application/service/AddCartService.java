package com.online.ecommerce.application.service;

import com.online.ecommerce.application.entity.Cart;
import com.online.ecommerce.application.entity.Product;

public interface AddCartService {
	
	Cart addCart(Cart cart, Product productDetails) throws Exception;
 
}
