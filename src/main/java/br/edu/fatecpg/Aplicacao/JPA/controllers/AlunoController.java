package br.edu.fatecpg.Aplicacao.JPA.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.fatecpg.Aplicacao.JPA.model.Aluno;
import br.edu.fatecpg.Aplicacao.JPA.repository.AlunoRepository;
import jakarta.transaction.Transactional;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/alunos")
public class AlunoController {
 
    @Autowired
    private AlunoRepository repository;

@Transactional
@GetMapping("/")
    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = repository.findAll();
        return alunos != null ? alunos : new ArrayList<>();
    }



    // Exibe a página para criar um novo aluno
    @GetMapping("/novo")
    public String criarAlunoForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "form";  // Nome do arquivo HTML para o formulário
    }

    // Cria um novo aluno
    @PostMapping("/novo")
    public String criarAluno(@ModelAttribute Aluno aluno) {
        repository.save(aluno);
        return "redirect:/alunos/";  // Redireciona para a lista de alunos após a criação
    }

    // Exibe a página para editar um aluno
    @GetMapping("/editar/{id}")
    public String editarAlunoForm(@PathVariable Long id, Model model) {
        Optional<Aluno> aluno = repository.findById(id);
        aluno.ifPresent(value -> model.addAttribute("aluno", value));
        return "form";  // Nome do arquivo HTML para o formulário
    }

    // Atualiza as informações de um aluno
    @PostMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, @ModelAttribute Aluno aluno) {
        aluno.setId(id);
        repository.save(aluno);
        return "redirect:/alunos/";  // Redireciona para a lista de alunos após a atualização
    }

    // Deleta um aluno
    @GetMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/alunos/";  // Redireciona para a lista de alunos após a exclusão
    }
}
