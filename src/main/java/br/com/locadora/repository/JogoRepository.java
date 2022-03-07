package br.com.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

}
