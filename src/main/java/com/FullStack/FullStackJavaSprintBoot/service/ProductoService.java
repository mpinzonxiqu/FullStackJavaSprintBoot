package com.FullStack.FullStackJavaSprintBoot.service;

import com.FullStack.FullStackJavaSprintBoot.model.Producto;
import com.FullStack.FullStackJavaSprintBoot.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizarProducto(Long id, Producto nuevoProducto) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(nuevoProducto.getNombre());
            p.setPrecio(nuevoProducto.getPrecio());
            p.setDescripcion(nuevoProducto.getDescripcion());
            return productoRepository.save(p);
        });
    }

    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

}
