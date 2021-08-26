package com.fiap.SmartCities.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import lombok.*;

@Table(name="TB_SENSOR_VELOCIDADE_TEMPO")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "VelVento", allocationSize = 1, sequenceName = "SQ_TB_SENSOR_VEL_VENTO")
@Data
public class VelocidadeVento {
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VelVento")
	private Long id;
	private Double velocidade;
	public String DATASINAL = dtf.format(LocalDateTime.now());
}
