package com.appcity.app.estadistica.models;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.appcity.app.estadistica.request.Resultados;

@Document(collection = "estadisticas_ena")
public class Estadisticas {

	@Id
	private String id;

	@NotBlank(message = "Name cannot be null")
	@Size(max = 50)
	private String nombre;

	private Integer visualizaciones;

	private Integer likes;

	private Integer dislikes;

	private Integer numeroSubscritas;

	private Integer numeroCuestionario;

	private Integer numeroComentarios;

	private List<Resultados> resultados;

	public Estadisticas() {
	}

	public Estadisticas(String nombre, Integer visualizaciones, Integer likes, Integer dislikes,
			Integer numeroSubscritas, Integer numeroCuestionario, Integer numeroComentarios,
			List<Resultados> resultados) {
		super();
		this.nombre = nombre;
		this.visualizaciones = visualizaciones;
		this.likes = likes;
		this.dislikes = dislikes;
		this.numeroSubscritas = numeroSubscritas;
		this.numeroCuestionario = numeroCuestionario;
		this.numeroComentarios = numeroComentarios;
		this.resultados = resultados;
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

	public Integer getVisualizaciones() {
		return visualizaciones;
	}

	public void setVisualizaciones(Integer visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public Integer getNumeroSubscritas() {
		return numeroSubscritas;
	}

	public void setNumeroSubscritas(Integer numeroSubscritas) {
		this.numeroSubscritas = numeroSubscritas;
	}

	public Integer getNumeroCuestionario() {
		return numeroCuestionario;
	}

	public void setNumeroCuestionario(Integer numeroCuestionario) {
		this.numeroCuestionario = numeroCuestionario;
	}

	public Integer getNumeroComentarios() {
		return numeroComentarios;
	}

	public void setNumeroComentarios(Integer numeroComentarios) {
		this.numeroComentarios = numeroComentarios;
	}

	public List<Resultados> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultados> resultados) {
		this.resultados = resultados;
	}

}