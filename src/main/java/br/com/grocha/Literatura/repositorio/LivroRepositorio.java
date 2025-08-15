package br.com.grocha.Literatura.repositorio;

import br.com.grocha.Literatura.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long> {


    List<Livro> findByIdiomaIgnoreCase(String idioma);
    List<Livro> findByTitulo(String titulo);
}