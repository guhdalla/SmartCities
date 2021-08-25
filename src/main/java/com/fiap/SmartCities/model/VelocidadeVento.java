package com.fiap.SmartCities.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import lombok.*;

@Table(name="TB_SENSOR_VELOCIDADE_TEMPO")
@Entity
@SequenceGenerator(name = "VelVento", allocationSize = 1, sequenceName = "SQ_TB_SENSOR_VEL_VENTO")
@Data
public class VelocidadeVento {
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "VelVento")
	private Long id;
	private double kilometrosHora;
	private String DATASINAL = dtf.format(LocalDateTime.now());
}
