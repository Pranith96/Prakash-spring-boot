package com.online.ecommerce.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.online.ecommerce.application.entity.Cart;
import com.online.ecommerce.application.entity.Product;
import com.online.ecommerce.application.service.AddCartService;

@RestController
@RequestMapping("/cart")
public class AddCartController {

	@Autowired
	AddCartService addCartService;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/add")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) throws Exception {
		Integer productId = cart.getProductId();
		System.out.println(productId);
		Cart responseItem = null;
		Product productDetails = null;
		String productServiceGetOrderURL = "http://localhost:9999/product/get/{productId}";
		try {
			productDetails = restTemplate.getForObject(productServiceGetOrderURL, Product.class, productId);
		} catch (ResourceAccessException ex) {
			ex.printStackTrace();
		}
		System.out.println(productDetails);
		if (productDetails.getProductId().equals(productId)) {
			if (productDetails.getStockQuantity() <= 0) {
				throw new Exception("Stock Quantity is empty");
			}
		}

		if (cart.getQuantity() <= productDetails.getStockQuantity()) {
			try {
				responseItem = addCartService.addCart(cart, productDetails);

				Integer updatedStockQuantity = productDetails.getStockQuantity() - cart.getQuantity();
				System.out.println(updatedStockQuantity);
				productDetails.setStockQuantity(updatedStockQuantity);
				String productServiceUpdateURL = "http://localhost:9999/product/update/details";
				restTemplate.put(productServiceUpdateURL, productDetails);

			} catch (Exception e) {
				throw new Exception("enter less quantity or product does not exist", e);
			}
		} else {
			throw new Exception("enter less quantity");
		}

		return ResponseEntity.status(HttpStatus.OK).body(responseItem);
	}

	@GetMapping("/getproductdetails")
	public ResponseEntity<Product[]> getAllProducts() {
		String url = "http://localhost:9999/product/get";
		Product[] productDetails = restTemplate.getForObject(url, Product[].class);

		return ResponseEntity.status(HttpStatus.OK).body(productDetails);
	}

	@GetMapping("/add/product")
	public ResponseEntity<Product> addProduct() {

		String url = "http://localhost:9999/product/add";
		Product product = new Product();
		product.setProductCode("678tyu");
		product.setPricePerItem(450.0);
		product.setProductName("mobile");
		product.setStockQuantity(30);
		Product productDetails = restTemplate.postForObject(url, product, Product.class);

		return ResponseEntity.status(HttpStatus.OK).body(productDetails);
	}

	@GetMapping("/delete/product")
	public ResponseEntity<String> deleteProduct() {

		String url = "http://localhost:9999/product/delete/{productId}";
		Integer productId = 5;
		restTemplate.delete(url, productId);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
	}
}
