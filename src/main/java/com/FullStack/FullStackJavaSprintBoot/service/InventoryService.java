package com.FullStack.FullStackJavaSprintBoot.service;

import com.FullStack.FullStackJavaSprintBoot.model.Inventory;
import com.FullStack.FullStackJavaSprintBoot.model.Producto;
import com.FullStack.FullStackJavaSprintBoot.repository.InventoryRepository;
import com.FullStack.FullStackJavaSprintBoot.repository.ProductoRepository;
import com.FullStack.FullStackJavaSprintBoot.dto.CompraResponse;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepo;
    private final ProductoRepository productoRepo;

    public InventoryService(InventoryRepository inventoryRepo, ProductoRepository productoRepo) {
        this.inventoryRepo = inventoryRepo;
        this.productoRepo = productoRepo;
    }

    // ✅ Crear inventario
    public Inventory crearInventario(Long productoId, int cantidad) {
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productoId));

        Inventory inventario = new Inventory();
        inventario.setProducto(producto);
        inventario.setQuantity(cantidad);

        return inventoryRepo.save(inventario);
    }

    // ✅ Realizar compra
    public CompraResponse purchase(Long productoId, int cantidadSolicitada) {
        Inventory inventory = inventoryRepo.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en inventario"));

        if (inventory.getQuantity() < cantidadSolicitada) {
            return new CompraResponse(false, "Stock insuficiente");
        }

        inventory.setQuantity(inventory.getQuantity() - cantidadSolicitada);
        inventoryRepo.save(inventory);

        return new CompraResponse(true, "Compra realizada con éxito");
    }

    // ✅ Consultar cantidad en stock
    public int getCantidad(Long productoId) {
        Inventory inventory = inventoryRepo.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        return inventory.getQuantity();
    }

    // ✅ Actualizar cantidad
    public void actualizarCantidad(Long productoId, int nuevaCantidad) {
        Inventory inventory = inventoryRepo.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        inventory.setQuantity(nuevaCantidad);
        inventoryRepo.save(inventory);
    }
}
