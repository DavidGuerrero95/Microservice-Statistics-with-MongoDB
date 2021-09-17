package com.appcity.app.estadistica.models;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estadisticas_user")
public class EstadisticasUsuarios {

	@Id
	private String id;

	@NotBlank(message = "Name cannot be null")
	@Size(max = 50)
	private String nombre;

	private List<List<String>> historialParticipacion;

	public EstadisticasUsuarios() {
	}

	public EstadisticasUsuarios(String nombre, List<List<String>> historialParticipacion) {
		super();
		this.nombre = nombre;
		this.historialParticipacion = historialParticipacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<List<String>> getHistorialParticipacion() {
		return historialParticipacion;
	}

	public void setHistorialParticipacion(List<List<String>> historialParticipacion) {
		this.historialParticipacion = historialParticipacion;
	}
	

}
