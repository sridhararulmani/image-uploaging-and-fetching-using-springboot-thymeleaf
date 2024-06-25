package com.project.multipartfile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.multipartfile.entity.Product;

@Service
public interface ProductService {

	public List<Product> getAllProducts();

	public void addNewProduct(Product product);

	public Optional<Product> getProductById(Long productId);

}
