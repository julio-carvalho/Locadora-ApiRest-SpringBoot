package br.com.locadora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.model.Filme;
import br.com.locadora.repository.FilmeRepository;

@Service
public class FilmeService {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	public Filme createFilme(Filme filme) {
		filme.setDisponivel(true);
		return filmeRepository.save(filme);
	}
	
	public Filme salvaFilme(Filme filme) {
		return filmeRepository.save(filme);
	}
	
	public List<Filme> buscarFilmes() {
		return filmeRepository.findAll();
	}
	
	public Optional<Filme> findById(Long id) {
		return filmeRepository.findById(id);
	}
	
	public void delete(Long id) {
		filmeRepository.delete(this.getFilme(id));
	}
	
	public Filme getFilme(Long id) {
		return filmeRepository.getById(id);
	}
	
	public void updateFilme(Filme filme) {
		Optional<Filme> filmeUpdate = filmeRepository.findById(filme.getId());
		this.atualizarDados(filmeUpdate, filme);
		filmeRepository.save(filmeUpdate.get());
	}
	
	private void atualizarDados(Optional<Filme> filmeUpdate, Filme filme) {
		filmeUpdate.get().setNome(filme.getNome());
		filmeUpdate.get().setGenero(filme.getGenero());
		filmeUpdate.get().setClassificacao(filme.getClassificacao());
		filmeUpdate.get().setAno(filme.getAno());
	}
	
}
