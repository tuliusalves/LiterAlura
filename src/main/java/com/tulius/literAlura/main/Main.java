package com.tulius.literAlura.main;

import com.tulius.literAlura.model.Authors;
import com.tulius.literAlura.model.Books;
import com.tulius.literAlura.model.BooksData;
import com.tulius.literAlura.model.ResultsData;
import com.tulius.literAlura.repository.BooksRepository;
import com.tulius.literAlura.service.ConvertData;
import com.tulius.literAlura.service.GetAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private BooksRepository repository;
    private ConvertData converter = new ConvertData();
    private GetAPI getAPI = new GetAPI();
    private Scanner scanner = new Scanner(System.in);
    List<Books> books = new ArrayList<>();

    private String bookName;

    public Main(BooksRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        int option = 1;

        while (option != 0) {
            System.out.println("""
                    Execute uma das operações abaixo digitando um número:
                    1 - Buscar livro pelo nome.
                    2 - Listar todos os livros pesquisados.
                    3 - Listar os autores.
                    4 - Listar os autores vivos em determinado ano.
                    5 - Listar livros pelo seu idioma.
                    6 - Listar o top 5 dos livros mais baixados.
                                        
                    0 - Sair do programa.""");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    searchBook();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    System.out.println("Digite o ano para procurar os autores");
                    Integer year = scanner.nextInt();
                    scanner.nextLine();
                    livingAuthorsInYear(year);
                    break;
                case 5:
                    List<Books> books = repository.findAllBooksOrderByLanguages();
                    books.forEach(b -> System.out.println("Idioma = "+ Arrays.toString(b.getLanguages())
                            +", Título = "+b.getTitle()));
                    break;
                case 6:
                    top5Books();
                    break;
                case 0:
                    System.out.println("Sistema encerrado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }


        }
    }

    private ResultsData getBookData(String bookName) {
        String ADDRESS = "https://gutendex.com/books/?search=";
        var json = getAPI.getData(ADDRESS + bookName.toLowerCase().replace(" ", "%20"));
        return converter.convertData(json, ResultsData.class);
    }

    private void searchBook(){
        System.out.println("Digite o nome do livro: ");
        String bookName = scanner.nextLine();

        ResultsData resultsData = getBookData(bookName);
        List<BooksData> booksData= resultsData.results().stream().toList();
        booksData.forEach(b -> books.add(new Books(b)));
        try {
            books.forEach(b -> repository.save(b));
        }catch (Exception e){
            System.out.println("Livro não encontrado ");
        }
        System.out.println(repository.findByTitleContainingIgnoreCase(bookName));
    }

    private void listAllBooks() {
        List<Books> books = repository.findAll();

        books.forEach(System.out::println);
    }

    private void listAllAuthors(){
        List<Authors> listAuthors = repository.findAllAuthors();
        listAuthors.forEach(System.out::println);
    }

    private void livingAuthorsInYear(Integer year){
        List<Authors> authors = repository.findAllAuthors();

        authors.forEach(a -> {if(a.getDeath_year() !=null || a.getBirth_year()!=null)
            if(year >= a.getBirth_year() && year <= a.getDeath_year())
                System.out.println(a);
        });
    }

    private void top5Books(){
        List<Books> booksTop5 = repository.findTop5ByOrderByDownloadCountDesc();
        booksTop5.forEach(System.out::println);
    }
}