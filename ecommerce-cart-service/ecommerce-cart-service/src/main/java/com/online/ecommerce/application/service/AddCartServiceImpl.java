package com.online.ecommerce.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.ecommerce.application.entity.Cart;
import com.online.ecommerce.application.entity.Product;
import com.online.ecommerce.application.repository.CartRepository;

@Service
@Transactional
public class AddCartServiceImpl implements AddCartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public Cart addCart(Cart item, Product productDetails) throws Exception {
		double totalPrice = item.getQuantity() * productDetails.getPricePerItem();
		item.setItemPrice(totalPrice);
		Cart response = cartRepository.save(item);
		if (response == null) {
			throw new Exception("data not saved");
		}

		return response;
	}

}
