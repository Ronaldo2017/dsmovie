package com.devsuperior.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.repositories.MovieRepository;

@Service // registra como um componente do sistema
//depende do MovieDTO
public class MovieService {

	// composicao de componentes
	@Autowired // resolve a dependencia
	private MovieRepository repository;

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		// traz a busca por paginas das entidades
		Page<Movie> result = repository.findAll(pageable);
		//converte para DTO e retorna
		Page<MovieDTO> page = result.map(x -> new MovieDTO(x));// retorna a pagina
		return page;
		//map = aplica uma funcao a cada elemento do lista
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		// traz a busca por id
		Movie result = repository.findById(id).get();
		//converte para DTO e retorna
		MovieDTO dto = new MovieDTO(result);
		return dto;
	}
	
	
}
