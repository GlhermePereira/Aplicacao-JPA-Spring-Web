package br.edu.fatecpg.Aplicacao.JPA;

import br.edu.fatecpg.Aplicacao.JPA.model.Aluno;
import br.edu.fatecpg.Aplicacao.JPA.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class  AplicacaoJpaApplication implements CommandLineRunner {
	@Autowired
	private AlunoRepository repositorio;

 public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AplicacaoJpaApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8084"));
        app.run(args);
    }	
  @Override
	public void run(String... args) throws Exception {

		//Aluno aluno = new Aluno("Paulo","3210");
		//repositorio.save(aluno);

		//List<Aluno> alunos = repositorio.findAll();

		//alunos.forEach(System.out::println);
	//
		//	Optional<Aluno> aluno = repositorio.findByNomeContainingIgnoreCase("pa");
		//if(aluno.isPresent()){
	//		System.out.println(aluno.get());
	//	}else{
		//	System.out.println("Não Encontrado!");
		//}

		List<Aluno> alunos = repositorio.findAll();
		System.out.println("Alunos encontrados: " + alunos);  // Para depuração

	}
}
