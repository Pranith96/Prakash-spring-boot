package com.online.ecommerce.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.ecommerce.application.service.RemoveCartService;

@RestController
@RequestMapping("/cart")
public class RemoveCartController {

	@Autowired
	RemoveCartService removeCartService;
	
	@DeleteMapping(value = "/remove/{cartId}")
	public ResponseEntity<String> removeItemFromCart(@PathVariable("cartId") Integer cartId) throws Exception {
		String removeItem = removeCartService.removeItemFromCart(cartId);
		return ResponseEntity.status(HttpStatus.OK).body(removeItem);

	}
}
