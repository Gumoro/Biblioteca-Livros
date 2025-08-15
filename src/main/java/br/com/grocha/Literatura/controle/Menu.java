package br.com.grocha.Literatura.controle;

import br.com.grocha.Literatura.modelo.Autor;
import br.com.grocha.Literatura.modelo.Livro;
import br.com.grocha.Literatura.servico.LivroServico;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final LivroServico livroServico;
    private final Scanner scanner;

    public Menu(LivroServico livroServico) {
        this.livroServico = livroServico;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== Menu Literatura =====");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em um determinado ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("6 - Limpar banco de dados (apenas para testes)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    livroServico.limparBanco();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        Livro livro = livroServico.buscarLivroNaAPI(titulo);
        if (livro != null) {
            System.out.println("\n------ LIVRO ------");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor().getNome());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Número de downloads: " + livro.getDownloads());
        } else {
            System.out.println("Livro não encontrado na API.");
        }
    }

    private void listarLivros() {
        List<Livro> livros = livroServico.listarTodosLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            for (Livro l : livros) {
                System.out.println("\n------ LIVRO ------");
                System.out.println("Título: " + l.getTitulo());
                System.out.println("Autor: " + l.getAutor().getNome());
                System.out.println("Idioma: " + l.getIdioma());
                System.out.println("Número de downloads: " + l.getDownloads());
            }
        }
    }

    private void listarAutores() {
        List<Autor> autores = livroServico.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        autores.forEach(a -> {
            String nasc = (a.getAnoNascimento() != null) ? a.getAnoNascimento().toString() : "desconhecido";
            String fal  = (a.getAnoFalecimento() != null) ? a.getAnoFalecimento().toString() : "desconhecido";
            System.out.println("Autor: " + a.getNome() + " | Nasc: " + nasc + " | Falecimento: " + fal);
        });
    }

    private void listarAutoresPorAno() {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        List<Autor> autores = livroServico.listarAutoresPorAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado nesse ano.");
        } else {
            autores.forEach(a -> System.out.println(a.getNome()));
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma (PT, EN, FR, ES): ");
        String idioma = scanner.nextLine();
        List<Livro> livros = livroServico.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma.");
        } else {
            for (Livro l : livros) {
                System.out.println("\n------ LIVRO ------");
                System.out.println("Título: " + l.getTitulo());
                System.out.println("Autor: " + l.getAutor().getNome());
                System.out.println("Idioma: " + l.getIdioma());
                System.out.println("Número de downloads: " + l.getDownloads());
            }
        }
    }
}