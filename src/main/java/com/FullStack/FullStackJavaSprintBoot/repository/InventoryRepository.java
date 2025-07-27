package com.FullStack.FullStackJavaSprintBoot.repository;


import java.util.Optional;

import com.FullStack.FullStackJavaSprintBoot.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductoId(Long productoId);
}
