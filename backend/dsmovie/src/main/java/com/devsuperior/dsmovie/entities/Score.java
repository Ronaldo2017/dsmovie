package com.devsuperior.dsmovie.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_score")
public class Score {

	// faz a referencia das classes user e movie
	// onde se faz necessario criar outra classe para criar
	// a chave composta/ fazendo a associacao
	
	@EmbeddedId
	private ScorePK id = new ScorePK();

	private Double value;

	public Score() {

	}

	// associar um filme com o score
	// vai na classe ScorePK e salva a referencia do movie
	public void setMovie(Movie movie) {
		id.setMovie(movie);
	}

	// vai na classe ScorePK e salva a referencia do usuario
	public void setUser(User user) {
		id.setUser(user);
	}

	public ScorePK getId() {
		return id;
	}

	public void setId(ScorePK id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
