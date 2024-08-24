package com.SysVentasJava.SysVentasJava.Services.Interfaces;

import com.SysVentasJava.SysVentasJava.Entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAllPaginated(Pageable pageable);

    List<Category> findAll();

    Optional<Category> findById(Integer id);

    Category saveOrUpdate(Category category);

    void deleteById(Integer id);
}
