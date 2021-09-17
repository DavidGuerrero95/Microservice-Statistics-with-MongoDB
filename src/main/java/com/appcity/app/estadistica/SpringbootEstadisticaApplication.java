/*****************************************************************************
********	MICROSERVICES WITH SPRING BOOT				******
********	DEVELOPED BY: SANTIAGO GUERRERO				******
********	FROM UNIVERSITY OF ANTIOQUIA				******
*****************************************************************************/
package com.appcity.app.estadistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.appcity.app.estadistica.clients.PreguntasRespuestasFeignClient;
import com.appcity.app.estadistica.repository.EstadisticasRepository;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SpringbootEstadisticaApplication implements CommandLineRunner {

	@Autowired
	PreguntasRespuestasFeignClient datosMuro;

	@Autowired
	EstadisticasRepository estadistica;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEstadisticaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * for(;;){ List<Muro> muro = datosMuro.getMuro(); List<Estadisticas> est =
		 * estadistica.findAll(); for(int i=0; i<muro.size();i++) { for(int
		 * j=0;j<est.size();j++) { if(est.get(j).getCodigoProyecto() == m) }
		 * if(muro.get(i).getEnabled()) {
		 * 
		 * } } }
		 */
	}
}
