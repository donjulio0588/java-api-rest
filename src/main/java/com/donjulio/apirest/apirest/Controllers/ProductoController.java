package com.donjulio.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donjulio.apirest.apirest.Repositories.ProductoRepository;
import com.donjulio.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Unable to find product by the provided Id"));
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    };

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto updatedProduct) {
        Producto foundedProduct = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Unable to find product by the provided Id: " + id));
       
        foundedProduct.setNombre(updatedProduct.getNombre());
        foundedProduct.setPrecio(updatedProduct.getPrecio());

        return productoRepository.save(foundedProduct);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Producto foundedProduct = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Unable to find product by the provided Id: " + id));

        productoRepository.delete(foundedProduct);
        return "Product succefully deleted";
    }
}
