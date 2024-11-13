package br.edu.fatecpg.Aplicacao.JPA.repository;

import br.edu.fatecpg.Aplicacao.JPA.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository
        extends JpaRepository<Aluno,Long> {
    Optional<Aluno> findByNomeContainingIgnoreCase(String nome);
}
