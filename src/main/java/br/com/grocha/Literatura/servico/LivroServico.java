package br.com.grocha.Literatura.servico;

import br.com.grocha.Literatura.modelo.*;
import br.com.grocha.Literatura.repositorio.AutorRepositorio;
import br.com.grocha.Literatura.repositorio.LivroRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class LivroServico {

    private final LivroRepositorio livroRepositorio;
    private final AutorRepositorio autorRepositorio;
    private final RestTemplate restTemplate;

    public LivroServico(LivroRepositorio livroRepositorio, AutorRepositorio autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
        this.restTemplate = new RestTemplate();
    }
    public void limparBanco() {
        livroRepositorio.deleteAll();
        autorRepositorio.deleteAll();
        System.out.println("Banco de dados limpo com sucesso!");
    }

    // ATUALIZADO
    public Livro buscarLivroNaAPI(String titulo) {
        try {
            String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String url = "https://gutendex.com/books?search=" + tituloCodificado;

            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                // pega o primeiro resultado
                GutendexBook resultado = response.getResults().get(0);

                // pega o primeiro autor do resultado (a API pode trazer vários)
                GutendexAuthor a = (resultado.getAuthors() != null && !resultado.getAuthors().isEmpty())
                        ? resultado.getAuthors().get(0)
                        : null;

                String nomeAutor = (a != null && a.getName() != null) ? a.getName() : "Desconhecido";
                Integer nasc = (a != null) ? a.getBirthYear() : null;
                Integer fal  = (a != null) ? a.getDeathYear() : null;

                // evita duplicar autor: procura pelo mesmo nome+nascimento+falecimento
                Autor autor = autorRepositorio
                        .findByNomeAndAnoNascimentoAndAnoFalecimento(nomeAutor, nasc, fal)
                        .orElseGet(() -> {
                            Autor novo = new Autor();
                            novo.setNome(nomeAutor);
                            novo.setAnoNascimento(nasc);
                            novo.setAnoFalecimento(fal);
                            return autorRepositorio.save(novo);
                        });

                // cria e salva o livro
                Livro livro = new Livro();
                livro.setTitulo(resultado.getTitle());
                String idioma = (resultado.getLanguages() != null && !resultado.getLanguages().isEmpty())
                        ? resultado.getLanguages().get(0)
                        : "desconhecido";
                livro.setIdioma(idioma);
                livro.setDownloads(resultado.getDownloadCount());
                livro.setAutor(autor);

                return livroRepositorio.save(livro);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro na API: " + e.getMessage());
        }
        return null;
    }

    // demais métodos permanecem iguais
    public List<Livro> listarTodosLivros() {
        return livroRepositorio.findAll();
    }

    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    public List<Autor> listarAutoresPorAno(int ano) {
        return autorRepositorio.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepositorio.findByIdiomaIgnoreCase(idioma);
    }
}