package com.appcity.app.estadistica.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appcity.app.estadistica.clients.PreguntasRespuestasFeignClient;
import com.appcity.app.estadistica.clients.SubscripcionesFeignClient;
import com.appcity.app.estadistica.clients.UsuariosFeignClient;
import com.appcity.app.estadistica.models.Estadisticas;
import com.appcity.app.estadistica.models.EstadisticasUsuarios;
import com.appcity.app.estadistica.models.Proyectos;
import com.appcity.app.estadistica.models.Subscripciones;
import com.appcity.app.estadistica.models.Usuario;
import com.appcity.app.estadistica.repository.EstadisticasRepository;
import com.appcity.app.estadistica.repository.EstadisticasUserRepository;
import com.appcity.app.estadistica.request.Preguntas;
import com.appcity.app.estadistica.request.Resultados;
import com.appcity.app.estadistica.services.IEstadisticaService;
import com.appcity.app.estadistica.services.UserExcelExporter;

@RestController
public class EstadisticaController {

	@Autowired
	EstadisticasRepository estadistica;

	@Autowired
	PreguntasRespuestasFeignClient proyectos;

	@Autowired
	SubscripcionesFeignClient subscripciones;

	@Autowired
	IEstadisticaService estadisticaService;
	
	@Autowired
	EstadisticasUserRepository estadisticasUsuario;
	
	@Autowired
	UsuariosFeignClient usuariosClient;

	@GetMapping("/estadistica/verEstadistica/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public Estadisticas verEstadistica(@PathVariable("nombre") String nombre) {
		obtenerEstadistica(nombre);
		return estadistica.findByNombre(nombre);
	}

	@GetMapping("/estadistica/verLikes/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer verLikes(@PathVariable("nombre") String nombre) {
		Estadisticas estadisticas = estadistica.findByNombre(nombre);
		return estadisticas.getLikes();
	}

	@GetMapping("/estadistica/verDislikes/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer verDislikes(@PathVariable("nombre") String nombre) {
		Estadisticas estadisticas = estadistica.findByNombre(nombre);
		return estadisticas.getDislikes();
	}

	@PostMapping("/estadistica/crearEna")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void crearEstadistica(@RequestParam("nombre") String nombre) {
		Estadisticas estadisticas = new Estadisticas();
		List<Resultados> resultados = new ArrayList<Resultados>();
		Integer visualizaciones = 0;
		estadisticas.setVisualizaciones(visualizaciones);
		estadisticas.setNombre(nombre);
		estadisticas.setResultados(resultados);
		estadistica.save(estadisticas);
	}

	@PutMapping("/estadistica/visualizaciones/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public void aumentarVisualizaciones(@PathVariable("nombre") String nombre) {
		Estadisticas estadisticas = estadistica.findByNombre(nombre);
		Integer visualizacion = estadisticas.getVisualizaciones();
		visualizacion++;
		estadisticas.setVisualizaciones(visualizacion);
		estadistica.save(estadisticas);
	}

	@GetMapping("/estadistica/verVisualizacion/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public Integer verVisualizaciones(@PathVariable("nombre") String nombre) {
		Estadisticas estadisticas = estadistica.findByNombre(nombre);
		return estadisticas.getVisualizaciones();
	}

