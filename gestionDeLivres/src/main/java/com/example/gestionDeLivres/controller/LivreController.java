package com.example.gestionDeLivres.controller;

import com.example.gestionDeLivres.model.Livre;
import com.example.gestionDeLivres.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class LivreController {
    private final LivreService productService;

    @Autowired
    public LivreController(LivreService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Livre>> listProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Livre>> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Livre> createProduct(@RequestBody Livre product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateProduct(@PathVariable Long id, @RequestBody Livre product) {
        product.setId(id);
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}