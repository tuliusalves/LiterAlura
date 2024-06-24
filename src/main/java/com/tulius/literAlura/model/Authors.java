package com.tulius.literAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @ManyToOne
    private Books books;

    public Authors(){}

    public Authors(AuthorsData authorsData, Books books){
        String [] authors= authorsData.name().split(",");
        this.name= authors[1] + " "+authors[0];
        this.birth_year = authorsData.birth_year();
        this.death_year= authorsData.death_year();
        this.books= books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return name ;
    }
}
