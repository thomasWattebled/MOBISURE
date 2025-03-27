package com.example.devis.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.devis.Enum.TypeAssurance;

public class AssuranceTest {

	private Assurance assurance;
	
	@BeforeEach
    void setUp() {
		assurance = new Assurance(1L,TypeAssurance.MOTO);
	}
	
	@Test
	void testContructeur() {
		assertEquals(1L,assurance.getClientId());
		assertEquals(TypeAssurance.MOTO,assurance.getType());
		assertNotNull(assurance.getNumDossier());
	}
	
	@Test
	void testClientId() {
		assertEquals(1L,assurance.getClientId());
		assurance.setClientId(2L);
		assertEquals(2L,assurance.getClientId());
	}
	
	@Test
	void testType() {
		assertEquals(TypeAssurance.MOTO,assurance.getType());
		assurance.setType(TypeAssurance.VACANCES);
		assertEquals(TypeAssurance.VACANCES,assurance.getType());
	}
	
	@Test 
	void testId() {
		assurance.setId("1");
		assertEquals("1",assurance.getId());
	}
	
	@Test
	void testNumDossier() {
		assertNotNull(assurance.getNumDossier());
		assurance.setNumDossier("D-365");
		assertEquals("D-365",assurance.getNumDossier());
	}
	
}
