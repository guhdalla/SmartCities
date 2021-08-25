package com.fiap.SmartCities.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_SENSOR_LUMINOSIDADE")
@SequenceGenerator(name = "luminosidade", allocationSize = 1, sequenceName = "SQ_TB_SENSOR_LUMINOSIDADE")
public class Luminosidade {

	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "luminosidade")
	private Long id;
	private double luminosidade;
	private String DATASINAL = dtf.format(LocalDateTime.now());
	
	
	
	
}
