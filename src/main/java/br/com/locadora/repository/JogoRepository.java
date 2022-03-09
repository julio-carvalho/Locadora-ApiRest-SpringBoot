package br.com.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locadora.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	@Query("Select j from Jogo j where j.idCliente = ?1")
	List<Jogo> findByJogoId(@Param("idCliente") Long idCliente);
	
	@Query("Select j from Jogo j where j.disponivel = 1")
	List<Jogo> findJogosDisponiveis();
}
