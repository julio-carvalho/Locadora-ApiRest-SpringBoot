package br.com.locadora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.model.Jogo;
import br.com.locadora.repository.JogoRepository;

@Service
public class JogoService {
	
	@Autowired
	private JogoRepository jogoRepository;
	
	public Jogo createJogo(Jogo jogo) {
		jogo.setDisponivel(true);
		jogo.setIdCliente(null);
		return jogoRepository.save(jogo);
	}
	
	public Jogo salvaJogo(Jogo jogo) {
		return jogoRepository.save(jogo);
	}
	
	public List<Jogo> buscarJogos() {
		return jogoRepository.findAll();
	}
	
	public Optional<Jogo> findByIdJogo(Long id) {
		return jogoRepository.findById(id);
	}
	
	public void delete(Long id) {
		jogoRepository.delete(this.getJogo(id));
	}
	
	public Jogo getJogo(Long id) {
		return jogoRepository.getById(id);
	}
	
	public void updateJogo(Jogo jogo) {
		Optional<Jogo> jogoUpdate = jogoRepository.findById(jogo.getId());
		this.atualizarDados(jogoUpdate, jogo);
		jogoRepository.save(jogoUpdate.get());
	}
	
	private void atualizarDados(Optional<Jogo> jogoUpdate, Jogo jogo) {
		jogoUpdate.get().setNome(jogo.getNome());
		jogoUpdate.get().setGenero(jogo.getGenero());
		jogoUpdate.get().setClassificacao(jogo.getClassificacao());
		jogoUpdate.get().setAno(jogo.getAno());
		jogoUpdate.get().setValor(jogo.getValor());
	}
	
	//alugar filme
	public void updateDisponivelFalse(Jogo jogo, Long idCliente) {
		Optional<Jogo> jogoUpdate = jogoRepository.findById(jogo.getId());
		jogoUpdate.get().setDisponivel(false);
		jogoUpdate.get().setIdCliente(idCliente);
		jogoRepository.save(jogoUpdate.get());
	}
	
	//devolver filme
	public void updateDisponivelTrue(Jogo jogo) {
		Optional<Jogo> jogoUpdate = jogoRepository.findById(jogo.getId());
		jogoUpdate.get().setDisponivel(true);
		jogoUpdate.get().setIdCliente(null);
		jogoRepository.save(jogoUpdate.get());
	}
}
