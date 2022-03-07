package com.online.ecommerce.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws Exception {
		log.info("In product add controller");
		Product productResponse = productService.addProduct(product);
		log.info("In product add controller after service call");

		return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Product>> getAllProducts() {
		log.info("In product add controller");
		List<Product> products = productService.getAllProducts();
		log.info("In product add controller after service call");
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@GetMapping("/get/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "productId") Integer productId)
			throws Exception {
		log.info("In product add controller");
		Product productResponse = productService.getProductById(productId);
		log.info("In product add controller after service call");
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}

	@PutMapping("/update/details")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
		log.info("In product add controller");
		Product productResponse = productService.updateProduct(product);
		log.info("In product add controller after service call");
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable(name = "productId") Integer productId)
			throws Exception {
		log.info("In product add controller");
		String productResponse = productService.deleteProductById(productId);
		log.info("In product add controller after service call");
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}

}
