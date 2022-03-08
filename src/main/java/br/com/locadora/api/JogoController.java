package br.com.locadora.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.locadora.dto.ClienteDTO;
import br.com.locadora.model.Cliente;
import br.com.locadora.model.Jogo;
import br.com.locadora.repository.JogoRepository;
import br.com.locadora.service.ClienteService;
import br.com.locadora.service.JogoService;

@RestController
@RequestMapping("api/jogo")
public class JogoController {
	
	@Autowired
	private JogoRepository jogoRepository;
	
	@Autowired
	private JogoService jogoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Jogo>> listaJogos() {
		List<Jogo> jogos = new ArrayList();
		jogos = jogoService.buscarJogos();
		return ResponseEntity.ok().body(jogos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Jogo>> getFilmeById(@PathVariable Long id) {
		Optional<Jogo> jogo = jogoService.findByIdJogo(id);
		return ResponseEntity.ok().body(jogo);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastraJogo(@RequestBody Jogo jogo) {
		jogoService.createJogo(jogo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> deleteJogoById(@PathVariable Long id) {
		jogoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> updateJogoId(@RequestBody Jogo jogo, @PathVariable Long id) {
		Jogo jogoUpdate = jogo;
		jogoUpdate.setId(id);
		jogoService.updateJogo(jogoUpdate);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/alugar/{id}")
	private ResponseEntity<?> alugaFilme(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
		Optional<Jogo> buscaJogoId = jogoService.findByIdJogo(id);
		
		if(!buscaJogoId.get().isDisponivel()) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliCpf = clienteService.findByCPF(clienteDTO.getCpf());
		
		if(cliCpf == null) {
			return ResponseEntity.notFound().build();
		}
		
		
		jogoService.updateDisponivelFalse(buscaJogoId.get(), cliCpf.getId());
		return ResponseEntity.ok().body(buscaJogoId);
	}
	
	@PostMapping("/devolver/{id}")
	private ResponseEntity<?> devolveJogo(@PathVariable Long id) {
		Optional<Jogo> buscaJogoId = jogoService.findByIdJogo(id);
		
		if(buscaJogoId.get().isDisponivel()) {
			return ResponseEntity.notFound().build();
		}
		
		jogoService.updateDisponivelTrue(buscaJogoId.get());
		return ResponseEntity.ok().body(buscaJogoId);
	}
}
