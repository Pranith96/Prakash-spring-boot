package com.online.ecommerce.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.ecommerce.application.entity.Product;
import com.online.ecommerce.application.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws Exception {
		Product productResponse = productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@GetMapping("/get/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "productId") Integer productId)
			throws Exception {
		Product productResponse = productService.getProductById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}

	@PutMapping("/update/details")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
		Product productResponse = productService.updateProduct(product);
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable(name = "productId") Integer productId)
			throws Exception {
		String productResponse = productService.deleteProductById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}

}
