package com.project.multipartfile.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.multipartfile.entity.Product;
import com.project.multipartfile.repository.ProductRepository;
import com.project.multipartfile.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public void addNewProduct(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public Optional<Product> getProductById(Long productId) {
		return productRepository.findById(productId);
	}
}
