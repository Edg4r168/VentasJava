package com.SysVentasJava.SysVentasJava.Services.Interfaces;

import com.SysVentasJava.SysVentasJava.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Product> findAllPaginated(Pageable pageable);

    List<Product> findAll();

    Optional<Product> findById(Integer id);

    Product saveOrUpdate(Product product);

    void deleteById(Integer id);
}
