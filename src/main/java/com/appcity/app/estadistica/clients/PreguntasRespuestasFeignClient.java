package com.appcity.app.estadistica.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.appcity.app.estadistica.models.Proyectos;


@FeignClient(name = "app-preguntasrespuestas")
public interface PreguntasRespuestasFeignClient {

	@GetMapping("/preguntasrespuestas/obtenerProyectoByNombre/{nombre}")
	public Proyectos getProyectosByNombre(@PathVariable("nombre") String nombre);
	
}
