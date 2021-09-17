package com.appcity.app.estadistica.request;

import java.util.List;

public class Preguntas {

	private Integer numeroPregunta;
	private Integer tipoConsulta;
	private String pregunta;
	private String informacion;
	private List<String> opciones;
	private Boolean obligatorio;

	public Preguntas() {
	}

	public Preguntas(Integer numeroPregunta, Integer tipoConsulta, String pregunta, String informacion,
			List<String> opciones, Boolean obligatorio) {
		super();
		this.numeroPregunta = numeroPregunta;
		this.tipoConsulta = tipoConsulta;
		this.pregunta = pregunta;
		this.informacion = informacion;
		this.opciones = opciones;
		this.obligatorio = obligatorio;
	}

	public Integer getNumeroPregunta() {
		return numeroPregunta;
	}

	public void setNumeroPregunta(Integer numeroPregunta) {
		this.numeroPregunta = numeroPregunta;
	}

	public Integer getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(Integer tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public Boolean getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(Boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

}
