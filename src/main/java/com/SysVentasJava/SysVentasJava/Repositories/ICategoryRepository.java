package com.SysVentasJava.SysVentasJava.Repositories;

import com.SysVentasJava.SysVentasJava.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
