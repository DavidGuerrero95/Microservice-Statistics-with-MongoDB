package com.appcity.app.estadistica.services;

import java.util.List;

import com.appcity.app.estadistica.models.Proyectos;
import com.appcity.app.estadistica.request.Resultados;

public interface IEstadisticaService {

	public List<List<String>> tipoUnoCuatro(int i, Proyectos proyecto, Resultados resultados);
	
	public List<List<String>> tipoDos(int i, Proyectos proyecto, Resultados resultados);
	
	public List<List<String>> tipoTres(int i, Proyectos proyecto, Resultados resultados);


}
