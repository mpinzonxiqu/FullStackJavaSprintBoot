package com.FullStack.FullStackJavaSprintBoot.controller;

import com.FullStack.FullStackJavaSprintBoot.dto.CompraRequest;
import com.FullStack.FullStackJavaSprintBoot.dto.CompraResponse;
import com.FullStack.FullStackJavaSprintBoot.dto.UpdateCantidadRequest;
import com.FullStack.FullStackJavaSprintBoot.model.Inventory;
import com.FullStack.FullStackJavaSprintBoot.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/purchase")
    public ResponseEntity<CompraResponse> purchase(@RequestBody CompraRequest request) {
        return ResponseEntity.ok(service.purchase(request.getProductId(), request.getQuantity()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integer> getQuantity(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCantidad(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateCantidadRequest request) {
        service.actualizarCantidad(id, request.getNuevaCantidad());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<Inventory> crearInventario(@RequestBody CrearInventarioRequest request) {
        Inventory inventario = service.crearInventario(request.getProductoId(), request.getCantidad());
        return ResponseEntity.ok(inventario);
    }



}
