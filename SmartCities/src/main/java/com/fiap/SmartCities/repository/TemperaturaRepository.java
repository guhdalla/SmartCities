package com.fiap.SmartCities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.SmartCities.model.Temperatura;

@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Long> {

	
}
