package br.com.crudsb;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.crudsb.model.Pessoa;
import br.com.crudsb.repository.PessoaRepository;

@Component
@Transactional
public class BancoInicial implements CommandLineRunner {

	@Autowired
	private PessoaRepository pessoaRepo;

	@Override
	public void run(String... args) throws Exception {

		Pessoa p1 = new Pessoa("Julio");
		p1.setDataNascimento(LocalDate.of(1998, 10, 6));
		p1.setEmail("julio@gmail.com");
		
		Pessoa p2 = new Pessoa("Joaquina");
		p2.setDataNascimento(LocalDate.of(1999, 12, 5));
		p2.setEmail("joaquina@gmail.com");

		pessoaRepo.save(p1);
		pessoaRepo.save(p2);
	}
}
