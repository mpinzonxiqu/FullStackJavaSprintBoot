package com.FullStack.FullStackJavaSprintBoot.service;


import com.FullStack.FullStackJavaSprintBoot.dto.CompraResponse;
import com.FullStack.FullStackJavaSprintBoot.model.Inventory;
import com.FullStack.FullStackJavaSprintBoot.model.Producto;
import com.FullStack.FullStackJavaSprintBoot.repository.InventoryRepository;
import com.FullStack.FullStackJavaSprintBoot.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    private InventoryRepository inventoryRepository;
    private ProductoRepository productoRepository;
    private InventoryService inventoryService;

    @BeforeEach
    void setup() {
        inventoryRepository = mock(InventoryRepository.class);
        productoRepository = mock(ProductoRepository.class);
        inventoryService = new InventoryService(inventoryRepository, productoRepository);
    }

    @Test
    void testPurchase_StockSuficiente() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setQuantity(10);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        CompraResponse response = inventoryService.purchase(1L, 5);

        assertTrue(response.isSuccess());
        assertEquals("Compra realizada con éxito", response.getMessage());
        verify(inventoryRepository).save(any(Inventory.class));
    }

    @Test
    void testPurchase_StockInsuficiente() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setQuantity(2);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        CompraResponse response = inventoryService.purchase(1L, 5);

        assertFalse(response.isSuccess());
        assertEquals("Stock insuficiente", response.getMessage());
        verify(inventoryRepository, never()).save(any());
    }

    @Test
    void testActualizarCantidad() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setQuantity(10);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        inventoryService.actualizarCantidad(1L, 20);

        assertEquals(20, inventory.getQuantity());
        verify(inventoryRepository).save(inventory);
    }

    @Test
    void testGetCantidad_Existente() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setQuantity(15);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        int cantidad = inventoryService.getCantidad(1L);

        assertEquals(15, cantidad);
    }
}
