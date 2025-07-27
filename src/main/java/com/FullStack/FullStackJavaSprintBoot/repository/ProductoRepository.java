package com.FullStack.FullStackJavaSprintBoot.repository;

import com.FullStack.FullStackJavaSprintBoot.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
