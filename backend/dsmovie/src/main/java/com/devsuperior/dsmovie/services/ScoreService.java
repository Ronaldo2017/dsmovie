package com.devsuperior.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	/*
	 * busca o filme atraves do id no bd
	 */

	@Autowired // resolve a dependencia
	private MovieRepository movieRepository;

	// composicao de componentes

	@Autowired // resolve a dependencia
	private UserRepository userRepository;
	@Autowired // resolve a dependencia
	private ScoreRepository scoreRepository;
	
	//salvar novo score a partir do scoreDTO
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		User user = userRepository.findByEmail(dto.getEmail());
		if(user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			//salvando no bd
			user = userRepository.saveAndFlush(user);
		}
		
		//pegando o filme
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		
		//salvando o score
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		//salva o score
		score = scoreRepository.saveAndFlush(score);
		
		//percorre a lista de score e acumula a soma de scores
		double sum = 0.0;
		for (Score s : movie.getScores()) {
			sum += s.getValue();
		}
		
		//media do score
		double avg = sum / movie.getScores().size();
		
		//atualiza os scores
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		//salva no bd
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
		
	}

}
