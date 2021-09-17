package com.appcity.app.estadistica.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.appcity.app.estadistica.models.Usuario;

@FeignClient(name = "app-usuarios")
public interface UsuariosFeignClient {

	@GetMapping("/users/listar")
	public List<Usuario> getUsers();
}
