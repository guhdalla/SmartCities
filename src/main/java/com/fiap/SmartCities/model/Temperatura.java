package com.fiap.SmartCities.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_SENSOR_TEMP")
@Entity
@SequenceGenerator(name = "temperatura", allocationSize = 1, sequenceName = "SQ_TB_SENSOR_TEMP")
public class Temperatura {

	public Temperatura(int i, int j, String dATASINAL2) {
		// TODO Auto-generated constructor stub
	}
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "temperatura")
	private Long id;
	private Double temperatura;
	private String DATASINAL = dtf.format(LocalDateTime.now());
 
}
