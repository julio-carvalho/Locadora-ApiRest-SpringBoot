package br.com.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
