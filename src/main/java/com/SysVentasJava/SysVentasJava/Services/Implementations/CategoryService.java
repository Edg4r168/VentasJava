package com.SysVentasJava.SysVentasJava.Services.Implementations;

import com.SysVentasJava.SysVentasJava.Entities.Category;
import com.SysVentasJava.SysVentasJava.Repositories.ICategoryRepository;
import com.SysVentasJava.SysVentasJava.Services.Interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository _categoryRepository;

    @Override
    public Page<Category> findAllPaginated(Pageable pageable) {
        return _categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAll() {
        return _categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return _categoryRepository.findById(id);
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return _categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        _categoryRepository.deleteById(id);
    }
}
