# Projeto Literatura

## Descrição
O projeto **Literatura** é uma aplicação em **Java** utilizando **Spring Boot** que permite buscar, registrar e listar livros e autores. Ele consome a **API Gutendex** para buscar informações sobre livros, autores, idiomas e número de downloads.  

A aplicação roda no console e oferece um menu interativo para o usuário.

---

## Funcionalidades

1. **Buscar livro pelo título (API)**  
   Permite buscar um livro pelo seu título na API Gutendex e salvar no banco de dados local. Exibe:
   - Título do livro
   - Autor
   - Idioma
   - Número de downloads

2. **Listar livros registrados**  
   Lista todos os livros já salvos no banco de dados.

3. **Listar autores registrados**  
   Lista todos os autores cadastrados, exibindo:
   - Nome
   - Ano de nascimento
   - Ano de falecimento

4. **Listar autores vivos em um determinado ano**  
   Permite filtrar autores que estavam vivos em um ano específico.

5. **Listar livros por idioma**  
   Permite filtrar livros salvos por idioma (PT, EN, FR, ES).

6. **Limpar banco de dados (apenas para testes)**  
   Remove todos os livros e autores cadastrados.

---

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.5.4
- Spring Data JPA
- PostgreSQL
- Maven
- RestTemplate para consumir API externa

---
## Estrutura do projeto

- `controle/Menu.java` → `Interface do usuário e menu interativo.`
- `modelo/` → `Classes` `Livro`, `Autor`, `GutendexBook`, `GutendexResponse`.
- `repositorio/` → `Interfaces` `LivroRepositorio`, `AutorRepositorio.`.
- `servico/LivroServico.java` → `Lógica de busca na API, cadastro e consultas ao banco.`
- `LiteraturaApplication.java` → `Classe principal para iniciar a aplicação.`

---

## Observações

- A aplicação utiliza a **API Gutendex** para buscar livros. Alguns títulos podem não ser encontrados dependendo do nome pesquisado.
- Para testes, há a opção de limpar todo o banco de dados.
- O número de downloads e datas dos autores são buscados diretamente da API e salvos no banco de dados.

---

## Exemplo de uso

````
===== Menu Literatura =====
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros por idioma
0 - Sair
Escolha uma opção: 1
Digite o título do livro: Emma

------ LIVRO ------
Título: Emma
Autor: Austen, Jane
Idioma: en
Número de downloads: 96753
````
---

## Autor
Gustavo Moreira Rocha
www.linkedin.com/in/gustavo-rocha-9b9682167
