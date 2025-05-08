package com.example.gestionDeLivres.service;

import com.example.gestionDeLivres.model.Livre;
import com.example.gestionDeLivres.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    
    private final LivreRepository productRepository;

    @Autowired
    public LivreService(LivreRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Livre> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Livre> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Livre saveProduct(Livre product) {
        return productRepository.save(product);
    }

    public Livre updateProduct(Long id, Livre productDetails) {
        Livre product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}