package com.example.gestionDeLivres.repository;

import com.example.gestionDeLivres.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
