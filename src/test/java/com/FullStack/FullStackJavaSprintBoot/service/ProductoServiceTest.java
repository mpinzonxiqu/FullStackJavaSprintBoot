package com.FullStack.FullStackJavaSprintBoot.service;

import com.FullStack.FullStackJavaSprintBoot.model.Producto;
import com.FullStack.FullStackJavaSprintBoot.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    private final ProductoRepository productoRepository = mock(ProductoRepository.class);
    private final ProductoService productoService = new ProductoService(productoRepository);

    @Test
    void testListarProductos() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Camisa");

        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> resultado = productoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Camisa", resultado.get(0).getNombre());

        verify(productoRepository, times(1)).findAll();
    }
}

