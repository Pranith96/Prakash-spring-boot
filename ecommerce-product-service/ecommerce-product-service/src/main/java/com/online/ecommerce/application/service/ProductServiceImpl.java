package com.online.ecommerce.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.ecommerce.application.entity.Product;
import com.online.ecommerce.application.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Integer productId) throws Exception {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (!optionalProduct.isPresent()) {
			throw new Exception("No Such Product Found");
		}
		return optionalProduct.get();
	}

	@Override
	public Product updateProduct(Product product) throws Exception {
		Optional<Product> productResponse = productRepository.findById(product.getProductId());
		if (!productResponse.isPresent()) {
			throw new Exception("No Such Product Found");
		}

		if (product.getProductName() != null) {
			productResponse.get().setProductName(product.getProductName());
		}
		if (product.getPricePerItem() != null) {
			productResponse.get().setPricePerItem(product.getPricePerItem());
		}
		if (product.getProductCode() != null) {
			productResponse.get().setProductCode(product.getProductCode());
		}
		if (product.getStockQuantity() != null) {
			productResponse.get().setStockQuantity(product.getStockQuantity());
		}
		
		System.out.println(product);
		Product response = productRepository.save(product);
		return response;
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);

	}

	@Override
	public String deleteProductById(Integer productId) throws Exception {
		Optional<Product> productResponse = productRepository.findById(productId);
		if (!productResponse.isPresent()) {
			throw new Exception("No Such Product Found");
		}

		productRepository.deleteById(productId);

		return "product deleted successfully";
	}
}
