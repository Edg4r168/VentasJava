package com.SysVentasJava.SysVentasJava.Repositories;

import com.SysVentasJava.SysVentasJava.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
