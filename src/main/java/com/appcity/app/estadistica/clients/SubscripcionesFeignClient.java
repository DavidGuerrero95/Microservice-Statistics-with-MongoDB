package com.appcity.app.estadistica.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.appcity.app.estadistica.models.Subscripciones;

@FeignClient(name = "app-subscripciones")
public interface SubscripcionesFeignClient {

	@GetMapping("/subscripciones/obtenerProyectoByNombre/{nombre}")
	public Subscripciones getProyectosByNombre(@PathVariable("nombre") String nombre);
	
}
