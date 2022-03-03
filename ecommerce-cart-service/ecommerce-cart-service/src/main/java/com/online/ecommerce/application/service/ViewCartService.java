package com.online.ecommerce.application.service;

import java.util.Optional;

import com.online.ecommerce.application.entity.Cart;

public interface ViewCartService {

	Optional<Cart> viewCart(Integer cartId);

}
