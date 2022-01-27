package br.com.crudsb.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.crudsb.model.Pessoa;
import br.com.crudsb.repository.PessoaRepository;

@Controller
public class PessoaService {

	private PessoaRepository pessoaRepo;

	public PessoaService(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	@GetMapping("/bean/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "bean/pessoas/index";
	}
	
	@GetMapping("/bean/pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "/bean/pessoas/form";
	}
	
	@PostMapping("bean/pessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return "redirect:/bean/pessoas";
	}
}
