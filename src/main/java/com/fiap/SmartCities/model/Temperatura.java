package com.fiap.SmartCities.model;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import lombok.*;


@Table(name="TB_SENSOR_TEMP")
@Entity
@SequenceGenerator(name = "temperatura", allocationSize = 1, sequenceName = "SQ_TB_SENSOR_TEMP")
@Data
public class Temperatura {

	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "temperatura")
	private Long id;
	private Double temperatura;
	private String DATASINAL = dtf.format(LocalDateTime.now());
}