	@PutMapping("/estadistica/obtenerEstadistica/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public void obtenerEstadistica(@PathVariable("nombre") String nombre) {
		Proyectos proyecto = proyectos.getProyectosByNombre(nombre);
		Estadisticas estadisticas = estadistica.findByNombre(nombre);
		Subscripciones subs = subscripciones.getProyectosByNombre(nombre);
		estadisticas.setNumeroSubscritas(subs.getSubscripciones().size());
		estadisticas.setLikes(subs.getLike().size());
		estadisticas.setDislikes(subs.getDislike().size());
		estadisticas.setNumeroComentarios(subs.getComentarios().size());
		estadisticas.setNumeroCuestionario(subs.getCuestionarios().size());
		List<List<Preguntas>> preguntas = proyecto.getPreguntas();
		List<Integer> tipoPregunta = new ArrayList<Integer>();
		List<String> enunciado = new ArrayList<String>();
		List<Resultados> listaResultados = new ArrayList<Resultados>();
		for (int i = 0; i < preguntas.size(); i++) {
			tipoPregunta.add(preguntas.get(i).get(0).getTipoConsulta());
			enunciado.add(preguntas.get(i).get(0).getPregunta());
		}
		if (estadisticas.getResultados().isEmpty()) {
			estadisticas.setResultados(listaResultados);
		} else {
			for (int i = 0; i < tipoPregunta.size(); i++) {
				Resultados resultados = new Resultados();
				resultados.setTipoConsulta(tipoPregunta.get(i));
				resultados.setNumeroPersonas(subs.getCuestionarios().size());
				List<Double> promedioPonderado = new ArrayList<Double>();
				List<Double> promedio = new ArrayList<Double>();
				List<String> respuestas = new ArrayList<String>();
				List<Integer> personasOpcion = new ArrayList<Integer>();
				resultados.setEnunciado(enunciado.get(i));
				resultados.setNumeroPersonas(proyecto.getRespuestas().get(i).size());
				resultados.setOpciones(proyecto.getPreguntas().get(i).get(0).getOpciones());
				switch (tipoPregunta.get(i)) {
				case 1:
					List<List<String>> tipoUno = estadisticaService.tipoUnoCuatro(i, proyecto, resultados);
					for (String d : tipoUno.get(0)) {
						promedioPonderado.add(Double.parseDouble(d));
					}
					resultados.setPromedioPonderado(promedioPonderado);
					resultados.setImpacto(tipoUno.get(1));
					listaResultados.add(resultados);
					break;
				case 2:
					List<List<String>> tipoDos = estadisticaService.tipoDos(i, proyecto, resultados);

					for (String d : tipoDos.get(0)) {
						promedio.add(Double.parseDouble(d));
					}
					resultados.setMenorEscogida(tipoDos.get(1).get(0));
					resultados.setMayorEscogida(tipoDos.get(1).get(1));
					resultados.setPromedio(promedio);
					listaResultados.add(resultados);
					break;
				case 3:
					List<List<String>> tipoTres = estadisticaService.tipoTres(i, proyecto, resultados);
					for (String d : tipoTres.get(0)) {
						personasOpcion.add(Integer.valueOf(d));
					}
					for (String d : tipoTres.get(2)) {
						promedio.add(Double.parseDouble(d));
					}

					resultados.setPersonasOpcion(personasOpcion);
					resultados.setRespuestas(tipoTres.get(1));
					resultados.setPromedio(promedio);
					listaResultados.add(resultados);
					break;
				case 4:
					List<List<String>> tipoCuatro = estadisticaService.tipoUnoCuatro(i, proyecto, resultados);
					for (String d : tipoCuatro.get(0)) {
						promedioPonderado.add(Double.parseDouble(d));
					}
					resultados.setPromedioPonderado(promedioPonderado);
					resultados.setImpacto(tipoCuatro.get(1));
					listaResultados.add(resultados);
					break;
				case 5:
					for (int j = 0; j < proyecto.getRespuestas().get(i).size(); j++) {
						respuestas.add(proyecto.getRespuestas().get(i).get(j).get(0));
					}
					resultados.setRespuestas(respuestas);
					listaResultados.add(resultados);
					break;
				default:
					break;
				}
			}
			estadisticas.setResultados(listaResultados);
		}
		estadistica.save(estadisticas);
	}

	@DeleteMapping("/estadistica/borrarEstadisticas/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public void borrarEstadisticas(@PathVariable String nombre) {
		Estadisticas esta = estadistica.findByNombre(nombre);
		String id = esta.getId();
		estadistica.deleteById(id);
	}
	
	@GetMapping("/estadistica/export/excel/{nombre}")
    public void exportToExcel(@PathVariable String nombre,HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        Estadisticas listUsers = estadistica.findByNombre(nombre);
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response);    
    } 
	
	@DeleteMapping("/estadistica/borrarEstadisticasUsuario/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public void borrarEstadisticasUsuario(@PathVariable String nombre) {
		EstadisticasUsuarios esta = estadisticasUsuario.findByNombre(nombre);
		String id = esta.getId();
		estadisticasUsuario.deleteById(id);
	}
	
	@GetMapping("/estadistica/verEstadisticasUsuario/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<List<String>> verEstadisticasUsuario(@PathVariable String nombre){
		EstadisticasUsuarios esta = estadisticasUsuario.findByNombre(nombre);
		return esta.getHistorialParticipacion();
	}
	
	@PostMapping("/estadistica/arreglar")
	public void arreglar() {
		List<Usuario> listaUsuarios = usuariosClient.getUsers();
		for(int i=0; i<listaUsuarios.size(); i++) {
			EstadisticasUsuarios estadisticasUsuarios = new EstadisticasUsuarios();
			List<List<String>> historialParticipacion = new ArrayList<List<String>>();
			estadisticasUsuarios.setNombre(listaUsuarios.get(i).getUsername());
			estadisticasUsuarios.setHistorialParticipacion(historialParticipacion);
			estadisticasUsuario.save(estadisticasUsuarios);
		}
	}

}
