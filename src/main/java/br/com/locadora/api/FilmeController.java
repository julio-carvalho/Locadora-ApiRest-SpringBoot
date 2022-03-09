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
import br.com.locadora.model.Filme;
import br.com.locadora.repository.FilmeRepository;
import br.com.locadora.service.ClienteService;
import br.com.locadora.service.FilmeService;

@RestController
@RequestMapping("api/filme")
public class FilmeController {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private FilmeService filmeService;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Filme>> listaFilmes() {
		List<Filme> filmes = new ArrayList();
		filmes = filmeService.buscarFilmes();
		return ResponseEntity.ok().body(filmes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Filme>> getFilmeById(@PathVariable Long id) {
		Optional<Filme> filme = filmeService.findByIdFilme(id);
		return ResponseEntity.ok().body(filme);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastraFilme(@RequestBody Filme filme) {
		filmeService.createFilme(filme);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filme.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> deleteFilmeById(@PathVariable Long id) {
		filmeService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> updateFilmeId(@RequestBody Filme filme, @PathVariable Long id) {
		Filme filmeUpdate = filme;
		filmeUpdate.setId(id);
		filmeService.updateFilme(filmeUpdate);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/alugar/{id}")
	public ResponseEntity<?> alugaFilme(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
		Optional<Filme> buscaFilmeId = filmeService.findByIdFilme(id);
		
		if(!buscaFilmeId.get().isDisponivel()) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliCpf = clienteService.findByCPF(clienteDTO.getCpf());
		
		if(cliCpf == null) {
			return ResponseEntity.notFound().build();
		}
		
		
		filmeService.updateDisponivelFalse(buscaFilmeId.get(), cliCpf.getId());
		return ResponseEntity.ok().body(buscaFilmeId);
	}
	
	@PostMapping("/devolver/{id}")
	public ResponseEntity<?> devolveFilme(@PathVariable Long id) {
		Optional<Filme> buscaFilmeId = filmeService.findByIdFilme(id);
		
		if(buscaFilmeId.get().isDisponivel()) {
			return ResponseEntity.notFound().build();
		}
		
		filmeService.updateDisponivelTrue(buscaFilmeId.get());
		return ResponseEntity.ok().body(buscaFilmeId);
	}
	
	//lista de filme que o cliente tem alugado
	@PostMapping("/cliente/{id}")
	public ResponseEntity<?> filmeClienteById(@PathVariable Long id) {
		List<Filme> filmes = new ArrayList();
		filmes = filmeService.buscaFilmeByIdCliente(id);
		
		
		if(filmes == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(filmes);
	}
	
	//lista de jogos disponíveis
	@GetMapping("/disponivel")
	public ResponseEntity<?> filmesDisponiveis() {
		List<Filme> filmes = new ArrayList();
		filmes = filmeService.buscaFilmesDisponiveis();
		
		if(filmes == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(filmes);
	}
}
