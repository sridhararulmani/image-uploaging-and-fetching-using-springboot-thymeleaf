package com.project.multipartfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.multipartfile.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
