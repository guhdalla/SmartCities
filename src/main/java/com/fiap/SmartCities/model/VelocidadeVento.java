package com.fiap.SmartCities.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_SENSOR_VELOCIDADE_TEMPO")
@SequenceGenerator(name = "VelVento", allocationSize = 1, sequenceName = "SQ_TB_SENSOR_VEL_VENTO")
public class VelocidadeVento {
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "VelVento")
	private Long id;
	private double kilometrosHora;
	private String DATASINAL = dtf.format(LocalDateTime.now());
 

}
