package br.com.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.locadora.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
	@Query("Select f from Filme f where f.idCliente = ?1")
	List<Filme> findByFilmeId(@Param("idCliente") Long idCliente);
	
	@Query("Select f from Filme f where f.disponivel = 1")
	List<Filme> findFilmesDisponiveis();
}
