package br.com.grocha.Literatura.repositorio;

import br.com.grocha.Literatura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {


    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer anoNascimento, Integer anoFalecimento);


    Optional<Autor> findByNomeAndAnoNascimentoAndAnoFalecimento(String nome, Integer anoNascimento, Integer anoFalecimento);
}