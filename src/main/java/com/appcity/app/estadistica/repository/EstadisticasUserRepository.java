package com.appcity.app.estadistica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.appcity.app.estadistica.models.EstadisticasUsuarios;

public interface EstadisticasUserRepository extends MongoRepository<EstadisticasUsuarios, String> {

	@RestResource(path = "find-name")
	public EstadisticasUsuarios findByNombre(@Param("name") String nombre);

}
