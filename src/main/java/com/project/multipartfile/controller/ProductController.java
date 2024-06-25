package com.project.multipartfile.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.multipartfile.entity.Product;
import com.project.multipartfile.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/addNewProduct")
	public String addNewProductPage() {
		return "addNewProduct";
	}

	@PostMapping("/addNewProduct")
	public String addNewProduct(@RequestParam(value = "productName") String productName,
			@RequestParam(value = "productImage") MultipartFile multipartFile, Model model) {
		try {
			Product product = new Product();
			product.setProductName(productName);
			product.setProductImage(multipartFile.getBytes());
			productService.addNewProduct(product);
			model.addAttribute("message", "Image Successfuly Added...");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			System.out.println(e.getMessage());
			return "addNewProduct";
		}
		return "redirect:/";

	}

	@GetMapping("/")
	public String loadProducts(Model model) {

		System.out.println("products fetching");

		List<Product> products = productService.getAllProducts();

		if (!products.isEmpty()) {
			System.out.println("products are there");
			model.addAttribute("products", products);
		} else {
			model.addAttribute("productIsPresent", true);
			System.out.println("No Products are there");
		}

		return "home";
	}

	@GetMapping("/getImage/{productId}")
	public ResponseEntity<byte[]> getImage(@PathVariable(value = "productId") Long productId) {

		Optional<Product> optional = productService.getProductById(productId);

		if (optional.isPresent()) {
			Product product = optional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + product.getProductName() + "\"")
					.body(product.getProductImage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
