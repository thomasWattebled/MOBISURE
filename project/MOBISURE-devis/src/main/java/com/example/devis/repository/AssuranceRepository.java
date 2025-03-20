package com.example.devis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.devis.entity.Assurance;

	
public interface AssuranceRepository extends MongoRepository<Assurance,String> {

	List<Assurance> findByClientId(Long clientId);
	
}
