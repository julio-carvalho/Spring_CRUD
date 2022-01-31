package br.com.crudsb.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.crudsb.model.Pessoa;
import br.com.crudsb.repository.PessoaRepository;

@Controller
public class PessoaController {

	private PessoaRepository pessoaRepo;

	public PessoaController(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	@GetMapping("/bean/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "bean/pessoas/index";
	}
	
	@GetMapping("/bean/pessoas/nova")
	public String cadastrar(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "/bean/pessoas/form";
	}
	
	@PostMapping("bean/pessoas/salvar")
	public String salvar(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return "redirect:/bean/pessoas";
	}
	
	@GetMapping("/bean/pessoas/{id}")
	public String alterar(@PathVariable("id") long id, Model model) {
		Optional <Pessoa> buscaPessoa = pessoaRepo.findById(id);
		
		if(buscaPessoa.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		model.addAttribute("pessoa", buscaPessoa.get());
		return "bean/pessoas/form";
	}
	
	@GetMapping("/bean/pessoas/excluir/{id}")
	public String excluir(@PathVariable("id") long id) {
		Optional <Pessoa> buscaPessoa = pessoaRepo.findById(id);
		
		if(buscaPessoa.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		pessoaRepo.delete(buscaPessoa.get());
		return "redirect:/bean/pessoas";
	}
}
