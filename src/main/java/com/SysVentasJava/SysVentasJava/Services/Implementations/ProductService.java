package com.SysVentasJava.SysVentasJava.Services.Implementations;

import com.SysVentasJava.SysVentasJava.Entities.Product;
import com.SysVentasJava.SysVentasJava.Repositories.IProductRepository;
import com.SysVentasJava.SysVentasJava.Services.Interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository _productRepository;

    @Override
    public Page<Product> findAllPaginated(Pageable pageable) {
        return _productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return _productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return _productRepository.findById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return _productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        _productRepository.deleteById(id);
    }
}
