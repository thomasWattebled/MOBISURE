package com.example.devis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.devis.model.Voyage;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
}
